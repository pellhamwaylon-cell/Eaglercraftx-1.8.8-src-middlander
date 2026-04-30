package net.minecraft.block.state;

import com.google.common.base.Predicate;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockWorldState {
	private final World world;
	private final BlockPos pos;
	private final boolean field_181628_c;
	private IBlockState state;
	private TileEntity tileEntity;
	private boolean tileEntityInitialized;

	public BlockWorldState(World parWorld, BlockPos parBlockPos, boolean parFlag) {
		this.world = parWorld;
		this.pos = parBlockPos;
		this.field_181628_c = parFlag;
	}

	public IBlockState getBlockState() {
		if (this.state == null && (this.field_181628_c || this.world.isBlockLoaded(this.pos))) {
			this.state = this.world.getBlockState(this.pos);
		}

		return this.state;
	}

	public TileEntity getTileEntity() {
		if (this.tileEntity == null && !this.tileEntityInitialized) {
			this.tileEntity = this.world.getTileEntity(this.pos);
			this.tileEntityInitialized = true;
		}

		return this.tileEntity;
	}

	public BlockPos getPos() {
		return this.pos;
	}

	public static Predicate<BlockWorldState> hasState(final Predicate<IBlockState> parPredicate) {
		return new Predicate<BlockWorldState>() {
			public boolean apply(BlockWorldState blockworldstate) {
				return blockworldstate != null && parPredicate.apply(blockworldstate.getBlockState());
			}
		};
	}
}
