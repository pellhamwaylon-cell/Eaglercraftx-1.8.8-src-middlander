package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenReed extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 20; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(4) - random.nextInt(4), 0,
					random.nextInt(4) - random.nextInt(4));
			if (world.isAirBlock(blockpos1)) {
				BlockPos blockpos2 = blockpos1.down();
				if (world.getBlockState(blockpos2.west()).getBlock().getMaterial() == Material.water
						|| world.getBlockState(blockpos2.east()).getBlock().getMaterial() == Material.water
						|| world.getBlockState(blockpos2.north()).getBlock().getMaterial() == Material.water
						|| world.getBlockState(blockpos2.south()).getBlock().getMaterial() == Material.water) {
					int j = 2 + random.nextInt(random.nextInt(3) + 1);

					for (int k = 0; k < j; ++k) {
						if (Blocks.reeds.canBlockStay(world, blockpos1)) {
							world.setBlockState(blockpos1.up(k), Blocks.reeds.getDefaultState(), 2);
						}
					}
				}
			}
		}

		return true;
	}
}
