package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelLeashKnot;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.util.ResourceLocation;

public class RenderLeashKnot extends Render<EntityLeashKnot> {
	private static final ResourceLocation leashKnotTextures = new ResourceLocation("textures/entity/lead_knot.png");
	private ModelLeashKnot leashKnotModel = new ModelLeashKnot();

	public RenderLeashKnot(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void doRender(EntityLeashKnot entityleashknot, double d0, double d1, double d2, float f, float f1) {
		GlStateManager.pushMatrix();
		GlStateManager.disableCull();
		GlStateManager.translate((float) d0, (float) d1, (float) d2);
		float f2 = 0.0625F;
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		GlStateManager.enableAlpha();
		this.bindEntityTexture(entityleashknot);
		this.leashKnotModel.render(entityleashknot, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, f2);
		GlStateManager.popMatrix();
		super.doRender(entityleashknot, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityLeashKnot var1) {
		return leashKnotTextures;
	}
}
