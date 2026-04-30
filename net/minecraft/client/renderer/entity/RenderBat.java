package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBat;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBat extends RenderLiving<EntityBat> {
	private static final ResourceLocation batTextures = new ResourceLocation("textures/entity/bat.png");

	public RenderBat(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBat(), 0.25F);
	}

	protected ResourceLocation getEntityTexture(EntityBat var1) {
		return batTextures;
	}

	protected void preRenderCallback(EntityBat var1, float var2) {
		GlStateManager.scale(0.35F, 0.35F, 0.35F);
	}

	protected void rotateCorpse(EntityBat entitybat, float f, float f1, float f2) {
		if (!entitybat.getIsBatHanging()) {
			GlStateManager.translate(0.0F, MathHelper.cos(f * 0.3F) * 0.1F, 0.0F);
		} else {
			GlStateManager.translate(0.0F, -0.1F, 0.0F);
		}

		super.rotateCorpse(entitybat, f, f1, f2);
	}
}
