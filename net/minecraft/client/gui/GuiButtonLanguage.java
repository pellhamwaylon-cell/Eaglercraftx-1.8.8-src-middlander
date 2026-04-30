package net.minecraft.client.gui;

import net.lax1dude.eaglercraft.v1_8.Mouse;
import net.lax1dude.eaglercraft.v1_8.internal.EnumCursorType;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;

public class GuiButtonLanguage extends GuiButton {
	public GuiButtonLanguage(int buttonID, int xPos, int yPos) {
		super(buttonID, xPos, yPos, 20, 20, "");
	}

	public void drawButton(Minecraft minecraft, int i, int j) {
		if (this.visible) {
			minecraft.getTextureManager().bindTexture(GuiButton.buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			boolean flag = i >= this.xPosition && j >= this.yPosition && i < this.xPosition + this.width
					&& j < this.yPosition + this.height;
			int k = 106;
			if (flag) {
				k += this.height;
				Mouse.showCursor(EnumCursorType.HAND);
			}

			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, k, this.width, this.height);
		}
	}
}
