package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenWaterlily extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 10; ++i) {
			int j = blockpos.getX() + random.nextInt(8) - random.nextInt(8);
			int k = blockpos.getY() + random.nextInt(4) - random.nextInt(4);
			int l = blockpos.getZ() + random.nextInt(8) - random.nextInt(8);
			if (world.isAirBlock(new BlockPos(j, k, l))
					&& Blocks.waterlily.canPlaceBlockAt(world, new BlockPos(j, k, l))) {
				world.setBlockState(new BlockPos(j, k, l), Blocks.waterlily.getDefaultState(), 2);
			}
		}

		return true;
	}
}
