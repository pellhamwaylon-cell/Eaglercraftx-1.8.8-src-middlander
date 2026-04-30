package net.minecraft.world.gen.layer;

import net.minecraft.world.biome.BiomeGenBase;

public class GenLayerRiverMix extends GenLayer {
	private GenLayer biomePatternGeneratorChain;
	private GenLayer riverPatternGeneratorChain;

	public GenLayerRiverMix(long parLong1, GenLayer parGenLayer, GenLayer parGenLayer2) {
		super(parLong1);
		this.biomePatternGeneratorChain = parGenLayer;
		this.riverPatternGeneratorChain = parGenLayer2;
	}

	public void initWorldGenSeed(long i) {
		this.biomePatternGeneratorChain.initWorldGenSeed(i);
		this.riverPatternGeneratorChain.initWorldGenSeed(i);
		super.initWorldGenSeed(i);
	}

	public int[] getInts(int i, int j, int k, int l) {
		int[] aint = this.biomePatternGeneratorChain.getInts(i, j, k, l);
		int[] aint1 = this.riverPatternGeneratorChain.getInts(i, j, k, l);
		int[] aint2 = IntCache.getIntCache(k * l);

		for (int i1 = 0; i1 < k * l; ++i1) {
			if (aint[i1] != BiomeGenBase.ocean.biomeID && aint[i1] != BiomeGenBase.deepOcean.biomeID) {
				if (aint1[i1] == BiomeGenBase.river.biomeID) {
					if (aint[i1] == BiomeGenBase.icePlains.biomeID) {
						aint2[i1] = BiomeGenBase.frozenRiver.biomeID;
					} else if (aint[i1] != BiomeGenBase.mushroomIsland.biomeID
							&& aint[i1] != BiomeGenBase.mushroomIslandShore.biomeID) {
						aint2[i1] = aint1[i1] & 255;
					} else {
						aint2[i1] = BiomeGenBase.mushroomIslandShore.biomeID;
					}
				} else {
					aint2[i1] = aint[i1];
				}
			} else {
				aint2[i1] = aint[i1];
			}
		}

		return aint2;
	}
}
