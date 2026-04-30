package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import com.google.common.collect.Lists;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class BiomeGenMutated extends BiomeGenBase {
	protected BiomeGenBase baseBiome;

	public BiomeGenMutated(int id, BiomeGenBase biome) {
		super(id);
		this.baseBiome = biome;
		this.func_150557_a(biome.color, true);
		this.biomeName = biome.biomeName + " M";
		this.topBlock = biome.topBlock;
		this.fillerBlock = biome.fillerBlock;
		this.fillerBlockMetadata = biome.fillerBlockMetadata;
		this.minHeight = biome.minHeight;
		this.maxHeight = biome.maxHeight;
		this.temperature = biome.temperature;
		this.rainfall = biome.rainfall;
		this.waterColorMultiplier = biome.waterColorMultiplier;
		this.enableSnow = biome.enableSnow;
		this.enableRain = biome.enableRain;
		this.spawnableCreatureList = Lists.newArrayList(biome.spawnableCreatureList);
		this.spawnableMonsterList = Lists.newArrayList(biome.spawnableMonsterList);
		this.spawnableCaveCreatureList = Lists.newArrayList(biome.spawnableCaveCreatureList);
		this.spawnableWaterCreatureList = Lists.newArrayList(biome.spawnableWaterCreatureList);
		this.temperature = biome.temperature;
		this.rainfall = biome.rainfall;
		this.minHeight = biome.minHeight + 0.1F;
		this.maxHeight = biome.maxHeight + 0.2F;
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		this.baseBiome.theBiomeDecorator.decorate(world, random, this, blockpos);
	}

	public void genTerrainBlocks(World world, EaglercraftRandom random, ChunkPrimer chunkprimer, int i, int j,
			double d0) {
		this.baseBiome.genTerrainBlocks(world, random, chunkprimer, i, j, d0);
	}

	public float getSpawningChance() {
		return this.baseBiome.getSpawningChance();
	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom random) {
		return this.baseBiome.genBigTreeChance(random);
	}

	public int getFoliageColorAtPos(BlockPos blockpos) {
		return this.baseBiome.getFoliageColorAtPos(blockpos);
	}

	public int getGrassColorAtPos(BlockPos blockpos) {
		return this.baseBiome.getGrassColorAtPos(blockpos);
	}

	public Class<? extends BiomeGenBase> getBiomeClass() {
		return this.baseBiome.getBiomeClass();
	}

	public boolean isEqualTo(BiomeGenBase biomegenbase) {
		return this.baseBiome.isEqualTo(biomegenbase);
	}

	public BiomeGenBase.TempCategory getTempCategory() {
		return this.baseBiome.getTempCategory();
	}
}
