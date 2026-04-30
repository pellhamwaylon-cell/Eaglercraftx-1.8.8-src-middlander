package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BlockStaticLiquid extends BlockLiquid {
	protected BlockStaticLiquid(Material materialIn) {
		super(materialIn);
		this.setTickRandomly(false);
		if (materialIn == Material.lava) {
			this.setTickRandomly(true);
		}

	}

	public void onNeighborBlockChange(World world, BlockPos blockpos, IBlockState iblockstate, Block var4) {
		if (!this.checkForMixing(world, blockpos, iblockstate)) {
			this.updateLiquid(world, blockpos, iblockstate);
		}

	}

	private void updateLiquid(World worldIn, BlockPos pos, IBlockState state) {
		BlockDynamicLiquid blockdynamicliquid = getFlowingBlock(this.blockMaterial);
		worldIn.setBlockState(pos, blockdynamicliquid.getDefaultState().withProperty(LEVEL, state.getValue(LEVEL)), 2);
		worldIn.scheduleUpdate(pos, blockdynamicliquid, this.tickRate(worldIn));
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom random) {
		if (this.blockMaterial == Material.lava) {
			if (world.getGameRules().getBoolean("doFireTick")) {
				int i = random.nextInt(3);
				if (i > 0) {
					BlockPos blockpos1 = blockpos;

					for (int j = 0; j < i; ++j) {
						blockpos1 = blockpos1.add(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
						Block block = world.getBlockState(blockpos1).getBlock();
						if (block.blockMaterial == Material.air) {
							if (this.isSurroundingBlockFlammable(world, blockpos1)) {
								world.setBlockState(blockpos1, Blocks.fire.getDefaultState());
								return;
							}
						} else if (block.blockMaterial.blocksMovement()) {
							return;
						}
					}
				} else {
					for (int k = 0; k < 3; ++k) {
						BlockPos blockpos2 = blockpos.add(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
						if (world.isAirBlock(blockpos2.up()) && this.getCanBlockBurn(world, blockpos2)) {
							world.setBlockState(blockpos2.up(), Blocks.fire.getDefaultState());
						}
					}
				}

			}
		}
	}

	protected boolean isSurroundingBlockFlammable(World worldIn, BlockPos pos) {
		EnumFacing[] facings = EnumFacing._VALUES;
		for (int i = 0; i < facings.length; ++i) {
			EnumFacing enumfacing = facings[i];
			if (this.getCanBlockBurn(worldIn, pos.offset(enumfacing))) {
				return true;
			}
		}

		return false;
	}

	private boolean getCanBlockBurn(World worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos).getBlock().getMaterial().getCanBurn();
	}
}
