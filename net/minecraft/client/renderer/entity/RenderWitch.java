package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.ResourceLocation;

public class RenderWitch extends RenderLiving<EntityWitch> {
	private static final ResourceLocation witchTextures = new ResourceLocation("textures/entity/witch.png");

	public RenderWitch(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelWitch(0.0F), 0.5F);
		this.addLayer(new LayerHeldItemWitch(this));
	}

	public void doRender(EntityWitch entitywitch, double d0, double d1, double d2, float f, float f1) {
		((ModelWitch) this.mainModel).field_82900_g = entitywitch.getHeldItem() != null;
		super.doRender(entitywitch, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityWitch var1) {
		return witchTextures;
	}

	public void transformHeldFull3DItemLayer() {
		GlStateManager.translate(0.0F, 0.1875F, 0.0F);
	}

	protected void preRenderCallback(EntityWitch var1, float var2) {
		float f = 0.9375F;
		GlStateManager.scale(f, f, f);
	}
}
