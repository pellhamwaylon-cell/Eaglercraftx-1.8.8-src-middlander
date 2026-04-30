package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenSpikes extends WorldGenerator {
	private Block baseBlockRequired;

	public WorldGenSpikes(Block parBlock) {
		this.baseBlockRequired = parBlock;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (world.isAirBlock(blockpos) && world.getBlockState(blockpos.down()).getBlock() == this.baseBlockRequired) {
			int i = random.nextInt(32) + 6;
			int j = random.nextInt(4) + 1;
			BlockPos blockpos$mutableblockpos = new BlockPos();

			for (int k = blockpos.getX() - j; k <= blockpos.getX() + j; ++k) {
				for (int l = blockpos.getZ() - j; l <= blockpos.getZ() + j; ++l) {
					int i1 = k - blockpos.getX();
					int j1 = l - blockpos.getZ();
					if (i1 * i1 + j1 * j1 <= j * j + 1
							&& world.getBlockState(blockpos$mutableblockpos.func_181079_c(k, blockpos.getY() - 1, l))
									.getBlock() != this.baseBlockRequired) {
						return false;
					}
				}
			}

			for (int l1 = blockpos.getY(); l1 < blockpos.getY() + i && l1 < 256; ++l1) {
				for (int i2 = blockpos.getX() - j; i2 <= blockpos.getX() + j; ++i2) {
					for (int j2 = blockpos.getZ() - j; j2 <= blockpos.getZ() + j; ++j2) {
						int k2 = i2 - blockpos.getX();
						int k1 = j2 - blockpos.getZ();
						if (k2 * k2 + k1 * k1 <= j * j + 1) {
							world.setBlockState(new BlockPos(i2, l1, j2), Blocks.obsidian.getDefaultState(), 2);
						}
					}
				}
			}

			EntityEnderCrystal entityendercrystal = new EntityEnderCrystal(world);
			entityendercrystal.setLocationAndAngles((double) ((float) blockpos.getX() + 0.5F),
					(double) (blockpos.getY() + i), (double) ((float) blockpos.getZ() + 0.5F),
					random.nextFloat() * 360.0F, 0.0F);
			world.spawnEntityInWorld(entityendercrystal);
			world.setBlockState(blockpos.up(i), Blocks.bedrock.getDefaultState(), 2);
			return true;
		} else {
			return false;
		}
	}
}
