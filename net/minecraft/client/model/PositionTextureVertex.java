package net.minecraft.client.model;

import net.minecraft.util.Vec3;

public class PositionTextureVertex {
	public Vec3 vector3D;
	public float texturePositionX;
	public float texturePositionY;

	public PositionTextureVertex(float parFloat1, float parFloat2, float parFloat3, float parFloat4, float parFloat5) {
		this(new Vec3((double) parFloat1, (double) parFloat2, (double) parFloat3), parFloat4, parFloat5);
	}

	public PositionTextureVertex setTexturePosition(float parFloat1, float parFloat2) {
		return new PositionTextureVertex(this, parFloat1, parFloat2);
	}

	public PositionTextureVertex(PositionTextureVertex textureVertex, float texturePositionXIn,
			float texturePositionYIn) {
		this.vector3D = textureVertex.vector3D;
		this.texturePositionX = texturePositionXIn;
		this.texturePositionY = texturePositionYIn;
	}

	public PositionTextureVertex(Vec3 vector3DIn, float texturePositionXIn, float texturePositionYIn) {
		this.vector3D = vector3DIn;
		this.texturePositionX = texturePositionXIn;
		this.texturePositionY = texturePositionYIn;
	}
}
