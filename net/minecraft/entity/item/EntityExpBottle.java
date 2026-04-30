package net.minecraft.entity.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityExpBottle extends EntityThrowable {
	public EntityExpBottle(World worldIn) {
		super(worldIn);
	}

	public EntityExpBottle(World worldIn, EntityLivingBase parEntityLivingBase) {
		super(worldIn, parEntityLivingBase);
	}

	public EntityExpBottle(World worldIn, double parDouble1, double parDouble2, double parDouble3) {
		super(worldIn, parDouble1, parDouble2, parDouble3);
	}

	protected float getGravityVelocity() {
		return 0.07F;
	}

	protected float getVelocity() {
		return 0.7F;
	}

	protected float getInaccuracy() {
		return -20.0F;
	}

	protected void onImpact(MovingObjectPosition var1) {
		if (!this.worldObj.isRemote) {
			this.worldObj.playAuxSFX(2002, new BlockPos(this), 0);
			int i = 3 + this.worldObj.rand.nextInt(5) + this.worldObj.rand.nextInt(5);

			while (i > 0) {
				int j = EntityXPOrb.getXPSplit(i);
				i -= j;
				this.worldObj.spawnEntityInWorld(new EntityXPOrb(this.worldObj, this.posX, this.posY, this.posZ, j));
			}

			this.setDead();
		}

	}
}
