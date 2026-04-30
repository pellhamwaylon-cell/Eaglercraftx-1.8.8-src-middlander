package net.minecraft.world;

public class ColorizerFoliage {
	private static int[] foliageBuffer = new int[65536];

	public static void setFoliageBiomeColorizer(int[] parArrayOfInt) {
		foliageBuffer = parArrayOfInt;
	}

	public static int getFoliageColor(double parDouble1, double parDouble2) {
		parDouble2 = parDouble2 * parDouble1;
		int i = (int) ((1.0D - parDouble1) * 255.0D);
		int j = (int) ((1.0D - parDouble2) * 255.0D);
		return foliageBuffer[j << 8 | i];
	}

	public static int getFoliageColorPine() {
		return 6396257;
	}

	public static int getFoliageColorBirch() {
		return 8431445;
	}

	public static int getFoliageColorBasic() {
		return 4764952;
	}
}
