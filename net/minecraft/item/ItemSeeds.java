package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSeeds extends Item {
	private Block crops;
	private Block soilBlockID;

	public ItemSeeds(Block crops, Block soil) {
		this.crops = crops;
		this.soilBlockID = soil;
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing enumfacing, float var6, float var7, float var8) {
		if (enumfacing != EnumFacing.UP) {
			return false;
		} else if (!entityplayer.canPlayerEdit(blockpos.offset(enumfacing), enumfacing, itemstack)) {
			return false;
		} else if (world.getBlockState(blockpos).getBlock() == this.soilBlockID && world.isAirBlock(blockpos.up())) {
			world.setBlockState(blockpos.up(), this.crops.getDefaultState());
			--itemstack.stackSize;
			return true;
		} else {
			return false;
		}
	}
}
