package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemRedstone extends Item {
	public ItemRedstone() {
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing enumfacing, float var6, float var7, float var8) {
		boolean flag = world.getBlockState(blockpos).getBlock().isReplaceable(world, blockpos);
		BlockPos blockpos1 = flag ? blockpos : blockpos.offset(enumfacing);
		if (!entityplayer.canPlayerEdit(blockpos1, enumfacing, itemstack)) {
			return false;
		} else {
			Block block = world.getBlockState(blockpos1).getBlock();
			if (!world.canBlockBePlaced(block, blockpos1, false, enumfacing, (Entity) null, itemstack)) {
				return false;
			} else if (Blocks.redstone_wire.canPlaceBlockAt(world, blockpos1)) {
				--itemstack.stackSize;
				world.setBlockState(blockpos1, Blocks.redstone_wire.getDefaultState());
				return true;
			} else {
				return false;
			}
		}
	}
}
