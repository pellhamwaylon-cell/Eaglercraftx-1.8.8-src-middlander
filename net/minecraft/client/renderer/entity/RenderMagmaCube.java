package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelMagmaCube;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.util.ResourceLocation;

public class RenderMagmaCube extends RenderLiving<EntityMagmaCube> {
	private static final ResourceLocation magmaCubeTextures = new ResourceLocation(
			"textures/entity/slime/magmacube.png");

	public RenderMagmaCube(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelMagmaCube(), 0.25F);
	}

	protected ResourceLocation getEntityTexture(EntityMagmaCube var1) {
		return magmaCubeTextures;
	}

	protected void preRenderCallback(EntityMagmaCube entitymagmacube, float f) {
		int i = entitymagmacube.getSlimeSize();
		float f1 = (entitymagmacube.prevSquishFactor
				+ (entitymagmacube.squishFactor - entitymagmacube.prevSquishFactor) * f) / ((float) i * 0.5F + 1.0F);
		float f2 = 1.0F / (f1 + 1.0F);
		float f3 = (float) i;
		GlStateManager.scale(f2 * f3, 1.0F / f2 * f3, f2 * f3);
	}
}
