package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenLiquids extends WorldGenerator {
	private Block block;

	public WorldGenLiquids(Block parBlock) {
		this.block = parBlock;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (world.getBlockState(blockpos.up()).getBlock() != Blocks.stone) {
			return false;
		} else if (world.getBlockState(blockpos.down()).getBlock() != Blocks.stone) {
			return false;
		} else if (world.getBlockState(blockpos).getBlock().getMaterial() != Material.air
				&& world.getBlockState(blockpos).getBlock() != Blocks.stone) {
			return false;
		} else {
			int i = 0;
			if (world.getBlockState(blockpos.west()).getBlock() == Blocks.stone) {
				++i;
			}

			if (world.getBlockState(blockpos.east()).getBlock() == Blocks.stone) {
				++i;
			}

			if (world.getBlockState(blockpos.north()).getBlock() == Blocks.stone) {
				++i;
			}

			if (world.getBlockState(blockpos.south()).getBlock() == Blocks.stone) {
				++i;
			}

			int j = 0;
			if (world.isAirBlock(blockpos.west())) {
				++j;
			}

			if (world.isAirBlock(blockpos.east())) {
				++j;
			}

			if (world.isAirBlock(blockpos.north())) {
				++j;
			}

			if (world.isAirBlock(blockpos.south())) {
				++j;
			}

			if (i == 3 && j == 1) {
				world.setBlockState(blockpos, this.block.getDefaultState(), 2);
				world.forceBlockUpdateTick(this.block, blockpos, random);
			}

			return true;
		}
	}
}
