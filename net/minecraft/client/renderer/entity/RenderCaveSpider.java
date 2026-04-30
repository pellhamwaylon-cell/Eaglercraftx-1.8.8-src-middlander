package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.monster.EntityCaveSpider;
import net.minecraft.util.ResourceLocation;

public class RenderCaveSpider extends RenderSpider<EntityCaveSpider> {
	private static final ResourceLocation caveSpiderTextures = new ResourceLocation(
			"textures/entity/spider/cave_spider.png");

	public RenderCaveSpider(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.shadowSize *= 0.7F;
	}

	protected void preRenderCallback(EntityCaveSpider var1, float var2) {
		GlStateManager.scale(0.7F, 0.7F, 0.7F);
	}

	protected ResourceLocation getEntityTexture(EntityCaveSpider var1) {
		return caveSpiderTextures;
	}
}
