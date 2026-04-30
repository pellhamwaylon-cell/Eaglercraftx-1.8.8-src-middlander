package net.minecraft.util;

public class Matrix4f extends net.lax1dude.eaglercraft.v1_8.vector.Matrix4f {
	public Matrix4f(float[] parArrayOfFloat) {
		this.m00 = parArrayOfFloat[0];
		this.m01 = parArrayOfFloat[1];
		this.m02 = parArrayOfFloat[2];
		this.m03 = parArrayOfFloat[3];
		this.m10 = parArrayOfFloat[4];
		this.m11 = parArrayOfFloat[5];
		this.m12 = parArrayOfFloat[6];
		this.m13 = parArrayOfFloat[7];
		this.m20 = parArrayOfFloat[8];
		this.m21 = parArrayOfFloat[9];
		this.m22 = parArrayOfFloat[10];
		this.m23 = parArrayOfFloat[11];
		this.m30 = parArrayOfFloat[12];
		this.m31 = parArrayOfFloat[13];
		this.m32 = parArrayOfFloat[14];
		this.m33 = parArrayOfFloat[15];
	}

	public Matrix4f() {
		this.m00 = this.m01 = this.m02 = this.m03 = this.m10 = this.m11 = this.m12 = this.m13 = this.m20 = this.m21 = this.m22 = this.m23 = this.m30 = this.m31 = this.m32 = this.m33 = 0.0F;
	}
}
