package net.minecraft.client.resources.model;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.util.EnumFacing;

public class BuiltInModel implements IBakedModel {
	private ItemCameraTransforms cameraTransforms;

	public BuiltInModel(ItemCameraTransforms parItemCameraTransforms) {
		this.cameraTransforms = parItemCameraTransforms;
	}

	public List<BakedQuad> getFaceQuads(EnumFacing var1) {
		return null;
	}

	public List<BakedQuad> getGeneralQuads() {
		return null;
	}

	public boolean isAmbientOcclusion() {
		return false;
	}

	public boolean isGui3d() {
		return true;
	}

	public boolean isBuiltInRenderer() {
		return true;
	}

	public EaglerTextureAtlasSprite getParticleTexture() {
		return null;
	}

	public ItemCameraTransforms getItemCameraTransforms() {
		return this.cameraTransforms;
	}
}
