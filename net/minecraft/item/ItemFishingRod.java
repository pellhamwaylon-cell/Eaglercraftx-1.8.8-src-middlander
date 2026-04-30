package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
	public ItemFishingRod() {
		this.setMaxDamage(64);
		this.setMaxStackSize(1);
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	public boolean isFull3D() {
		return true;
	}

	public boolean shouldRotateAroundWhenRendering() {
		return true;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (entityplayer.fishEntity != null) {
			int i = entityplayer.fishEntity.handleHookRetraction();
			itemstack.damageItem(i, entityplayer);
			entityplayer.swingItem();
		} else {
			world.playSoundAtEntity(entityplayer, "random.bow", 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
			if (!world.isRemote) {
				world.spawnEntityInWorld(new EntityFishHook(world, entityplayer));
			}

			entityplayer.swingItem();
			entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
		}

		return itemstack;
	}

	public boolean isItemTool(ItemStack itemstack) {
		return super.isItemTool(itemstack);
	}

	public int getItemEnchantability() {
		return 1;
	}
}
