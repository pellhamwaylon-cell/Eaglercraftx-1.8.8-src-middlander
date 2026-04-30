package net.minecraft.entity.item;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityMinecartEmpty extends EntityMinecart {
	public EntityMinecartEmpty(World worldIn) {
		super(worldIn);
	}

	public EntityMinecartEmpty(World worldIn, double parDouble1, double parDouble2, double parDouble3) {
		super(worldIn, parDouble1, parDouble2, parDouble3);
	}

	public boolean interactFirst(EntityPlayer entityplayer) {
		if (this.riddenByEntity != null && this.riddenByEntity instanceof EntityPlayer
				&& this.riddenByEntity != entityplayer) {
			return true;
		} else if (this.riddenByEntity != null && this.riddenByEntity != entityplayer) {
			return false;
		} else {
			if (!this.worldObj.isRemote) {
				entityplayer.mountEntity(this);
			}

			return true;
		}
	}

	public void onActivatorRailPass(int var1, int var2, int var3, boolean flag) {
		if (flag) {
			if (this.riddenByEntity != null) {
				this.riddenByEntity.mountEntity((Entity) null);
			}

			if (this.getRollingAmplitude() == 0) {
				this.setRollingDirection(-this.getRollingDirection());
				this.setRollingAmplitude(10);
				this.setDamage(50.0F);
				this.setBeenAttacked();
			}
		}

	}

	public EntityMinecart.EnumMinecartType getMinecartType() {
		return EntityMinecart.EnumMinecartType.RIDEABLE;
	}
}
