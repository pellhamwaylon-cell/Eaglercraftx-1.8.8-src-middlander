package net.minecraft.world;

public class ColorizerGrass {
	private static int[] grassBuffer = new int[65536];

	public static void setGrassBiomeColorizer(int[] parArrayOfInt) {
		grassBuffer = parArrayOfInt;
	}

	public static int getGrassColor(double parDouble1, double parDouble2) {
		parDouble2 = parDouble2 * parDouble1;
		int i = (int) ((1.0D - parDouble1) * 255.0D);
		int j = (int) ((1.0D - parDouble2) * 255.0D);
		int k = j << 8 | i;
		return k > grassBuffer.length ? -65281 : grassBuffer[k];
	}
}
