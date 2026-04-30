package net.minecraft.util;

public enum EnumWorldBlockLayer {
	SOLID("Solid"), CUTOUT_MIPPED("Mipped Cutout"), CUTOUT("Cutout"), TRANSLUCENT("Translucent"),
	REALISTIC_WATER("EaglerShaderWater"), GLASS_HIGHLIGHTS("EaglerShaderGlassHighlights");

	public static final EnumWorldBlockLayer[] _VALUES = values();

	private final String layerName;

	private EnumWorldBlockLayer(String layerNameIn) {
		this.layerName = layerNameIn;
	}

	public String toString() {
		return this.layerName;
	}
}
