package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockVine;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class WorldGenVines extends WorldGenerator {
	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (; blockpos.getY() < 128; blockpos = blockpos.up()) {
			if (world.isAirBlock(blockpos)) {
				EnumFacing[] facings = EnumFacing.Plane.HORIZONTAL.facingsArray;
				for (int i = 0; i < facings.length; ++i) {
					EnumFacing enumfacing = facings[i];
					if (Blocks.vine.canPlaceBlockOnSide(world, blockpos, enumfacing)) {
						IBlockState iblockstate = Blocks.vine.getDefaultState()
								.withProperty(BlockVine.NORTH, Boolean.valueOf(enumfacing == EnumFacing.NORTH))
								.withProperty(BlockVine.EAST, Boolean.valueOf(enumfacing == EnumFacing.EAST))
								.withProperty(BlockVine.SOUTH, Boolean.valueOf(enumfacing == EnumFacing.SOUTH))
								.withProperty(BlockVine.WEST, Boolean.valueOf(enumfacing == EnumFacing.WEST));
						world.setBlockState(blockpos, iblockstate, 2);
						break;
					}
				}
			} else {
				blockpos = blockpos.add(random.nextInt(4) - random.nextInt(4), 0,
						random.nextInt(4) - random.nextInt(4));
			}
		}

		return true;
	}
}
