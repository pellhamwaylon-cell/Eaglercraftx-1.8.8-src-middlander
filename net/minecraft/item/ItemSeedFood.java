package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemSeedFood extends ItemFood {
	private Block crops;
	private Block soilId;

	public ItemSeedFood(int healAmount, float saturation, Block crops, Block soil) {
		super(healAmount, saturation, false);
		this.crops = crops;
		this.soilId = soil;
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing enumfacing, float var6, float var7, float var8) {
		if (enumfacing != EnumFacing.UP) {
			return false;
		} else if (!entityplayer.canPlayerEdit(blockpos.offset(enumfacing), enumfacing, itemstack)) {
			return false;
		} else if (world.getBlockState(blockpos).getBlock() == this.soilId && world.isAirBlock(blockpos.up())) {
			world.setBlockState(blockpos.up(), this.crops.getDefaultState());
			--itemstack.stackSize;
			return true;
		} else {
			return false;
		}
	}
}
