package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockStandingSign extends BlockSign {
	public static final PropertyInteger ROTATION = PropertyInteger.create("rotation", 0, 15);

	public BlockStandingSign() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(ROTATION, Integer.valueOf(0)));
	}

	public void onNeighborBlockChange(World world, BlockPos blockpos, IBlockState iblockstate, Block block) {
		if (!world.getBlockState(blockpos.down()).getBlock().getMaterial().isSolid()) {
			this.dropBlockAsItem(world, blockpos, iblockstate, 0);
			world.setBlockToAir(blockpos);
		}

		super.onNeighborBlockChange(world, blockpos, iblockstate, block);
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(ROTATION, Integer.valueOf(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((Integer) iblockstate.getValue(ROTATION)).intValue();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { ROTATION });
	}
}
