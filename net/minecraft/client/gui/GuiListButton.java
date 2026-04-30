package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;

public class GuiListButton extends GuiButton {
	private boolean field_175216_o;
	private String localizationStr;
	private final GuiPageButtonList.GuiResponder guiResponder;

	public GuiListButton(GuiPageButtonList.GuiResponder responder, int parInt1, int parInt2, int parInt3,
			String parString1, boolean parFlag) {
		super(parInt1, parInt2, parInt3, 150, 20, "");
		this.localizationStr = parString1;
		this.field_175216_o = parFlag;
		this.displayString = this.buildDisplayString();
		this.guiResponder = responder;
	}

	private String buildDisplayString() {
		return I18n.format(this.localizationStr, new Object[0]) + ": "
				+ (this.field_175216_o ? I18n.format("gui.yes", new Object[0]) : I18n.format("gui.no", new Object[0]));
	}

	public void func_175212_b(boolean parFlag) {
		this.field_175216_o = parFlag;
		this.displayString = this.buildDisplayString();
		this.guiResponder.func_175321_a(this.id, parFlag);
	}

	public boolean mousePressed(Minecraft minecraft, int i, int j) {
		if (super.mousePressed(minecraft, i, j)) {
			this.field_175216_o = !this.field_175216_o;
			this.displayString = this.buildDisplayString();
			this.guiResponder.func_175321_a(this.id, this.field_175216_o);
			return true;
		} else {
			return false;
		}
	}
}
