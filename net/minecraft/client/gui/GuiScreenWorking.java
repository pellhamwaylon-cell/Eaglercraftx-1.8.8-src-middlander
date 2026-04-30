package net.minecraft.client.gui;

import net.minecraft.util.IProgressUpdate;

public class GuiScreenWorking extends GuiScreen implements IProgressUpdate {
	private String field_146591_a = "";
	private String field_146589_f = "";
	private int progress;
	private boolean doneWorking;

	public void displaySavingString(String s) {
		this.resetProgressAndMessage(s);
	}

	public void resetProgressAndMessage(String s) {
		this.field_146591_a = s;
		this.displayLoadingString("Working...");
	}

	public void displayLoadingString(String s) {
		this.field_146589_f = s;
		this.setLoadingProgress(0);
	}

	public void setLoadingProgress(int i) {
		this.progress = i;
	}

	public void setDoneWorking() {
		this.doneWorking = true;
	}

	public void drawScreen(int i, int j, float f) {
		if (this.doneWorking) {
			if (!this.mc.func_181540_al()) {
				this.mc.displayGuiScreen((GuiScreen) null);
			}

		} else {
			this.drawDefaultBackground();
			this.drawCenteredString(this.fontRendererObj, this.field_146591_a, this.width / 2, 70, 16777215);
			this.drawCenteredString(this.fontRendererObj, this.field_146589_f + " " + this.progress + "%",
					this.width / 2, 90, 16777215);
			super.drawScreen(i, j, f);
		}
	}
}
