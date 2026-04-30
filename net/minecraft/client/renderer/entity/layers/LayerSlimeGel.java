package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.ShadersRenderPassFuture;
import net.lax1dude.eaglercraft.v1_8.vector.Matrix4f;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;

public class LayerSlimeGel implements LayerRenderer<EntitySlime> {
	private final RenderSlime slimeRenderer;
	private final ModelBase slimeModel = new ModelSlime(0);

	public LayerSlimeGel(RenderSlime slimeRendererIn) {
		this.slimeRenderer = slimeRendererIn;
	}

	public void doRenderLayer(EntitySlime entityslime, float f, float f1, float var4, float f2, float f3, float f4,
			float f5) {
		if (DeferredStateManager.isInDeferredPass()) {
			if (DeferredStateManager.forwardCallbackHandler != null) {
				final Matrix4f mat = new Matrix4f(GlStateManager.getModelViewReference());
				DeferredStateManager.forwardCallbackHandler.push(new ShadersRenderPassFuture(entityslime) {
					@Override
					public void draw(PassType pass) {
						if (pass == PassType.MAIN) {
							DeferredStateManager.reportForwardRenderObjectPosition2(x, y, z);
						}
						DeferredStateManager.setDefaultMaterialConstants();
						DeferredStateManager.setRoughnessConstant(0.3f);
						DeferredStateManager.setMetalnessConstant(0.1f);
						boolean flag = LayerSlimeGel.this.slimeRenderer.setBrightness(entityslime, partialTicks,
								LayerSlimeGel.this.shouldCombineTextures());
						EntityRenderer.enableLightmapStatic();
						GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						GlStateManager.pushMatrix();
						GlStateManager.loadMatrix(mat);
						RenderManager.setupLightmapCoords(entityslime, partialTicks);
						LayerSlimeGel.this.slimeModel
								.setModelAttributes(LayerSlimeGel.this.slimeRenderer.getMainModel());
						LayerSlimeGel.this.slimeRenderer.bindTexture(RenderSlime.slimeTextures);
						LayerSlimeGel.this.slimeModel.render(entityslime, f, f1, f2, f3, f4, f5);
						GlStateManager.popMatrix();
						EntityRenderer.disableLightmapStatic();
						if (flag) {
							LayerSlimeGel.this.slimeRenderer.unsetBrightness();
						}
					}
				});
			}
			return;
		}
		if (!entityslime.isInvisible()) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 771);
			this.slimeModel.setModelAttributes(this.slimeRenderer.getMainModel());
			this.slimeModel.render(entityslime, f, f1, f2, f3, f4, f5);
			GlStateManager.disableBlend();
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}
}
