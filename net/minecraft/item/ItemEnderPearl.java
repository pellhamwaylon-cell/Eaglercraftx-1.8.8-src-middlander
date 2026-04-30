package net.minecraft.item;

import net.lax1dude.eaglercraft.v1_8.sp.SingleplayerServerController;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemEnderPearl extends Item {
	public ItemEnderPearl() {
		this.maxStackSize = 16;
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.capabilities.isCreativeMode && world.isRemote
				&& !SingleplayerServerController.isClientInEaglerSingleplayerOrLAN()) {
			return itemstack;
		} else {
			if (!entityplayer.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityEnderPearl(world, entityplayer));
			}

			entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
			return itemstack;
		}
	}

	public boolean shouldUseOnTouchEagler(ItemStack itemStack) {
		return true;
	}
}
