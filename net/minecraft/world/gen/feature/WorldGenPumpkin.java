package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockPumpkin;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenPumpkin extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos1) && world.getBlockState(blockpos1.down()).getBlock() == Blocks.grass
					&& Blocks.pumpkin.canPlaceBlockAt(world, blockpos1)) {
				world.setBlockState(blockpos1, Blocks.pumpkin.getDefaultState().withProperty(BlockPumpkin.FACING,
						EnumFacing.Plane.HORIZONTAL.random(random)), 2);
			}
		}

		return true;
	}
}
