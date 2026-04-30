package net.minecraft.entity.ai;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBed;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityAIOcelotSit extends EntityAIMoveToBlock {
	private final EntityOcelot field_151493_a;

	public EntityAIOcelotSit(EntityOcelot parEntityOcelot, double parDouble1) {
		super(parEntityOcelot, parDouble1, 8);
		this.field_151493_a = parEntityOcelot;
	}

	public boolean shouldExecute() {
		return this.field_151493_a.isTamed() && !this.field_151493_a.isSitting() && super.shouldExecute();
	}

	public boolean continueExecuting() {
		return super.continueExecuting();
	}

	public void startExecuting() {
		super.startExecuting();
		this.field_151493_a.getAISit().setSitting(false);
	}

	public void resetTask() {
		super.resetTask();
		this.field_151493_a.setSitting(false);
	}

	public void updateTask() {
		super.updateTask();
		this.field_151493_a.getAISit().setSitting(false);
		if (!this.getIsAboveDestination()) {
			this.field_151493_a.setSitting(false);
		} else if (!this.field_151493_a.isSitting()) {
			this.field_151493_a.setSitting(true);
		}

	}

	protected boolean shouldMoveTo(World world, BlockPos blockpos) {
		if (!world.isAirBlock(blockpos.up())) {
			return false;
		} else {
			IBlockState iblockstate = world.getBlockState(blockpos);
			Block block = iblockstate.getBlock();
			if (block == Blocks.chest) {
				TileEntity tileentity = world.getTileEntity(blockpos);
				if (tileentity instanceof TileEntityChest && ((TileEntityChest) tileentity).numPlayersUsing < 1) {
					return true;
				}
			} else {
				if (block == Blocks.lit_furnace) {
					return true;
				}

				if (block == Blocks.bed && iblockstate.getValue(BlockBed.PART) != BlockBed.EnumPartType.HEAD) {
					return true;
				}
			}

			return false;
		}
	}
}
