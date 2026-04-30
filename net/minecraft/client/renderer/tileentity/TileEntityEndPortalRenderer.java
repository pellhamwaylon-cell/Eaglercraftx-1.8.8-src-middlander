package net.minecraft.client.renderer.tileentity;

import net.lax1dude.eaglercraft.v1_8.internal.buffer.FloatBuffer;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.ShadersRenderPassFuture;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.tileentity.TileEntityEndPortal;
import net.minecraft.util.ResourceLocation;

public class TileEntityEndPortalRenderer extends TileEntitySpecialRenderer<TileEntityEndPortal> {
	private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
	private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
	private static final EaglercraftRandom field_147527_e = new EaglercraftRandom(31100L);
	FloatBuffer field_147528_b = GLAllocation.createDirectFloatBuffer(16);

	public void renderTileEntityAt(TileEntityEndPortal var1, double d0, double d1, double d2, float var8, int var9) {
		if (DeferredStateManager.isInDeferredPass()) {
			if (!DeferredStateManager.isInParaboloidPass() && !DeferredStateManager.isEnableShadowRender()
					&& DeferredStateManager.forwardCallbackHandler != null) {
				DeferredStateManager.forwardCallbackHandler
						.push(new ShadersRenderPassFuture((float) d0, (float) d1, (float) d2, var8) {
							@Override
							public void draw(PassType pass) {
								if (pass == PassType.MAIN) {
									DeferredStateManager.reportForwardRenderObjectPosition2(x, y, z);
								}
								DeferredStateManager.setDefaultMaterialConstants();
								DeferredStateManager.setRoughnessConstant(0.3f);
								DeferredStateManager.setMetalnessConstant(0.3f);
								DeferredStateManager.setEmissionConstant(0.9f);
								renderTileEntityAt0(var1, d0, d1, d2, var8, var9);
								DeferredStateManager.setDefaultMaterialConstants();
								DeferredStateManager.setHDRTranslucentPassBlendFunc();
							}
						});
			}
			return;
		}
		GlStateManager.enableBlend();
		renderTileEntityAt0(var1, d0, d1, d2, var8, var9);
		GlStateManager.disableBlend();
	}

	private void renderTileEntityAt0(TileEntityEndPortal var1, double d0, double d1, double d2, float var8, int var9) {
		float f = (float) this.rendererDispatcher.entityX;
		float f1 = (float) this.rendererDispatcher.entityY;
		float f2 = (float) this.rendererDispatcher.entityZ;
		GlStateManager.disableLighting();
		field_147527_e.setSeed(31100L);
		float f3 = 0.75F;

		for (int i = 0; i < 16; ++i) {
			GlStateManager.pushMatrix();
			float f4 = (float) (16 - i);
			float f5 = 0.0625F;
			float f6 = 1.0F / (f4 + 1.0F);
			if (i == 0) {
				this.bindTexture(END_SKY_TEXTURE);
				f6 = 0.1F;
				f4 = 65.0F;
				f5 = 0.125F;
				if (DeferredStateManager.isInDeferredPass()) {
					DeferredStateManager.setHDRTranslucentPassBlendFunc();
				} else {
					GlStateManager.blendFunc(770, 771);
				}
			}

			if (i >= 1) {
				this.bindTexture(END_PORTAL_TEXTURE);
			}

			if (i == 1) {
				if (DeferredStateManager.isInDeferredPass()) {
					GlStateManager.tryBlendFuncSeparate(GL_ONE, GL_ONE, GL_ZERO, GL_ZERO);
				} else {
					GlStateManager.blendFunc(1, 1);
				}
				f5 = 0.5F;
			}

			float f7 = (float) (-(d1 + (double) f3 - 1.25));
			float f8 = f7 + (float) ActiveRenderInfo.getPosition().yCoord;
			float f9 = f7 + f4 + (float) ActiveRenderInfo.getPosition().yCoord;
			float f10 = f8 / f9;
			f10 = (float) (d1 + (double) f3) + f10;
			GlStateManager.translate(f, f10, f2);
			GlStateManager.texGen(GlStateManager.TexGen.S, 9217);
			GlStateManager.texGen(GlStateManager.TexGen.T, 9217);
			GlStateManager.texGen(GlStateManager.TexGen.R, 9217);
			GlStateManager.texGen(GlStateManager.TexGen.Q, 9216);
			GlStateManager.func_179105_a(GlStateManager.TexGen.S, 9473, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
			GlStateManager.func_179105_a(GlStateManager.TexGen.T, 9473, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
			GlStateManager.func_179105_a(GlStateManager.TexGen.R, 9473, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
			GlStateManager.func_179105_a(GlStateManager.TexGen.Q, 9474, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
			GlStateManager.enableTexGen();
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.0F, (float) (Minecraft.getSystemTime() % 700000L) / 700000.0F, 0.0F);
			GlStateManager.scale(f5, f5, f5);
			GlStateManager.translate(0.5F, 0.5F, 0.0F);
			GlStateManager.rotate((float) (i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GlStateManager.translate(-0.5F, -0.5F, 0.0F);
			GlStateManager.translate(-f, -f2, -f1);
			f8 = f7 + (float) ActiveRenderInfo.getPosition().yCoord;
			GlStateManager.translate((float) ActiveRenderInfo.getPosition().xCoord * f4 / f8,
					(float) ActiveRenderInfo.getPosition().zCoord * f4 / f8, -f1);
			Tessellator tessellator = Tessellator.getInstance();
			WorldRenderer worldrenderer = tessellator.getWorldRenderer();
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
			float f11 = (field_147527_e.nextFloat() * 0.5F + 0.1F) * f6;
			float f12 = (field_147527_e.nextFloat() * 0.5F + 0.4F) * f6;
			float f13 = (field_147527_e.nextFloat() * 0.5F + 0.5F) * f6;
			if (i == 0) {
				f11 = f12 = f13 = 1.0F * f6;
			}

			worldrenderer.pos(d0, d1 + (double) f3, d2).color(f11, f12, f13, 1.0F).endVertex();
			worldrenderer.pos(d0, d1 + (double) f3, d2 + 1.0D).color(f11, f12, f13, 1.0F).endVertex();
			worldrenderer.pos(d0 + 1.0D, d1 + (double) f3, d2 + 1.0D).color(f11, f12, f13, 1.0F).endVertex();
			worldrenderer.pos(d0 + 1.0D, d1 + (double) f3, d2).color(f11, f12, f13, 1.0F).endVertex();
			tessellator.draw();
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
			this.bindTexture(END_SKY_TEXTURE);
		}

		GlStateManager.disableTexGen();
		GlStateManager.enableLighting();
	}

	private FloatBuffer func_147525_a(float parFloat1, float parFloat2, float parFloat3, float parFloat4) {
		this.field_147528_b.clear();
		this.field_147528_b.put(parFloat1).put(parFloat2).put(parFloat3).put(parFloat4);
		this.field_147528_b.flip();
		return this.field_147528_b;
	}
}
