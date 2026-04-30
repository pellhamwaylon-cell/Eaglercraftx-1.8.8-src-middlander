package net.minecraft.world.gen.layer;

public class GenLayerAddSnow extends GenLayer {
	public GenLayerAddSnow(long parLong1, GenLayer parGenLayer) {
		super(parLong1);
		this.parent = parGenLayer;
	}

	public int[] getInts(int i, int j, int k, int l) {
		int i1 = i - 1;
		int j1 = j - 1;
		int k1 = k + 2;
		int l1 = l + 2;
		int[] aint = this.parent.getInts(i1, j1, k1, l1);
		int[] aint1 = IntCache.getIntCache(k * l);

		for (int i2 = 0; i2 < l; ++i2) {
			for (int j2 = 0; j2 < k; ++j2) {
				int k2 = aint[j2 + 1 + (i2 + 1) * k1];
				this.initChunkSeed((long) (j2 + i), (long) (i2 + j));
				if (k2 == 0) {
					aint1[j2 + i2 * k] = 0;
				} else {
					int l2 = this.nextInt(6);
					if (l2 == 0) {
						l2 = 4;
					} else if (l2 <= 1) {
						l2 = 3;
					} else {
						l2 = 1;
					}

					aint1[j2 + i2 * k] = l2;
				}
			}
		}

		return aint1;
	}
}
