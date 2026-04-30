package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelWither;
import net.minecraft.client.renderer.entity.layers.LayerWitherAura;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.util.ResourceLocation;

public class RenderWither extends RenderLiving<EntityWither> {
	private static final ResourceLocation invulnerableWitherTextures = new ResourceLocation(
			"textures/entity/wither/wither_invulnerable.png");
	private static final ResourceLocation witherTextures = new ResourceLocation("textures/entity/wither/wither.png");

	public RenderWither(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelWither(0.0F), 1.0F);
		this.addLayer(new LayerWitherAura(this));
	}

	public void doRender(EntityWither entitywither, double d0, double d1, double d2, float f, float f1) {
		BossStatus.setBossStatus(entitywither, true);
		super.doRender(entitywither, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityWither entitywither) {
		int i = entitywither.getInvulTime();
		return i > 0 && (i > 80 || i / 5 % 2 != 1) ? invulnerableWitherTextures : witherTextures;
	}

	protected void preRenderCallback(EntityWither entitywither, float f) {
		float f1 = 2.0F;
		int i = entitywither.getInvulTime();
		if (i > 0) {
			f1 -= ((float) i - f) / 220.0F * 0.5F;
		}

		GlStateManager.scale(f1, f1, f1);
	}
}
