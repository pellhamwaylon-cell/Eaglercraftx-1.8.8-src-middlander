package net.minecraft.client.renderer.tileentity;

import static net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.ExtGLEnums.*;
import static net.lax1dude.eaglercraft.v1_8.internal.PlatformOpenGL.*;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.EaglerDeferredPipeline;
import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;

public class TileEntitySignRenderer extends TileEntitySpecialRenderer<TileEntitySign> {
	private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
	private final ModelSign model = new ModelSign();

	public static boolean disableProfanityFilter = false;

	public void renderTileEntityAt(TileEntitySign tileentitysign, double d0, double d1, double d2, float var8, int i) {
		Block block = tileentitysign.getBlockType();
		GlStateManager.pushMatrix();
		float f = 0.6666667F;
		if (block == Blocks.standing_sign) {
			GlStateManager.translate((float) d0 + 0.5F, (float) d1 + 0.75F * f, (float) d2 + 0.5F);
			float f1 = (float) (tileentitysign.getBlockMetadata() * 360) / 16.0F;
			GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
			this.model.signStick.showModel = true;
		} else {
			int k = tileentitysign.getBlockMetadata();
			float f2 = 0.0F;
			if (k == 2) {
				f2 = 180.0F;
			}

			if (k == 4) {
				f2 = 90.0F;
			}

			if (k == 5) {
				f2 = -90.0F;
			}

			GlStateManager.translate((float) d0 + 0.5F, (float) d1 + 0.75F * f, (float) d2 + 0.5F);
			GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
			this.model.signStick.showModel = false;
		}

		if (i >= 0) {
			this.bindTexture(DESTROY_STAGES[i]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			this.bindTexture(SIGN_TEXTURE);
		}

		GlStateManager.enableRescaleNormal();
		GlStateManager.pushMatrix();
		GlStateManager.scale(f, -f, -f);
		this.model.renderSign();
		GlStateManager.popMatrix();
		FontRenderer fontrenderer = this.getFontRenderer();
		float f3 = 0.015625F * f;
		GlStateManager.translate(0.0F, 0.5F * f, 0.07F * f);
		GlStateManager.scale(f3, -f3, f3);
		EaglercraftGPU.glNormal3f(0.0F, 0.0F, -1.0F * f3);
		GlStateManager.depthMask(false);
		byte b0 = 0;
		if (i < 0) {
			if (DeferredStateManager.isInDeferredPass()) {
				_wglDrawBuffers(_GL_COLOR_ATTACHMENT0);
				GlStateManager.colorMask(true, true, true, false);
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
			}
			IChatComponent[] signText = disableProfanityFilter ? tileentitysign.signText
					: tileentitysign.getSignTextProfanityFilter();
			for (int j = 0; j < signText.length; ++j) {
				if (signText[j] != null) {
					IChatComponent ichatcomponent = signText[j];
					List list = GuiUtilRenderComponents.func_178908_a(ichatcomponent, 90, fontrenderer, false, true);
					String s = list != null && list.size() > 0 ? ((IChatComponent) list.get(0)).getFormattedText() : "";
					if (j == tileentitysign.lineBeingEdited) {
						s = "> " + s + " <";
						fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - signText.length * 5,
								b0);
					} else {
						fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - signText.length * 5,
								b0);
					}
				}
			}
			if (DeferredStateManager.isInDeferredPass()) {
				_wglDrawBuffers(EaglerDeferredPipeline.instance.gBufferDrawBuffers);
				GlStateManager.colorMask(true, true, true, true);
			}
		}

		GlStateManager.depthMask(true);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();
		if (i >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}

	}
}
