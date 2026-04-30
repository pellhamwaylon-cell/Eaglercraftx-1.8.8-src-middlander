package net.minecraft.client.renderer.entity.layers;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderSnowMan;
import net.minecraft.entity.monster.EntitySnowman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class LayerSnowmanHead implements LayerRenderer<EntitySnowman> {
	private final RenderSnowMan snowManRenderer;

	public LayerSnowmanHead(RenderSnowMan snowManRendererIn) {
		this.snowManRenderer = snowManRendererIn;
	}

	public void doRenderLayer(EntitySnowman entitysnowman, float var2, float var3, float var4, float var5, float var6,
			float var7, float var8) {
		if (!entitysnowman.isInvisible()) {
			GlStateManager.pushMatrix();
			this.snowManRenderer.getMainModel().head.postRender(0.0625F);
			float f = 0.625F;
			GlStateManager.translate(0.0F, -0.34375F, 0.0F);
			GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.scale(f, -f, -f);
			Minecraft.getMinecraft().getItemRenderer().renderItem(entitysnowman, new ItemStack(Blocks.pumpkin, 1),
					ItemCameraTransforms.TransformType.HEAD);
			GlStateManager.popMatrix();
		}
	}

	public boolean shouldCombineTextures() {
		return true;
	}
}
