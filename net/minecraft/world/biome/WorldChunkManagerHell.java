package net.minecraft.world.biome;

import java.util.Arrays;
import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.util.BlockPos;

public class WorldChunkManagerHell extends WorldChunkManager {
	private BiomeGenBase biomeGenerator;
	private float rainfall;

	public WorldChunkManagerHell(BiomeGenBase parBiomeGenBase, float parFloat1) {
		this.biomeGenerator = parBiomeGenBase;
		this.rainfall = parFloat1;
	}

	public BiomeGenBase getBiomeGenerator(BlockPos var1) {
		return this.biomeGenerator;
	}

	public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] abiomegenbase, int var2, int var3, int i, int j) {
		if (abiomegenbase == null || abiomegenbase.length < i * j) {
			abiomegenbase = new BiomeGenBase[i * j];
		}

		Arrays.fill(abiomegenbase, 0, i * j, this.biomeGenerator);
		return abiomegenbase;
	}

	public float[] getRainfall(float[] afloat, int var2, int var3, int i, int j) {
		if (afloat == null || afloat.length < i * j) {
			afloat = new float[i * j];
		}

		Arrays.fill(afloat, 0, i * j, this.rainfall);
		return afloat;
	}

	public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] abiomegenbase, int var2, int var3, int i, int j) {
		if (abiomegenbase == null || abiomegenbase.length < i * j) {
			abiomegenbase = new BiomeGenBase[i * j];
		}

		Arrays.fill(abiomegenbase, 0, i * j, this.biomeGenerator);
		return abiomegenbase;
	}

	public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] abiomegenbase, int i, int j, int k, int l, boolean var6) {
		return this.loadBlockGeneratorData(abiomegenbase, i, j, k, l);
	}

	public BlockPos findBiomePosition(int i, int j, int k, List<BiomeGenBase> list, EaglercraftRandom random) {
		return list.contains(this.biomeGenerator)
				? new BlockPos(i - k + random.nextInt(k * 2 + 1), 0, j - k + random.nextInt(k * 2 + 1))
				: null;
	}

	public boolean areBiomesViable(int var1, int var2, int var3, List<BiomeGenBase> list) {
		return list.contains(this.biomeGenerator);
	}
}
