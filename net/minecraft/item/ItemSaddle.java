package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;

public class ItemSaddle extends Item {
	public ItemSaddle() {
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabTransport);
	}

	public boolean itemInteractionForEntity(ItemStack itemstack, EntityPlayer var2, EntityLivingBase entitylivingbase) {
		if (entitylivingbase instanceof EntityPig) {
			EntityPig entitypig = (EntityPig) entitylivingbase;
			if (!entitypig.getSaddled() && !entitypig.isChild()) {
				entitypig.setSaddled(true);
				entitypig.worldObj.playSoundAtEntity(entitypig, "mob.horse.leather", 0.5F, 1.0F);
				--itemstack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		this.itemInteractionForEntity(stack, (EntityPlayer) null, target);
		return true;
	}
}
