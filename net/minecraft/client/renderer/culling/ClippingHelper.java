package net.minecraft.client.renderer.culling;

public class ClippingHelper {
	public float[][] frustum = new float[6][4];
	public float[] projectionMatrix = new float[16];
	public float[] modelviewMatrix = new float[16];
	public float[] clippingMatrix = new float[16];

	private double dot(float[] parArrayOfFloat, double parDouble1, double parDouble2, double parDouble3) {
		return (double) parArrayOfFloat[0] * parDouble1 + (double) parArrayOfFloat[1] * parDouble2
				+ (double) parArrayOfFloat[2] * parDouble3 + (double) parArrayOfFloat[3];
	}

	public boolean isBoxInFrustum(double parDouble1, double parDouble2, double parDouble3, double parDouble4,
			double parDouble5, double parDouble6) {
		for (int i = 0; i < 6; ++i) {
			float[] afloat = this.frustum[i];
			if (this.dot(afloat, parDouble1, parDouble2, parDouble3) <= 0.0D
					&& this.dot(afloat, parDouble4, parDouble2, parDouble3) <= 0.0D
					&& this.dot(afloat, parDouble1, parDouble5, parDouble3) <= 0.0D
					&& this.dot(afloat, parDouble4, parDouble5, parDouble3) <= 0.0D
					&& this.dot(afloat, parDouble1, parDouble2, parDouble6) <= 0.0D
					&& this.dot(afloat, parDouble4, parDouble2, parDouble6) <= 0.0D
					&& this.dot(afloat, parDouble1, parDouble5, parDouble6) <= 0.0D
					&& this.dot(afloat, parDouble4, parDouble5, parDouble6) <= 0.0D) {
				return false;
			}
		}

		return true;
	}
}
