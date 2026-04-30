package net.minecraft.world.gen.layer;

public class GenLayerFuzzyZoom extends GenLayerZoom {
	public GenLayerFuzzyZoom(long parLong1, GenLayer parGenLayer) {
		super(parLong1, parGenLayer);
	}

	protected int selectModeOrRandom(int parInt1, int parInt2, int parInt3, int parInt4) {
		return this.selectRandom(new int[] { parInt1, parInt2, parInt3, parInt4 });
	}
}
