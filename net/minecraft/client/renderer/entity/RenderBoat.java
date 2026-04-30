package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderBoat extends Render<EntityBoat> {
	private static final ResourceLocation boatTextures = new ResourceLocation("textures/entity/boat.png");
	protected ModelBase modelBoat = new ModelBoat();

	public RenderBoat(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.shadowSize = 0.5F;
	}

	public void doRender(EntityBoat entityboat, double d0, double d1, double d2, float f, float f1) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) d0, (float) d1 + 0.25F, (float) d2);
		GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
		float f2 = (float) entityboat.getTimeSinceHit() - f1;
		float f3 = entityboat.getDamageTaken() - f1;
		if (f3 < 0.0F) {
			f3 = 0.0F;
		}

		if (f2 > 0.0F) {
			GlStateManager.rotate(MathHelper.sin(f2) * f2 * f3 / 10.0F * (float) entityboat.getForwardDirection(), 1.0F,
					0.0F, 0.0F);
		}

		float f4 = 0.75F;
		GlStateManager.scale(f4, f4, f4);
		GlStateManager.scale(1.0F / f4, 1.0F / f4, 1.0F / f4);
		this.bindEntityTexture(entityboat);
		GlStateManager.scale(-1.0F, -1.0F, 1.0F);
		this.modelBoat.render(entityboat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
		super.doRender(entityboat, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityBoat var1) {
		return boatTextures;
	}
}
