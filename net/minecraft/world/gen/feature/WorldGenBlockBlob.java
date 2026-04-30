package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenBlockBlob extends WorldGenerator {
	private final Block field_150545_a;
	private final int field_150544_b;

	public WorldGenBlockBlob(Block parBlock, int parInt1) {
		super(false);
		this.field_150545_a = parBlock;
		this.field_150544_b = parInt1;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		while (true) {
			label0: {
				if (blockpos.getY() > 3) {
					if (world.isAirBlock(blockpos.down())) {
						break label0;
					}

					Block block = world.getBlockState(blockpos.down()).getBlock();
					if (block != Blocks.grass && block != Blocks.dirt && block != Blocks.stone) {
						break label0;
					}
				}

				if (blockpos.getY() <= 3) {
					return false;
				}

				int i1 = this.field_150544_b;

				for (int i = 0; i1 >= 0 && i < 3; ++i) {
					int j = i1 + random.nextInt(2);
					int k = i1 + random.nextInt(2);
					int l = i1 + random.nextInt(2);
					float f = (float) (j + k + l) * 0.333F + 0.5F;

					for (BlockPos blockpos1 : BlockPos.getAllInBoxMutable(blockpos.add(-j, -k, -l),
							blockpos.add(j, k, l))) {
						if (blockpos1.distanceSq(blockpos) <= (double) (f * f)) {
							world.setBlockState(blockpos1, this.field_150545_a.getDefaultState(), 4);
						}
					}

					blockpos = blockpos.add(-(i1 + 1) + random.nextInt(2 + i1 * 2), 0 - random.nextInt(2),
							-(i1 + 1) + random.nextInt(2 + i1 * 2));
				}

				return true;
			}

			blockpos = blockpos.down();
		}
	}
}
