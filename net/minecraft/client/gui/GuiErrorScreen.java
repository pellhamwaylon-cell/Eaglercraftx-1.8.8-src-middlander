package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;

public class GuiErrorScreen extends GuiScreen {
	private String field_146313_a;
	private String field_146312_f;

	public GuiErrorScreen(String parString1, String parString2) {
		this.field_146313_a = parString1;
		this.field_146312_f = parString2;
	}

	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, 140, I18n.format("gui.cancel", new Object[0])));
	}

	public void drawScreen(int i, int j, float f) {
		this.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
		this.drawCenteredString(this.fontRendererObj, this.field_146313_a, this.width / 2, 90, 16777215);
		this.drawCenteredString(this.fontRendererObj, this.field_146312_f, this.width / 2, 110, 16777215);
		super.drawScreen(i, j, f);
	}

	protected void keyTyped(char parChar1, int parInt1) {
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		this.mc.displayGuiScreen((GuiScreen) null);
	}
}
