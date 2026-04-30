package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenDesertWells;

public class BiomeGenDesert extends BiomeGenBase {
	public BiomeGenDesert(int parInt1) {
		super(parInt1);
		this.spawnableCreatureList.clear();
		this.topBlock = Blocks.sand.getDefaultState();
		this.fillerBlock = Blocks.sand.getDefaultState();
		this.theBiomeDecorator.treesPerChunk = -999;
		this.theBiomeDecorator.deadBushPerChunk = 2;
		this.theBiomeDecorator.reedsPerChunk = 50;
		this.theBiomeDecorator.cactiPerChunk = 10;
		this.spawnableCreatureList.clear();
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		super.decorate(world, random, blockpos);
		if (random.nextInt(1000) == 0) {
			int i = random.nextInt(16) + 8;
			int j = random.nextInt(16) + 8;
			BlockPos blockpos1 = world.getHeight(blockpos.add(i, 0, j)).up();
			(new WorldGenDesertWells()).generate(world, random, blockpos1);
		}

	}
}
