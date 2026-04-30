package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.ResourceLocation;

public class LayerWolfCollar implements LayerRenderer<EntityWolf> {
	private static final ResourceLocation WOLF_COLLAR = new ResourceLocation("textures/entity/wolf/wolf_collar.png");
	private final RenderWolf wolfRenderer;

	public LayerWolfCollar(RenderWolf wolfRendererIn) {
		this.wolfRenderer = wolfRendererIn;
	}

	public void doRenderLayer(EntityWolf entitywolf, float f, float f1, float var4, float f2, float f3, float f4,
			float f5) {
		if (entitywolf.isTamed() && !entitywolf.isInvisible()) {
			this.wolfRenderer.bindTexture(WOLF_COLLAR);
			EnumDyeColor enumdyecolor = EnumDyeColor.byMetadata(entitywolf.getCollarColor().getMetadata());
			float[] afloat = EntitySheep.func_175513_a(enumdyecolor);
			GlStateManager.color(afloat[0], afloat[1], afloat[2]);
			this.wolfRenderer.getMainModel().render(entitywolf, f, f1, f2, f3, f4, f5);
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}
}
