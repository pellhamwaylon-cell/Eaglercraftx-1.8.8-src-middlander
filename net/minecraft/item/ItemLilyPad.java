package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class ItemLilyPad extends ItemColored {
	public ItemLilyPad(Block block) {
		super(block, false);
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

				BlockPos blockpos1 = blockpos.up();
				IBlockState iblockstate = world.getBlockState(blockpos);
				if (iblockstate.getBlock().getMaterial() == Material.water
						&& ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0
						&& world.isAirBlock(blockpos1)) {
					world.setBlockState(blockpos1, Blocks.waterlily.getDefaultState());
					if (!entityplayer.capabilities.isCreativeMode) {
						--itemstack.stackSize;
					}

					entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
				}
			}

			return itemstack;
		}
	}

	public int getColorFromItemStack(ItemStack itemstack, int var2) {
		return Blocks.waterlily.getRenderColor(Blocks.waterlily.getStateFromMeta(itemstack.getMetadata()));
	}
}
