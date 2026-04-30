package net.minecraft.entity.item;

import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DynamicLightManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityTNTPrimed extends Entity {
	public int fuse;
	private EntityLivingBase tntPlacedBy;

	public EntityTNTPrimed(World worldIn) {
		super(worldIn);
		this.preventEntitySpawning = true;
		this.setSize(0.98F, 0.98F);
	}

	public EntityTNTPrimed(World worldIn, double parDouble1, double parDouble2, double parDouble3,
			EntityLivingBase parEntityLivingBase) {
		this(worldIn);
		this.setPosition(parDouble1, parDouble2, parDouble3);
		float f = (float) (Math.random() * 3.1415927410125732D * 2.0D);
		this.motionX = (double) (-((float) Math.sin((double) f)) * 0.02F);
		this.motionY = 0.20000000298023224D;
		this.motionZ = (double) (-((float) Math.cos((double) f)) * 0.02F);
		this.fuse = 80;
		this.prevPosX = parDouble1;
		this.prevPosY = parDouble2;
		this.prevPosZ = parDouble3;
		this.tntPlacedBy = parEntityLivingBase;
	}

	protected void entityInit() {
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return !this.isDead;
	}

	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		this.motionY -= 0.03999999910593033D;
		this.moveEntity(this.motionX, this.motionY, this.motionZ);
		this.motionX *= 0.9800000190734863D;
		this.motionY *= 0.9800000190734863D;
		this.motionZ *= 0.9800000190734863D;
		if (this.onGround) {
			this.motionX *= 0.699999988079071D;
			this.motionZ *= 0.699999988079071D;
			this.motionY *= -0.5D;
		}

		if (this.fuse-- <= 0) {
			this.setDead();
			if (!this.worldObj.isRemote) {
				this.explode();
			}
		} else {
			this.handleWaterMovement();
			this.worldObj.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5D, this.posZ, 0.0D,
					0.0D, 0.0D, new int[0]);
		}

	}

	private void explode() {
		float f = 4.0F;
		this.worldObj.createExplosion(this, this.posX, this.posY + (double) (this.height / 16.0F), this.posZ, f, true);
	}

	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setByte("Fuse", (byte) this.fuse);
	}

	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		this.fuse = nbttagcompound.getByte("Fuse");
	}

	public EntityLivingBase getTntPlacedBy() {
		return this.tntPlacedBy;
	}

	public float getEyeHeight() {
		return 0.0F;
	}

	protected void renderDynamicLightsEaglerAt(double entityX, double entityY, double entityZ, double renderX,
			double renderY, double renderZ, float partialTicks, boolean isInFrustum) {
		super.renderDynamicLightsEaglerAt(entityX, entityY, entityZ, renderX, renderY, renderZ, partialTicks,
				isInFrustum);
		if (fuse / 5 % 2 == 0) {
			float dynamicLightMag = 10.0f;
			DynamicLightManager.renderDynamicLight("entity_" + getEntityId() + "_tnt_flash", entityX, entityY + 0.5,
					entityZ, dynamicLightMag, dynamicLightMag * 0.7792f, dynamicLightMag * 0.618f, false);
		}
	}

	protected float getEaglerDynamicLightsValueSimple(float partialTicks) {
		float f = super.getEaglerDynamicLightsValueSimple(partialTicks);
		if (fuse / 5 % 2 == 0) {
			f = Math.min(f + 0.75f, 1.25f);
		}
		return f;
	}
}
