package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenSwamp extends BiomeGenBase {
	protected BiomeGenSwamp(int parInt1) {
		super(parInt1);
		this.theBiomeDecorator.treesPerChunk = 2;
		this.theBiomeDecorator.flowersPerChunk = 1;
		this.theBiomeDecorator.deadBushPerChunk = 1;
		this.theBiomeDecorator.mushroomsPerChunk = 8;
		this.theBiomeDecorator.reedsPerChunk = 10;
		this.theBiomeDecorator.clayPerChunk = 1;
		this.theBiomeDecorator.waterlilyPerChunk = 4;
		this.theBiomeDecorator.sandPerChunk2 = 0;
		this.theBiomeDecorator.sandPerChunk = 0;
		this.theBiomeDecorator.grassPerChunk = 5;
		this.waterColorMultiplier = 14745518;
		this.spawnableMonsterList.add(new BiomeGenBase.SpawnListEntry(EntitySlime.class, 1, 1, 1));
	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom var1) {
		return this.worldGeneratorSwamp;
	}

	public int getGrassColorAtPos(BlockPos blockpos) {
		double d0 = GRASS_COLOR_NOISE.func_151601_a((double) blockpos.getX() * 0.0225D,
				(double) blockpos.getZ() * 0.0225D);
		return d0 < -0.1D ? 5011004 : 6975545;
	}

	public int getFoliageColorAtPos(BlockPos var1) {
		return 6975545;
	}

	public BlockFlower.EnumFlowerType pickRandomFlower(EaglercraftRandom var1, BlockPos var2) {
		return BlockFlower.EnumFlowerType.BLUE_ORCHID;
	}

	public void genTerrainBlocks(World world, EaglercraftRandom random, ChunkPrimer chunkprimer, int i, int j,
			double d0) {
		double d1 = GRASS_COLOR_NOISE.func_151601_a((double) i * 0.25D, (double) j * 0.25D);
		if (d1 > 0.0D) {
			int k = i & 15;
			int l = j & 15;

			for (int i1 = 255; i1 >= 0; --i1) {
				if (chunkprimer.getBlockState(l, i1, k).getBlock().getMaterial() != Material.air) {
					if (i1 == 62 && chunkprimer.getBlockState(l, i1, k).getBlock() != Blocks.water) {
						chunkprimer.setBlockState(l, i1, k, Blocks.water.getDefaultState());
						if (d1 < 0.12D) {
							chunkprimer.setBlockState(l, i1 + 1, k, Blocks.waterlily.getDefaultState());
						}
					}
					break;
				}
			}
		}

		this.generateBiomeTerrain(world, random, chunkprimer, i, j, d0);
	}
}
