package net.minecraft.client.renderer;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.item.ItemStack;

public class ChestRenderer {
	public void renderChestBrightness(Block color, float parFloat1) {
		GlStateManager.color(parFloat1, parFloat1, parFloat1, 1.0F);
		GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
		TileEntityItemStackRenderer.instance.renderByItem(new ItemStack(color));
	}
}
