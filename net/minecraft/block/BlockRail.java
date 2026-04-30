package net.minecraft.block;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRail extends BlockRailBase {
	public static PropertyEnum<BlockRailBase.EnumRailDirection> SHAPE;

	protected BlockRail() {
		super(false);
		this.setDefaultState(
				this.blockState.getBaseState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.NORTH_SOUTH));
	}

	public static void bootstrapStates() {
		SHAPE = PropertyEnum.<BlockRailBase.EnumRailDirection>create("shape", BlockRailBase.EnumRailDirection.class);
	}

	protected void onNeighborChangedInternal(World world, BlockPos blockpos, IBlockState iblockstate, Block block) {
		if (block.canProvidePower()
				&& (new BlockRailBase.Rail(world, blockpos, iblockstate)).countAdjacentRails() == 3) {
			this.func_176564_a(world, blockpos, iblockstate, false);
		}

	}

	public IProperty<BlockRailBase.EnumRailDirection> getShapeProperty() {
		return SHAPE;
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(SHAPE, BlockRailBase.EnumRailDirection.byMetadata(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((BlockRailBase.EnumRailDirection) iblockstate.getValue(SHAPE)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { SHAPE });
	}
}
