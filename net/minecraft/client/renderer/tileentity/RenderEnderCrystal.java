package net.minecraft.client.renderer.tileentity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderEnderCrystal extends Render<EntityEnderCrystal> {
	private static final ResourceLocation enderCrystalTextures = new ResourceLocation(
			"textures/entity/endercrystal/endercrystal.png");
	private ModelBase modelEnderCrystal = new ModelEnderCrystal(0.0F, true);

	public RenderEnderCrystal(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.shadowSize = 0.5F;
	}

	public void doRender(EntityEnderCrystal entityendercrystal, double d0, double d1, double d2, float f, float f1) {
		float f2 = (float) entityendercrystal.innerRotation + f1;
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) d0, (float) d1, (float) d2);
		this.bindTexture(enderCrystalTextures);
		float f3 = MathHelper.sin(f2 * 0.2F) / 2.0F + 0.5F;
		f3 = f3 * f3 + f3;
		this.modelEnderCrystal.render(entityendercrystal, 0.0F, f2 * 3.0F, f3 * 0.2F, 0.0F, 0.0F, 0.0625F);
		GlStateManager.popMatrix();
		super.doRender(entityendercrystal, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityEnderCrystal var1) {
		return enderCrystalTextures;
	}
}
