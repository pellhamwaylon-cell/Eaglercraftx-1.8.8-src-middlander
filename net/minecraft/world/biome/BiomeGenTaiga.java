package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenTaiga extends BiomeGenBase {

	private static final WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
	private static final WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
	private static final WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
	private static final WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
	private static final WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
	private int field_150644_aH;

	public BiomeGenTaiga(int parInt1, int parInt2) {
		super(parInt1);
		this.field_150644_aH = parInt2;
		this.spawnableCreatureList.add(new BiomeGenBase.SpawnListEntry(EntityWolf.class, 8, 4, 4));
		this.theBiomeDecorator.treesPerChunk = 10;
		if (parInt2 != 1 && parInt2 != 2) {
			this.theBiomeDecorator.grassPerChunk = 1;
			this.theBiomeDecorator.mushroomsPerChunk = 1;
		} else {
			this.theBiomeDecorator.grassPerChunk = 7;
			this.theBiomeDecorator.deadBushPerChunk = 1;
			this.theBiomeDecorator.mushroomsPerChunk = 3;
		}

	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom random) {
		return (WorldGenAbstractTree) ((this.field_150644_aH == 1 || this.field_150644_aH == 2)
				&& random.nextInt(3) == 0
						? (this.field_150644_aH != 2 && random.nextInt(13) != 0 ? field_150641_aE : field_150642_aF)
						: (random.nextInt(3) == 0 ? field_150639_aC : field_150640_aD));
	}

	public WorldGenerator getRandomWorldGenForGrass(EaglercraftRandom random) {
		return random.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass.EnumType.FERN)
				: new WorldGenTallGrass(BlockTallGrass.EnumType.GRASS);
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
			int i = random.nextInt(3);

			for (int j = 0; j < i; ++j) {
				int k = random.nextInt(16) + 8;
				int l = random.nextInt(16) + 8;
				BlockPos blockpos1 = world.getHeight(blockpos.add(k, 0, l));
				field_150643_aG.generate(world, random, blockpos1);
			}
		}

		DOUBLE_PLANT_GENERATOR.setPlantType(BlockDoublePlant.EnumPlantType.FERN);

		for (int i1 = 0; i1 < 7; ++i1) {
			int j1 = random.nextInt(16) + 8;
			int k1 = random.nextInt(16) + 8;
			int l1 = random.nextInt(world.getHeight(blockpos.add(j1, 0, k1)).getY() + 32);
			DOUBLE_PLANT_GENERATOR.generate(world, random, blockpos.add(j1, l1, k1));
		}

		super.decorate(world, random, blockpos);
	}

	public void genTerrainBlocks(World world, EaglercraftRandom random, ChunkPrimer chunkprimer, int i, int j,
			double d0) {
		if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
			this.topBlock = Blocks.grass.getDefaultState();
			this.fillerBlock = Blocks.dirt.getDefaultState();
			if (d0 > 1.75D) {
				this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT,
						BlockDirt.DirtType.COARSE_DIRT);
			} else if (d0 > -0.95D) {
				this.topBlock = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT,
						BlockDirt.DirtType.PODZOL);
			}
		}

		this.generateBiomeTerrain(world, random, chunkprimer, i, j, d0);
	}

	protected BiomeGenBase createMutatedBiome(int i) {
		return this.biomeID == BiomeGenBase.megaTaiga.biomeID ? (new BiomeGenTaiga(i, 2)).func_150557_a(5858897, true)
				.setBiomeName("Mega Spruce Taiga").setFillerBlockMetadata(5159473).setTemperatureRainfall(0.25F, 0.8F)
				.setHeight(new BiomeGenBase.Height(this.minHeight, this.maxHeight)) : super.createMutatedBiome(i);
	}
}
