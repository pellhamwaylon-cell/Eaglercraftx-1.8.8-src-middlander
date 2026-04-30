package net.minecraft.world.gen;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.util.MathHelper;

public class NoiseGeneratorOctaves extends NoiseGenerator {
	private NoiseGeneratorImproved[] generatorCollection;
	private int octaves;

	public NoiseGeneratorOctaves(EaglercraftRandom parRandom, int parInt1) {
		this.octaves = parInt1;
		this.generatorCollection = new NoiseGeneratorImproved[parInt1];

		for (int i = 0; i < parInt1; ++i) {
			this.generatorCollection[i] = new NoiseGeneratorImproved(parRandom);
		}

	}

	public double[] generateNoiseOctaves(double[] parArrayOfDouble, int parInt1, int parInt2, int parInt3, int parInt4,
			int parInt5, int parInt6, double parDouble1, double parDouble2, double parDouble3) {
		if (parArrayOfDouble == null) {
			parArrayOfDouble = new double[parInt4 * parInt5 * parInt6];
		} else {
			for (int i = 0; i < parArrayOfDouble.length; ++i) {
				parArrayOfDouble[i] = 0.0D;
			}
		}

		double d3 = 1.0D;

		for (int j = 0; j < this.octaves; ++j) {
			double d0 = (double) parInt1 * d3 * parDouble1;
			double d1 = (double) parInt2 * d3 * parDouble2;
			double d2 = (double) parInt3 * d3 * parDouble3;
			long k = MathHelper.floor_double_long(d0);
			long l = MathHelper.floor_double_long(d2);
			d0 = d0 - (double) k;
			d2 = d2 - (double) l;
			k = k % 16777216L;
			l = l % 16777216L;
			d0 = d0 + (double) k;
			d2 = d2 + (double) l;
			this.generatorCollection[j].populateNoiseArray(parArrayOfDouble, d0, d1, d2, parInt4, parInt5, parInt6,
					parDouble1 * d3, parDouble2 * d3, parDouble3 * d3, d3);
			d3 /= 2.0D;
		}

		return parArrayOfDouble;
	}

	public double[] generateNoiseOctaves(double[] parArrayOfDouble, int parInt1, int parInt2, int parInt3, int parInt4,
			double parDouble1, double parDouble2, double parDouble3) {
		return this.generateNoiseOctaves(parArrayOfDouble, parInt1, 10, parInt2, parInt3, 1, parInt4, parDouble1, 1.0D,
				parDouble2);
	}
}
