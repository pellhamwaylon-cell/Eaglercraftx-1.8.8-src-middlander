package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemCarrotOnAStick extends Item {
	public ItemCarrotOnAStick() {
		this.setCreativeTab(CreativeTabs.tabTransport);
		this.setMaxStackSize(1);
		this.setMaxDamage(25);
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World var2, EntityPlayer entityplayer) {
		if (entityplayer.isRiding() && entityplayer.ridingEntity instanceof EntityPig) {
			EntityPig entitypig = (EntityPig) entityplayer.ridingEntity;
			if (entitypig.getAIControlledByPlayer().isControlledByPlayer()
					&& itemstack.getMaxDamage() - itemstack.getMetadata() >= 7) {
				entitypig.getAIControlledByPlayer().boostSpeed();
				itemstack.damageItem(7, entityplayer);
				if (itemstack.stackSize == 0) {
					ItemStack itemstack1 = new ItemStack(Items.fishing_rod);
					itemstack1.setTagCompound(itemstack.getTagCompound());
					return itemstack1;
				}
			}
		}

		entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
		return itemstack;
	}
}
