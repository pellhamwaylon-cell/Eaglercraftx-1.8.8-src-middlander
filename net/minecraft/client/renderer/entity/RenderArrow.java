package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderArrow extends Render<EntityArrow> {
	private static final ResourceLocation arrowTextures = new ResourceLocation("textures/entity/arrow.png");

	public RenderArrow(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void doRender(EntityArrow entityarrow, double d0, double d1, double d2, float f, float f1) {
		this.bindEntityTexture(entityarrow);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) d0, (float) d1, (float) d2);
		GlStateManager.rotate(
				entityarrow.prevRotationYaw + (entityarrow.rotationYaw - entityarrow.prevRotationYaw) * f1 - 90.0F,
				0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(
				entityarrow.prevRotationPitch + (entityarrow.rotationPitch - entityarrow.prevRotationPitch) * f1, 0.0F,
				0.0F, 1.0F);
		Tessellator tessellator = Tessellator.getInstance();
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		byte b0 = 0;
		float f2 = 0.0F;
		float f3 = 0.5F;
		float f4 = (float) (0 + b0 * 10) / 32.0F;
		float f5 = (float) (5 + b0 * 10) / 32.0F;
		float f6 = 0.0F;
		float f7 = 0.15625F;
		float f8 = (float) (5 + b0 * 10) / 32.0F;
		float f9 = (float) (10 + b0 * 10) / 32.0F;
		float f10 = 0.05625F;
		GlStateManager.enableRescaleNormal();
		float f11 = (float) entityarrow.arrowShake - f1;
		if (f11 > 0.0F) {
			float f12 = -MathHelper.sin(f11 * 3.0F) * f11;
			GlStateManager.rotate(f12, 0.0F, 0.0F, 1.0F);
		}

		GlStateManager.rotate(45.0F, 1.0F, 0.0F, 0.0F);
		GlStateManager.scale(f10, f10, f10);
		GlStateManager.translate(-4.0F, 0.0F, 0.0F);
		EaglercraftGPU.glNormal3f(f10, 0.0F, 0.0F);
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
		worldrenderer.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
		worldrenderer.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
		worldrenderer.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
		tessellator.draw();
		EaglercraftGPU.glNormal3f(-f10, 0.0F, 0.0F);
		worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
		worldrenderer.pos(-7.0D, 2.0D, -2.0D).tex((double) f6, (double) f8).endVertex();
		worldrenderer.pos(-7.0D, 2.0D, 2.0D).tex((double) f7, (double) f8).endVertex();
		worldrenderer.pos(-7.0D, -2.0D, 2.0D).tex((double) f7, (double) f9).endVertex();
		worldrenderer.pos(-7.0D, -2.0D, -2.0D).tex((double) f6, (double) f9).endVertex();
		tessellator.draw();

		for (int i = 0; i < 4; ++i) {
			GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
			EaglercraftGPU.glNormal3f(0.0F, 0.0F, f10);
			worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
			worldrenderer.pos(-8.0D, -2.0D, 0.0D).tex((double) f2, (double) f4).endVertex();
			worldrenderer.pos(8.0D, -2.0D, 0.0D).tex((double) f3, (double) f4).endVertex();
			worldrenderer.pos(8.0D, 2.0D, 0.0D).tex((double) f3, (double) f5).endVertex();
			worldrenderer.pos(-8.0D, 2.0D, 0.0D).tex((double) f2, (double) f5).endVertex();
			tessellator.draw();
		}

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		super.doRender(entityarrow, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityArrow var1) {
		return arrowTextures;
	}
}
