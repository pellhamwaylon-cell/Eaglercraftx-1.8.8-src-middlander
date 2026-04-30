package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemGlassBottle extends Item {
	public ItemGlassBottle() {
		this.setCreativeTab(CreativeTabs.tabBrewing);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(world, entityplayer, true);
		if (movingobjectposition == null) {
			return itemstack;
		} else {
			if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
				BlockPos blockpos = movingobjectposition.getBlockPos();
				if (!world.isBlockModifiable(entityplayer, blockpos)) {
					return itemstack;
				}

				if (!entityplayer.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit),
						movingobjectposition.sideHit, itemstack)) {
					return itemstack;
				}

				if (world.getBlockState(blockpos).getBlock().getMaterial() == Material.water) {
					--itemstack.stackSize;
					entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
					if (itemstack.stackSize <= 0) {
						return new ItemStack(Items.potionitem);
					}

					if (!entityplayer.inventory.addItemStackToInventory(new ItemStack(Items.potionitem))) {
						entityplayer.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
					}
				}
			}

			return itemstack;
		}
	}
}
