package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.EaglerDeferredPipeline;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.ShadersRenderPassFuture;
import net.lax1dude.eaglercraft.v1_8.vector.Matrix4f;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.boss.EntityDragon;

public class LayerEnderDragonDeath implements LayerRenderer<EntityDragon> {
	public void doRenderLayer(EntityDragon entitydragon, float var2, float var3, float f, float var5, float var6,
			float var7, float var8) {
		if (DeferredStateManager.isInDeferredPass()) {
			if (entitydragon.deathTicks > 0 && !DeferredStateManager.isEnableShadowRender()
					&& DeferredStateManager.forwardCallbackHandler != null) {
				final Matrix4f mat = new Matrix4f(GlStateManager.getModelViewReference());
				final float ly = GlStateManager.getTexCoordY(1);
				DeferredStateManager.forwardCallbackHandler.push(
						new ShadersRenderPassFuture(entitydragon, EaglerDeferredPipeline.instance.getPartialTicks()) {
							@Override
							public void draw(PassType pass) {
								if (pass == PassType.MAIN) {
									DeferredStateManager.reportForwardRenderObjectPosition2(x, y, z);
								}
								float bright = 0.01f + ly * 0.001f;
								GlStateManager.color(bright, bright, bright, 1.0F);
								GlStateManager.pushMatrix();
								GlStateManager.loadMatrix(mat);
								GlStateManager.tryBlendFuncSeparate(770, GL_ONE, GL_ZERO, GL_ZERO);
								GlStateManager.enableCull();
								GlStateManager.enableBlend();
								GlStateManager.disableExtensionPipeline();
								EntityRenderer.disableLightmapStatic();
								doRenderLayer0(entitydragon, var2, var3, f, var5, var6, var7, var8);
								GlStateManager.enableExtensionPipeline();
								GlStateManager.popMatrix();
								EntityRenderer.disableLightmapStatic();
								GlStateManager.disableAlpha();
								DeferredStateManager.setHDRTranslucentPassBlendFunc();
							}
						});
			}
		} else {
			GlStateManager.enableBlend();
			GlStateManager.enableCull();
			GlStateManager.blendFunc(770, 1);
			doRenderLayer0(entitydragon, var2, var3, f, var5, var6, var7, var8);
			GlStateManager.disableBlend();
			GlStateManager.disableCull();
		}
	}

	public void doRenderLayer0(EntityDragon entitydragon, float var2, float var3, float f, float var5, float var6,
			float var7, float var8) {
		if (entitydragon.deathTicks > 0) {
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			RenderHelper.disableStandardItemLighting();
			float f1 = ((float) entitydragon.deathTicks + f) / 200.0F;
			float f2 = 0.0F;
			if (f1 > 0.8F) {
				f2 = (f1 - 0.8F) / 0.2F;
			}

			EaglercraftRandom random = new EaglercraftRandom(432L);
			GlStateManager.disableTexture2D();
			GlStateManager.shadeModel(7425);
			GlStateManager.disableAlpha();
			GlStateManager.depthMask(false);
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, -1.0F, -2.0F);

			for (int i = 0; (float) i < (f1 + f1 * f1) / 2.0F * 60.0F; ++i) {
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 0.0F, 1.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(random.nextFloat() * 360.0F + f1 * 90.0F, 0.0F, 0.0F, 1.0F);
				float f3 = random.nextFloat() * 20.0F + 5.0F + f2 * 10.0F;
				float f4 = random.nextFloat() * 2.0F + 1.0F + f2 * 2.0F;
				worldrenderer.begin(6, DefaultVertexFormats.POSITION_COLOR);
				worldrenderer.pos(0.0D, 0.0D, 0.0D).color(255, 255, 255, (int) (255.0F * (1.0F - f2))).endVertex();
				worldrenderer.pos(-0.866D * (double) f4, (double) f3, (double) (-0.5F * f4)).color(255, 0, 255, 0)
						.endVertex();
				worldrenderer.pos(0.866D * (double) f4, (double) f3, (double) (-0.5F * f4)).color(255, 0, 255, 0)
						.endVertex();
				worldrenderer.pos(0.0D, (double) f3, (double) (1.0F * f4)).color(255, 0, 255, 0).endVertex();
				worldrenderer.pos(-0.866D * (double) f4, (double) f3, (double) (-0.5F * f4)).color(255, 0, 255, 0)
						.endVertex();
				tessellator.draw();
			}

			GlStateManager.popMatrix();
			GlStateManager.depthMask(true);
			GlStateManager.shadeModel(7424);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.enableTexture2D();
			GlStateManager.enableAlpha();
			RenderHelper.enableStandardItemLighting();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
