package net.minecraft.world.biome;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.feature.WorldGenIceSpike;
import net.minecraft.world.gen.feature.WorldGenTaiga2;

public class BiomeGenSnow extends BiomeGenBase {
	private boolean field_150615_aC;
	private WorldGenIceSpike field_150616_aD = new WorldGenIceSpike();
	private WorldGenIcePath field_150617_aE = new WorldGenIcePath(4);

	public BiomeGenSnow(int parInt1, boolean parFlag) {
		super(parInt1);
		this.field_150615_aC = parFlag;
		if (parFlag) {
			this.topBlock = Blocks.snow.getDefaultState();
		}

		this.spawnableCreatureList.clear();
	}

	public void decorate(World world, EaglercraftRandom random, BlockPos blockpos) {
		if (this.field_150615_aC) {
			for (int i = 0; i < 3; ++i) {
				int j = random.nextInt(16) + 8;
				int k = random.nextInt(16) + 8;
				this.field_150616_aD.generate(world, random, world.getHeight(blockpos.add(j, 0, k)));
			}

			for (int l = 0; l < 2; ++l) {
				int i1 = random.nextInt(16) + 8;
				int j1 = random.nextInt(16) + 8;
				this.field_150617_aE.generate(world, random, world.getHeight(blockpos.add(i1, 0, j1)));
			}
		}

		super.decorate(world, random, blockpos);
	}

	public WorldGenAbstractTree genBigTreeChance(EaglercraftRandom var1) {
		return new WorldGenTaiga2(false);
	}

	protected BiomeGenBase createMutatedBiome(int i) {
		BiomeGenBase biomegenbase = (new BiomeGenSnow(i, true)).func_150557_a(13828095, true)
				.setBiomeName(this.biomeName + " Spikes").setEnableSnow().setTemperatureRainfall(0.0F, 0.5F)
				.setHeight(new BiomeGenBase.Height(this.minHeight + 0.1F, this.maxHeight + 0.1F));
		biomegenbase.minHeight = this.minHeight + 0.3F;
		biomegenbase.maxHeight = this.maxHeight + 0.4F;
		return biomegenbase;
	}
}
