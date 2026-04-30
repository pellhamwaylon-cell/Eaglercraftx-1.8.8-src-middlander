package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenIcePath extends WorldGenerator {
	private Block block = Blocks.packed_ice;
	private int basePathWidth;

	public WorldGenIcePath(int parInt1) {
		this.basePathWidth = parInt1;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		while (world.isAirBlock(blockpos) && blockpos.getY() > 2) {
			blockpos = blockpos.down();
		}

		if (world.getBlockState(blockpos).getBlock() != Blocks.snow) {
			return false;
		} else {
			int i = random.nextInt(this.basePathWidth - 2) + 2;
			byte b0 = 1;

			for (int j = blockpos.getX() - i; j <= blockpos.getX() + i; ++j) {
				for (int k = blockpos.getZ() - i; k <= blockpos.getZ() + i; ++k) {
					int l = j - blockpos.getX();
					int i1 = k - blockpos.getZ();
					if (l * l + i1 * i1 <= i * i) {
						for (int j1 = blockpos.getY() - b0; j1 <= blockpos.getY() + b0; ++j1) {
							BlockPos blockpos1 = new BlockPos(j, j1, k);
							Block blockx = world.getBlockState(blockpos1).getBlock();
							if (blockx == Blocks.dirt || blockx == Blocks.snow || blockx == Blocks.ice) {
								world.setBlockState(blockpos1, this.block.getDefaultState(), 2);
							}
						}
					}
				}
			}

			return true;
		}
	}
}
