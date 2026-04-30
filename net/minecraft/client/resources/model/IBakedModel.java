package net.minecraft.client.resources.model;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.EnumFacing;

public interface IBakedModel {
	List<BakedQuad> getFaceQuads(EnumFacing var1);

	List<BakedQuad> getGeneralQuads();

	boolean isAmbientOcclusion();

	boolean isGui3d();

	boolean isBuiltInRenderer();

	EaglerTextureAtlasSprite getParticleTexture();

	ItemCameraTransforms getItemCameraTransforms();
}
