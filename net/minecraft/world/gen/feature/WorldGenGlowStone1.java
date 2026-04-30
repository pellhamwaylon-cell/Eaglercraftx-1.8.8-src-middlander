package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenGlowStone1 extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		BlockPos tmp;
		if (!world.isAirBlock(blockpos)) {
			return false;
		} else if (world.getBlockState(blockpos.offsetEvenFaster(EnumFacing.UP, tmp = new BlockPos(0, 0, 0)))
				.getBlock() != Blocks.netherrack) {
			return false;
		} else {
			world.setBlockState(blockpos, Blocks.glowstone.getDefaultState(), 2);

			for (int i = 0; i < 1500; ++i) {
				BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8), -random.nextInt(12),
						random.nextInt(8) - random.nextInt(8));
				if (world.getBlockState(blockpos1).getBlock().getMaterial() == Material.air) {
					int j = 0;

					EnumFacing[] facings = EnumFacing._VALUES;
					for (int k = 0; k < facings.length; ++k) {
						if (world.getBlockState(blockpos1.offsetEvenFaster(facings[k], tmp))
								.getBlock() == Blocks.glowstone) {
							++j;
						}

						if (j > 1) {
							break;
						}
					}

					if (j == 1) {
						world.setBlockState(blockpos1, Blocks.glowstone.getDefaultState(), 2);
					}
				}
			}

			return true;
		}
	}
}
