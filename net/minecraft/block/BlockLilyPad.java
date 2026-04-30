package net.minecraft.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockLilyPad extends BlockBush {
	protected BlockLilyPad() {
		float f = 0.5F;
		float f1 = 0.015625F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f1, 0.5F + f);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void addCollisionBoxesToList(World world, BlockPos blockpos, IBlockState iblockstate,
			AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, Entity entity) {
		if (entity == null || !(entity instanceof EntityBoat)) {
			super.addCollisionBoxesToList(world, blockpos, iblockstate, axisalignedbb, list, entity);
		}

	}

	public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos blockpos, IBlockState var3) {
		return new AxisAlignedBB((double) blockpos.getX() + this.minX, (double) blockpos.getY() + this.minY,
				(double) blockpos.getZ() + this.minZ, (double) blockpos.getX() + this.maxX,
				(double) blockpos.getY() + this.maxY, (double) blockpos.getZ() + this.maxZ);
	}

	public int getBlockColor() {
		return 7455580;
	}

	public int getRenderColor(IBlockState var1) {
		return 7455580;
	}

	public int colorMultiplier(IBlockAccess var1, BlockPos var2, int var3) {
		return 2129968;
	}

	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.water;
	}

	public boolean canBlockStay(World world, BlockPos blockpos, IBlockState var3) {
		if (blockpos.getY() >= 0 && blockpos.getY() < 256) {
			IBlockState iblockstate = world.getBlockState(blockpos.down());
			return iblockstate.getBlock().getMaterial() == Material.water
					&& ((Integer) iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0;
		} else {
			return false;
		}
	}

	public int getMetaFromState(IBlockState var1) {
		return 0;
	}
}
