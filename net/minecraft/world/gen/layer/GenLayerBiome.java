package net.minecraft.world.gen.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;

public class GenLayerBiome extends GenLayer {
	private BiomeGenBase[] field_151623_c = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.desert,
			BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.plains };
	private BiomeGenBase[] field_151621_d = new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.roofedForest,
			BiomeGenBase.extremeHills, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.swampland };
	private BiomeGenBase[] field_151622_e = new BiomeGenBase[] { BiomeGenBase.forest, BiomeGenBase.extremeHills,
			BiomeGenBase.taiga, BiomeGenBase.plains };
	private BiomeGenBase[] field_151620_f = new BiomeGenBase[] { BiomeGenBase.icePlains, BiomeGenBase.icePlains,
			BiomeGenBase.icePlains, BiomeGenBase.coldTaiga };
	private final ChunkProviderSettings field_175973_g;

	public GenLayerBiome(long parLong1, GenLayer parGenLayer, WorldType parWorldType, String parString1) {
		super(parLong1);
		this.parent = parGenLayer;
		if (parWorldType == WorldType.DEFAULT_1_1) {
			this.field_151623_c = new BiomeGenBase[] { BiomeGenBase.desert, BiomeGenBase.forest,
					BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga };
			this.field_175973_g = null;
		} else if (parWorldType == WorldType.CUSTOMIZED) {
			this.field_175973_g = ChunkProviderSettings.Factory.jsonToFactory(parString1).func_177864_b();
		} else {
			this.field_175973_g = null;
		}

	}

	public int[] getInts(int i, int j, int k, int l) {
		int[] aint = this.parent.getInts(i, j, k, l);
		int[] aint1 = IntCache.getIntCache(k * l);

		for (int i1 = 0; i1 < l; ++i1) {
			for (int j1 = 0; j1 < k; ++j1) {
				this.initChunkSeed((long) (j1 + i), (long) (i1 + j));
				int k1 = aint[j1 + i1 * k];
				int l1 = (k1 & 3840) >> 8;
				k1 = k1 & -3841;
				if (this.field_175973_g != null && this.field_175973_g.fixedBiome >= 0) {
					aint1[j1 + i1 * k] = this.field_175973_g.fixedBiome;
				} else if (isBiomeOceanic(k1)) {
					aint1[j1 + i1 * k] = k1;
				} else if (k1 == BiomeGenBase.mushroomIsland.biomeID) {
					aint1[j1 + i1 * k] = k1;
				} else if (k1 == 1) {
					if (l1 > 0) {
						if (this.nextInt(3) == 0) {
							aint1[j1 + i1 * k] = BiomeGenBase.mesaPlateau.biomeID;
						} else {
							aint1[j1 + i1 * k] = BiomeGenBase.mesaPlateau_F.biomeID;
						}
					} else {
						aint1[j1 + i1 * k] = this.field_151623_c[this.nextInt(this.field_151623_c.length)].biomeID;
					}
				} else if (k1 == 2) {
					if (l1 > 0) {
						aint1[j1 + i1 * k] = BiomeGenBase.jungle.biomeID;
					} else {
						aint1[j1 + i1 * k] = this.field_151621_d[this.nextInt(this.field_151621_d.length)].biomeID;
					}
				} else if (k1 == 3) {
					if (l1 > 0) {
						aint1[j1 + i1 * k] = BiomeGenBase.megaTaiga.biomeID;
					} else {
						aint1[j1 + i1 * k] = this.field_151622_e[this.nextInt(this.field_151622_e.length)].biomeID;
					}
				} else if (k1 == 4) {
					aint1[j1 + i1 * k] = this.field_151620_f[this.nextInt(this.field_151620_f.length)].biomeID;
				} else {
					aint1[j1 + i1 * k] = BiomeGenBase.mushroomIsland.biomeID;
				}
			}
		}

		return aint1;
	}
}
