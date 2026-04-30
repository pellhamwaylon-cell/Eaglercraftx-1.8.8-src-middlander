package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.util.ResourceLocation;

public class RenderGhast extends RenderLiving<EntityGhast> {
	private static final ResourceLocation ghastTextures = new ResourceLocation("textures/entity/ghast/ghast.png");
	private static final ResourceLocation ghastShootingTextures = new ResourceLocation(
			"textures/entity/ghast/ghast_shooting.png");

	public RenderGhast(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelGhast(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityGhast entityghast) {
		return entityghast.isAttacking() ? ghastShootingTextures : ghastTextures;
	}

	protected void preRenderCallback(EntityGhast var1, float var2) {
		float f = 1.0F;
		float f1 = (8.0F + f) / 2.0F;
		float f2 = (8.0F + 1.0F / f) / 2.0F;
		GlStateManager.scale(f2, f1, f2);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	}
}
