package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.OpenGlHelper;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.ShadersRenderPassFuture;
import net.lax1dude.eaglercraft.v1_8.vector.Matrix4f;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.entity.RenderEnderman;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.EnumWorldBlockLayer;

public class LayerHeldBlock implements LayerRenderer<EntityEnderman> {
	private final RenderEnderman endermanRenderer;

	public LayerHeldBlock(RenderEnderman endermanRendererIn) {
		this.endermanRenderer = endermanRendererIn;
	}

	public void doRenderLayer(EntityEnderman entityenderman, float var2, float var3, float f, float var5, float var6,
			float var7, float var8) {
		IBlockState iblockstate = entityenderman.getHeldBlockState();
		if (iblockstate.getBlock().getMaterial() != Material.air) {
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			GlStateManager.enableRescaleNormal();
			GlStateManager.pushMatrix();
			GlStateManager.translate(0.0F, 0.6875F, -0.75F);
			GlStateManager.rotate(20.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.rotate(45.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.25F, 0.1875F, 0.25F);
			float f1 = 0.5F;
			GlStateManager.scale(-f1, -f1, f1);
			int i = entityenderman.getBrightnessForRender(f);
			int j = i % 65536;
			int k = i / 65536;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) j / 1.0F, (float) k / 1.0F);

			if (DeferredStateManager.isInDeferredPass()
					&& iblockstate.getBlock().getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT) {
				if (DeferredStateManager.forwardCallbackHandler != null) {
					final Matrix4f mat = new Matrix4f(GlStateManager.getModelViewReference());
					final float lx = GlStateManager.getTexCoordX(1), ly = GlStateManager.getTexCoordY(1);
					DeferredStateManager.forwardCallbackHandler.push(new ShadersRenderPassFuture(entityenderman) {
						@Override
						public void draw(PassType pass) {
							if (pass == PassType.MAIN) {
								DeferredStateManager.reportForwardRenderObjectPosition2(x, y, z);
							}
							EntityRenderer.enableLightmapStatic();
							GlStateManager.pushMatrix();
							GlStateManager.loadMatrix(mat);
							GlStateManager.texCoords2DDirect(1, lx, ly);
							LayerHeldBlock.this.endermanRenderer.bindTexture(TextureMap.locationBlocksTexture);
							GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
							blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);
							GlStateManager.popMatrix();
							EntityRenderer.disableLightmapStatic();
							GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
						}
					});
				}
				GlStateManager.popMatrix();
				GlStateManager.disableRescaleNormal();
				return;
			}

			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.endermanRenderer.bindTexture(TextureMap.locationBlocksTexture);
			blockrendererdispatcher.renderBlockBrightness(iblockstate, 1.0F);

			GlStateManager.popMatrix();
			GlStateManager.disableRescaleNormal();
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}
