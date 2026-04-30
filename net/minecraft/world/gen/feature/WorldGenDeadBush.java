package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenDeadBush extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		Block block;
		while (((block = world.getBlockState(blockpos).getBlock()).getMaterial() == Material.air
				|| block.getMaterial() == Material.leaves) && blockpos.getY() > 0) {
			blockpos = blockpos.down();
		}

		for (int i = 0; i < 4; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos1)
					&& Blocks.deadbush.canBlockStay(world, blockpos1, Blocks.deadbush.getDefaultState())) {
				world.setBlockState(blockpos1, Blocks.deadbush.getDefaultState(), 2);
			}
		}

		return true;
	}
}
