package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.util.ResourceLocation;

public class RenderBlaze extends RenderLiving<EntityBlaze> {
	private static final ResourceLocation blazeTextures = new ResourceLocation("textures/entity/blaze.png");

	public RenderBlaze(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBlaze(), 0.5F);
	}

	protected ResourceLocation getEntityTexture(EntityBlaze var1) {
		return blazeTextures;
	}

	public void doRender(EntityBlaze entityliving, double d0, double d1, double d2, float f, float f1) {
		if (DeferredStateManager.isInDeferredPass()) {
			DeferredStateManager.setEmissionConstant(1.0f);
			try {
				super.doRender(entityliving, d0, d1, d2, f, f1);
			} finally {
				DeferredStateManager.setEmissionConstant(0.0f);
			}
		} else {
			super.doRender(entityliving, d0, d1, d2, f, f1);
		}
	}
}
