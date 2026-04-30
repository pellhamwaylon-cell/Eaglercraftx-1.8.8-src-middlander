package net.minecraft.entity;

import net.minecraft.util.MathHelper;

public class EntityBodyHelper {
	private EntityLivingBase theLiving;
	private int rotationTickCounter;
	private float prevRenderYawHead;

	public EntityBodyHelper(EntityLivingBase parEntityLivingBase) {
		this.theLiving = parEntityLivingBase;
	}

	public void updateRenderAngles() {
		double d0 = this.theLiving.posX - this.theLiving.prevPosX;
		double d1 = this.theLiving.posZ - this.theLiving.prevPosZ;
		if (d0 * d0 + d1 * d1 > 2.500000277905201E-7D) {
			this.theLiving.renderYawOffset = this.theLiving.rotationYaw;
			this.theLiving.rotationYawHead = this.computeAngleWithBound(this.theLiving.renderYawOffset,
					this.theLiving.rotationYawHead, 75.0F);
			this.prevRenderYawHead = this.theLiving.rotationYawHead;
			this.rotationTickCounter = 0;
		} else {
			float f = 75.0F;
			if (Math.abs(this.theLiving.rotationYawHead - this.prevRenderYawHead) > 15.0F) {
				this.rotationTickCounter = 0;
				this.prevRenderYawHead = this.theLiving.rotationYawHead;
			} else {
				++this.rotationTickCounter;
				boolean flag = true;
				if (this.rotationTickCounter > 10) {
					f = Math.max(1.0F - (float) (this.rotationTickCounter - 10) / 10.0F, 0.0F) * 75.0F;
				}
			}

			this.theLiving.renderYawOffset = this.computeAngleWithBound(this.theLiving.rotationYawHead,
					this.theLiving.renderYawOffset, f);
		}
	}

	private float computeAngleWithBound(float parFloat1, float parFloat2, float parFloat3) {
		float f = MathHelper.wrapAngleTo180_float(parFloat1 - parFloat2);
		if (f < -parFloat3) {
			f = -parFloat3;
		}

		if (f >= parFloat3) {
			f = parFloat3;
		}

		return parFloat1 - f;
	}
}
