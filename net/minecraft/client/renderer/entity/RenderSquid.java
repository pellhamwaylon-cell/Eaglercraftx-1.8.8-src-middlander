package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.util.ResourceLocation;

public class RenderSquid extends RenderLiving<EntitySquid> {
	private static final ResourceLocation squidTextures = new ResourceLocation("textures/entity/squid.png");

	public RenderSquid(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
	}

	protected ResourceLocation getEntityTexture(EntitySquid var1) {
		return squidTextures;
	}

	protected void rotateCorpse(EntitySquid entitysquid, float var2, float f, float f1) {
		float f2 = entitysquid.prevSquidPitch + (entitysquid.squidPitch - entitysquid.prevSquidPitch) * f1;
		float f3 = entitysquid.prevSquidYaw + (entitysquid.squidYaw - entitysquid.prevSquidYaw) * f1;
		GlStateManager.translate(0.0F, 0.5F, 0.0F);
		GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(f2, 1.0F, 0.0F, 0.0F);
		GlStateManager.rotate(f3, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(0.0F, -1.2F, 0.0F);
	}

	protected float handleRotationFloat(EntitySquid entitysquid, float f) {
		return entitysquid.lastTentacleAngle + (entitysquid.tentacleAngle - entitysquid.lastTentacleAngle) * f;
	}
}
