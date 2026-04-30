package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.layers.LayerWolfCollar;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderWolf extends RenderLiving<EntityWolf> {
	private static final ResourceLocation wolfTextures = new ResourceLocation("textures/entity/wolf/wolf.png");
	private static final ResourceLocation tamedWolfTextures = new ResourceLocation(
			"textures/entity/wolf/wolf_tame.png");
	private static final ResourceLocation anrgyWolfTextures = new ResourceLocation(
			"textures/entity/wolf/wolf_angry.png");

	public RenderWolf(RenderManager renderManagerIn, ModelBase modelBaseIn, float shadowSizeIn) {
		super(renderManagerIn, modelBaseIn, shadowSizeIn);
		this.addLayer(new LayerWolfCollar(this));
	}

	protected float handleRotationFloat(EntityWolf entitywolf, float var2) {
		return entitywolf.getTailRotation();
	}

	public void doRender(EntityWolf entitywolf, double d0, double d1, double d2, float f, float f1) {
		if (entitywolf.isWolfWet()) {
			float f2 = entitywolf.getBrightness(f1) * entitywolf.getShadingWhileWet(f1);
			GlStateManager.color(f2, f2, f2);
		}

		super.doRender(entitywolf, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityWolf entitywolf) {
		return entitywolf.isTamed() ? tamedWolfTextures : (entitywolf.isAngry() ? anrgyWolfTextures : wolfTextures);
	}
}
