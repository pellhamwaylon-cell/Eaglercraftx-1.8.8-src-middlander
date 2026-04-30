package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenHellLava extends WorldGenerator {
	private final Block field_150553_a;
	private final boolean field_94524_b;

	public WorldGenHellLava(Block parBlock, boolean parFlag) {
		this.field_150553_a = parBlock;
		this.field_94524_b = parFlag;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (world.getBlockState(blockpos.up()).getBlock() != Blocks.netherrack) {
			return false;
		} else if (world.getBlockState(blockpos).getBlock().getMaterial() != Material.air
				&& world.getBlockState(blockpos).getBlock() != Blocks.netherrack) {
			return false;
		} else {
			int i = 0;
			if (world.getBlockState(blockpos.west()).getBlock() == Blocks.netherrack) {
				++i;
			}

			if (world.getBlockState(blockpos.east()).getBlock() == Blocks.netherrack) {
				++i;
			}

			if (world.getBlockState(blockpos.north()).getBlock() == Blocks.netherrack) {
				++i;
			}

			if (world.getBlockState(blockpos.south()).getBlock() == Blocks.netherrack) {
				++i;
			}

			if (world.getBlockState(blockpos.down()).getBlock() == Blocks.netherrack) {
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

			if (world.isAirBlock(blockpos.down())) {
				++j;
			}

			if (!this.field_94524_b && i == 4 && j == 1 || i == 5) {
				world.setBlockState(blockpos, this.field_150553_a.getDefaultState(), 2);
				world.forceBlockUpdateTick(this.field_150553_a, blockpos, random);
			}

			return true;
		}
	}
}
