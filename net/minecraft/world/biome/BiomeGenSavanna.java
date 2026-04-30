package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;

public class BiomeGenSavanna extends BiomeGenBase {

	private static final WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);

	protected BiomeGenSavanna(int parInt1) {
		super(parInt1);
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 1, 2, 6));
		this.theBiomeDecorator.treesPerChunk = 1;
		this.theBiomeDecorator.flowersPerChunk = 4;
		this.theBiomeDecorator.grassPerChunk = 20;
	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom random) {
		return (WorldGenAbstractTree) (random.nextInt(5) > 0 ? field_150627_aC : this.worldGeneratorTrees);
	}

	protected BiomeGenBase createMutatedBiome(int i) {
		BiomeGenSavanna.Mutated biomegensavanna$mutated = new BiomeGenSavanna.Mutated(i, this);
		biomegensavanna$mutated.temperature = (this.temperature + 1.0F) * 0.5F;
		biomegensavanna$mutated.minHeight = this.minHeight * 0.5F + 0.3F;
		biomegensavanna$mutated.maxHeight = this.maxHeight * 0.5F + 1.2F;
		return biomegensavanna$mutated;
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

		for (int i = 0; i < 7; ++i) {
			int j = random.nextInt(16) + 8;
			int k = random.nextInt(16) + 8;
			int l = random.nextInt(world.getHeight(blockpos.add(j, 0, k)).getY() + 32);
			DOUBLE_PLANT_GENERATOR.generate(world, random, blockpos.add(j, l, k));
		}

		super.decorate(world, random, blockpos);
	}

	public static class Mutated extends BiomeGenMutated {
		public Mutated(int parInt1, BiomeGenBase parBiomeGenBase) {
			super(parInt1, parBiomeGenBase);
			this.theBiomeDecorator.treesPerChunk = 2;
			this.theBiomeDecorator.flowersPerChunk = 2;
			this.theBiomeDecorator.grassPerChunk = 5;
		}

		public void genTerrainBlocks(World world, EaglercraftRandom random, ChunkPrimer chunkprimer, int i, int j,
				double d0) {
			this.topBlock = Blocks.grass.getDefaultState();
			this.fillerBlock = Blocks.dirt.getDefaultState();
			if (d0 > 1.75D) {
				this.topBlock = Blocks.stone.getDefaultState();
				this.fillerBlock = Blocks.stone.getDefaultState();
			} else if (d0 > -0.5D) {
				this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT,
						BlockDirt.DirtType.COARSE_DIRT);
			}

			this.generateBiomeTerrain(world, random, chunkprimer, i, j, d0);
		}

		public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
			this.theBiomeDecorator.decorate(world, random, this, blockpos);
		}
	}
}
