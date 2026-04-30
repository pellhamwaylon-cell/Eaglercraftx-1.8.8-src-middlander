package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenShrub extends WorldGenTrees {
	private final IBlockState leavesMetadata;
	private final IBlockState woodMetadata;

	public WorldGenShrub(IBlockState parIBlockState, IBlockState parIBlockState2) {
		super(false);
		this.woodMetadata = parIBlockState;
		this.leavesMetadata = parIBlockState2;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		Block block;
		while (((block = world.getBlockState(blockpos).getBlock()).getMaterial() == Material.air
				|| block.getMaterial() == Material.leaves) && blockpos.getY() > 0) {
			blockpos = blockpos.down();
		}

		Block block1 = world.getBlockState(blockpos).getBlock();
		if (block1 == Blocks.dirt || block1 == Blocks.grass) {
			blockpos = blockpos.up();
			this.setBlockAndNotifyAdequately(world, blockpos, this.woodMetadata);

			for (int i = blockpos.getY(); i <= blockpos.getY() + 2; ++i) {
				int j = i - blockpos.getY();
				int k = 2 - j;

				for (int l = blockpos.getX() - k; l <= blockpos.getX() + k; ++l) {
					int i1 = l - blockpos.getX();

					for (int j1 = blockpos.getZ() - k; j1 <= blockpos.getZ() + k; ++j1) {
						int k1 = j1 - blockpos.getZ();
						if (Math.abs(i1) != k || Math.abs(k1) != k || random.nextInt(2) != 0) {
							BlockPos blockpos1 = new BlockPos(l, i, j1);
							if (!world.getBlockState(blockpos1).getBlock().isFullBlock()) {
								this.setBlockAndNotifyAdequately(world, blockpos1, this.leavesMetadata);
							}
						}
					}
				}
			}
		}

		return true;
	}
}
