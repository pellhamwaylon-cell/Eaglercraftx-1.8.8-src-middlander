package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockSilverfish;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;

public class BiomeGenHills extends BiomeGenBase {

	private WorldGenerator theWorldGenerator = new WorldGenMinable(
			Blocks.monster_egg.getDefaultState().withProperty(BlockSilverfish.VARIANT, BlockSilverfish.EnumType.STONE),
			9);
	private WorldGenTaiga2 field_150634_aD = new WorldGenTaiga2(false);
	private int field_150635_aE = 0;
	private int field_150636_aF = 1;
	private int field_150637_aG = 2;
	private int field_150638_aH;

	protected BiomeGenHills(int parInt1, boolean parFlag) {
		super(parInt1);
		this.field_150638_aH = this.field_150635_aE;
		if (parFlag) {
			this.theBiomeDecorator.treesPerChunk = 3;
			this.field_150638_aH = this.field_150636_aF;
		}

	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom random) {
		return (WorldGenAbstractTree) (random.nextInt(3) > 0 ? this.field_150634_aD : super.genBigTreeChance(random));
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		super.decorate(world, random, blockpos);
		int i = 3 + random.nextInt(6);

		for (int j = 0; j < i; ++j) {
			int k = random.nextInt(16);
			int l = random.nextInt(28) + 4;
			int i1 = random.nextInt(16);
			BlockPos blockpos1 = blockpos.add(k, l, i1);
			if (world.getBlockState(blockpos1).getBlock() == Blocks.stone) {
				world.setBlockState(blockpos1, Blocks.emerald_ore.getDefaultState(), 2);
			}
		}

		for (i = 0; i < 7; ++i) {
			int j1 = random.nextInt(16);
			int k1 = random.nextInt(64);
			int l1 = random.nextInt(16);
			this.theWorldGenerator.generate(world, random, blockpos.add(j1, k1, l1));
		}

	}

	public void genTerrainBlocks(World world, EaglercraftRandom random, ChunkPrimer chunkprimer, int i, int j,
			double d0) {
		this.topBlock = Blocks.grass.getDefaultState();
		this.fillerBlock = Blocks.dirt.getDefaultState();
		if ((d0 < -1.0D || d0 > 2.0D) && this.field_150638_aH == this.field_150637_aG) {
			this.topBlock = Blocks.gravel.getDefaultState();
			this.fillerBlock = Blocks.gravel.getDefaultState();
		} else if (d0 > 1.0D && this.field_150638_aH != this.field_150636_aF) {
			this.topBlock = Blocks.stone.getDefaultState();
			this.fillerBlock = Blocks.stone.getDefaultState();
		}

		this.generateBiomeTerrain(world, random, chunkprimer, i, j, d0);
	}

	private BiomeGenHills mutateHills(BiomeGenBase parBiomeGenBase) {
		this.field_150638_aH = this.field_150637_aG;
		this.func_150557_a(parBiomeGenBase.color, true);
		this.setBiomeName(parBiomeGenBase.biomeName + " M");
		this.setHeight(new BiomeGenBase.Height(parBiomeGenBase.minHeight, parBiomeGenBase.maxHeight));
		this.setTemperatureRainfall(parBiomeGenBase.temperature, parBiomeGenBase.rainfall);
		return this;
	}

	protected BiomeGenBase createMutatedBiome(int i) {
		return (new BiomeGenHills(i, false)).mutateHills(this);
	}
}
