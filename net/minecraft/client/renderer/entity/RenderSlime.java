package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerSlimeGel;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.util.ResourceLocation;

public class RenderSlime extends RenderLiving<EntitySlime> {
	public static final ResourceLocation slimeTextures = new ResourceLocation("textures/entity/slime/slime.png");

	public RenderSlime(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
		this.addLayer(new LayerSlimeGel(this));
	}

	public void doRender(EntitySlime entityslime, double d0, double d1, double d2, float f, float f1) {
		this.shadowSize = 0.25F * (float) entityslime.getSlimeSize();
		super.doRender(entityslime, d0, d1, d2, f, f1);
	}

	protected void preRenderCallback(EntitySlime entityslime, float f) {
		float f1 = (float) entityslime.getSlimeSize();
		float f2 = (entityslime.prevSquishFactor + (entityslime.squishFactor - entityslime.prevSquishFactor) * f)
				/ (f1 * 0.5F + 1.0F);
		float f3 = 1.0F / (f2 + 1.0F);
		GlStateManager.scale(f3 * f1, 1.0F / f3 * f1, f3 * f1);
	}

	protected ResourceLocation getEntityTexture(EntitySlime var1) {
		return slimeTextures;
	}
}
