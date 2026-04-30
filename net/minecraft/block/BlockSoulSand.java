package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockSoulSand extends Block {
	public BlockSoulSand() {
		super(Material.sand, MapColor.brownColor);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos blockpos, IBlockState var3) {
		float f = 0.125F;
		return new AxisAlignedBB((double) blockpos.getX(), (double) blockpos.getY(), (double) blockpos.getZ(),
				(double) (blockpos.getX() + 1), (double) ((float) (blockpos.getY() + 1) - f),
				(double) (blockpos.getZ() + 1));
	}

	public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity entity) {
		entity.motionX *= 0.4D;
		entity.motionZ *= 0.4D;
	}
}
