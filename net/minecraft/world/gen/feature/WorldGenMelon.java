package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenMelon extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (Blocks.melon_block.canPlaceBlockAt(world, blockpos1)
					&& world.getBlockState(blockpos1.down()).getBlock() == Blocks.grass) {
				world.setBlockState(blockpos1, Blocks.melon_block.getDefaultState(), 2);
			}
		}

		return true;
	}
}
