package net.minecraft.client.renderer.block.model;

import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.optifine.model.QuadBounds;

public class BakedQuad {
	protected final int[] vertexData;
	protected final int[] vertexDataWithNormals;
	protected final int tintIndex;
	protected final EnumFacing face;
	protected final EaglerTextureAtlasSprite sprite;
	private QuadBounds quadBounds;

	public BakedQuad(int[] vertexDataIn, int[] vertexDataWithNormalsIn, int tintIndexIn, EnumFacing faceIn,
			EaglerTextureAtlasSprite sprite) {
		this.vertexData = vertexDataIn;
		this.vertexDataWithNormals = vertexDataWithNormalsIn;
		this.tintIndex = tintIndexIn;
		this.face = faceIn;
		this.sprite = sprite;
	}

	public int[] getVertexData() {
		return this.vertexData;
	}

	public int[] getVertexDataWithNormals() {
		return this.vertexDataWithNormals;
	}

	public boolean hasTintIndex() {
		return this.tintIndex != -1;
	}

	public int getTintIndex() {
		return this.tintIndex;
	}

	public EnumFacing getFace() {
		return this.face;
	}

	public EaglerTextureAtlasSprite getSprite() {
		return sprite;
	}

	public QuadBounds getQuadBounds() {
		if (this.quadBounds == null) {
			this.quadBounds = new QuadBounds(this.getVertexData());
		}

		return this.quadBounds;
	}

	public float getMidX() {
		QuadBounds quadbounds = this.getQuadBounds();
		return (quadbounds.getMaxX() + quadbounds.getMinX()) / 2.0F;
	}

	public double getMidY() {
		QuadBounds quadbounds = this.getQuadBounds();
		return (double) ((quadbounds.getMaxY() + quadbounds.getMinY()) / 2.0F);
	}

	public double getMidZ() {
		QuadBounds quadbounds = this.getQuadBounds();
		return (double) ((quadbounds.getMaxZ() + quadbounds.getMinZ()) / 2.0F);
	}

	public boolean isFaceQuad() {
		QuadBounds quadbounds = this.getQuadBounds();
		return quadbounds.isFaceQuad(this.face);
	}

	public boolean isFullQuad() {
		QuadBounds quadbounds = this.getQuadBounds();
		return quadbounds.isFullQuad(this.face);
	}

	public boolean isFullFaceQuad() {
		return this.isFullQuad() && this.isFaceQuad();
	}
}
