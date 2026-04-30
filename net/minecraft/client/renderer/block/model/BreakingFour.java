package net.minecraft.client.renderer.block.model;

import java.util.Arrays;

import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;

public class BreakingFour extends BakedQuad {

	public BreakingFour(BakedQuad parBakedQuad, EaglerTextureAtlasSprite textureIn) {
		super(Arrays.copyOf(parBakedQuad.getVertexData(), parBakedQuad.getVertexData().length),
				Arrays.copyOf(parBakedQuad.getVertexDataWithNormals(), parBakedQuad.getVertexDataWithNormals().length),
				parBakedQuad.tintIndex, parBakedQuad.face, textureIn);
		this.func_178217_e();
	}

	private void func_178217_e() {
		for (int i = 0; i < 4; ++i) {
			this.func_178216_a(i);
		}

	}

	private void func_178216_a(int parInt1) {
		int i = 7 * parInt1;
		float f = Float.intBitsToFloat(this.vertexData[i]);
		float f1 = Float.intBitsToFloat(this.vertexData[i + 1]);
		float f2 = Float.intBitsToFloat(this.vertexData[i + 2]);
		float f3 = 0.0F;
		float f4 = 0.0F;
		switch (this.face) {
		case DOWN:
			f3 = f * 16.0F;
			f4 = (1.0F - f2) * 16.0F;
			break;
		case UP:
			f3 = f * 16.0F;
			f4 = f2 * 16.0F;
			break;
		case NORTH:
			f3 = (1.0F - f) * 16.0F;
			f4 = (1.0F - f1) * 16.0F;
			break;
		case SOUTH:
			f3 = f * 16.0F;
			f4 = (1.0F - f1) * 16.0F;
			break;
		case WEST:
			f3 = f2 * 16.0F;
			f4 = (1.0F - f1) * 16.0F;
			break;
		case EAST:
			f3 = (1.0F - f2) * 16.0F;
			f4 = (1.0F - f1) * 16.0F;
		}

		this.vertexData[i + 4] = Float.floatToRawIntBits(sprite.getInterpolatedU((double) f3));
		this.vertexData[i + 4 + 1] = Float.floatToRawIntBits(sprite.getInterpolatedV((double) f4));
		if (this.vertexDataWithNormals != null) {
			int i2 = 8 * parInt1;
			this.vertexDataWithNormals[i2 + 4] = this.vertexData[i + 4];
			this.vertexDataWithNormals[i2 + 4 + 1] = this.vertexData[i + 4 + 1];

		}
	}
}
