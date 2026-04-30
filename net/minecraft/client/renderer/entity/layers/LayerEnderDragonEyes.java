package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.OpenGlHelper;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.client.renderer.entity.RenderDragon;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.util.ResourceLocation;

public class LayerEnderDragonEyes implements LayerRenderer<EntityDragon> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/enderdragon/dragon_eyes.png");
	private final RenderDragon dragonRenderer;

	public LayerEnderDragonEyes(RenderDragon dragonRendererIn) {
		this.dragonRenderer = dragonRendererIn;
	}

	public void doRenderLayer(EntityDragon entitydragon, float f, float f1, float f2, float f3, float f4, float f5,
			float f6) {
		this.dragonRenderer.bindTexture(TEXTURE);
		if (DeferredStateManager.isInDeferredPass()) {
			DeferredStateManager.setEmissionConstant(0.5f);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enablePolygonOffset();
			GlStateManager.doPolygonOffset(-0.025f, 1.0f);
			this.dragonRenderer.getMainModel().render(entitydragon, f, f1, f3, f4, f5, f6);
			this.dragonRenderer.func_177105_a(entitydragon, f2);
			GlStateManager.disablePolygonOffset();
			DeferredStateManager.setEmissionConstant(0.0f);
			return;
		}
		GlStateManager.enableBlend();
		GlStateManager.disableAlpha();
		GlStateManager.blendFunc(1, 1);
		GlStateManager.disableLighting();
		GlStateManager.depthFunc(514);
		char c0 = '\uf0f0';
		int i = c0 % 65536;
		int j = c0 / 65536;
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) i / 1.0F, (float) j / 1.0F);
		GlStateManager.enableLighting();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		this.dragonRenderer.getMainModel().render(entitydragon, f, f1, f3, f4, f5, f6);
		this.dragonRenderer.func_177105_a(entitydragon, f2);
		GlStateManager.disableBlend();
		GlStateManager.enableAlpha();
		GlStateManager.depthFunc(515);
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
