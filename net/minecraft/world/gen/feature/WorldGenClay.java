package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenClay extends WorldGenerator {
	private Block field_150546_a = Blocks.clay;
	private int numberOfBlocks;

	public WorldGenClay(int parInt1) {
		this.numberOfBlocks = parInt1;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (world.getBlockState(blockpos).getBlock().getMaterial() != Material.water) {
			return false;
		} else {
			int i = random.nextInt(this.numberOfBlocks - 2) + 2;
			byte b0 = 1;

			for (int j = blockpos.getX() - i; j <= blockpos.getX() + i; ++j) {
				for (int k = blockpos.getZ() - i; k <= blockpos.getZ() + i; ++k) {
					int l = j - blockpos.getX();
					int i1 = k - blockpos.getZ();
					if (l * l + i1 * i1 <= i * i) {
						for (int j1 = blockpos.getY() - b0; j1 <= blockpos.getY() + b0; ++j1) {
							BlockPos blockpos1 = new BlockPos(j, j1, k);
							Block block = world.getBlockState(blockpos1).getBlock();
							if (block == Blocks.dirt || block == Blocks.clay) {
								world.setBlockState(blockpos1, this.field_150546_a.getDefaultState(), 2);
							}
						}
					}
				}
			}

			return true;
		}
	}
}
