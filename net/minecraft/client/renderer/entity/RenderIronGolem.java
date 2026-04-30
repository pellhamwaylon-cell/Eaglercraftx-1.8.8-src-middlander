package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelIronGolem;
import net.minecraft.client.renderer.entity.layers.LayerIronGolemFlower;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.util.ResourceLocation;

public class RenderIronGolem extends RenderLiving<EntityIronGolem> {
	private static final ResourceLocation ironGolemTextures = new ResourceLocation("textures/entity/iron_golem.png");

	public RenderIronGolem(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelIronGolem(), 0.5F);
		this.addLayer(new LayerIronGolemFlower(this));
	}

	protected ResourceLocation getEntityTexture(EntityIronGolem var1) {
		return ironGolemTextures;
	}

	protected void rotateCorpse(EntityIronGolem entityirongolem, float f, float f1, float f2) {
		super.rotateCorpse(entityirongolem, f, f1, f2);
		if ((double) entityirongolem.limbSwingAmount >= 0.01D) {
			float f3 = 13.0F;
			float f4 = entityirongolem.limbSwing - entityirongolem.limbSwingAmount * (1.0F - f2) + 6.0F;
			float f5 = (Math.abs(f4 % f3 - f3 * 0.5F) - f3 * 0.25F) / (f3 * 0.25F);
			GlStateManager.rotate(6.5F * f5, 0.0F, 0.0F, 1.0F);
		}
	}
}
