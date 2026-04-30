package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;

public class LayerDeadmau5Head implements LayerRenderer<AbstractClientPlayer> {
	private final RenderPlayer playerRenderer;

	public LayerDeadmau5Head(RenderPlayer playerRendererIn) {
		this.playerRenderer = playerRendererIn;
	}

	public void doRenderLayer(AbstractClientPlayer abstractclientplayer, float var2, float var3, float f, float var5,
			float var6, float var7, float var8) {
		if (abstractclientplayer.getName().equals("deadmau5") && abstractclientplayer.hasSkin()
				&& !abstractclientplayer.isInvisible() && this.playerRenderer.getMainModel() instanceof ModelPlayer) {
			this.playerRenderer.bindTexture(abstractclientplayer.getLocationSkin());

			for (int i = 0; i < 2; ++i) {
				float f1 = abstractclientplayer.prevRotationYaw
						+ (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * f
						- (abstractclientplayer.prevRenderYawOffset
								+ (abstractclientplayer.renderYawOffset - abstractclientplayer.prevRenderYawOffset)
										* f);
				float f2 = abstractclientplayer.prevRotationPitch
						+ (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * f;
				GlStateManager.pushMatrix();
				GlStateManager.rotate(f1, 0.0F, 1.0F, 0.0F);
				GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
				GlStateManager.translate(0.375F * (float) (i * 2 - 1), 0.0F, 0.0F);
				GlStateManager.translate(0.0F, -0.375F, 0.0F);
				GlStateManager.rotate(-f2, 1.0F, 0.0F, 0.0F);
				GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
				float f3 = 1.3333334F;
				GlStateManager.scale(f3, f3, f3);
				((ModelPlayer) this.playerRenderer.getMainModel()).renderDeadmau5Head(0.0625F);
				GlStateManager.popMatrix();
			}

		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}
}
