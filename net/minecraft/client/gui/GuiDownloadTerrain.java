package net.minecraft.client.gui;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C00PacketKeepAlive;

public class GuiDownloadTerrain extends GuiScreen {
	private NetHandlerPlayClient netHandlerPlayClient;
	private int progress;

	public GuiDownloadTerrain(NetHandlerPlayClient netHandler) {
		this.netHandlerPlayClient = netHandler;
	}

	protected void keyTyped(char parChar1, int parInt1) {
	}

	public void initGui() {
		this.buttonList.clear();
	}

	public void updateScreen() {
		++this.progress;
		if (this.progress % 20 == 0) {
			this.netHandlerPlayClient.addToSendQueue(new C00PacketKeepAlive());
		}

	}

	public void drawScreen(int i, int j, float f) {
		this.drawBackground(0);
		this.drawCenteredString(this.fontRendererObj, I18n.format("multiplayer.downloadingTerrain", new Object[0]),
				this.width / 2, this.height / 2 - 50, 16777215);
		super.drawScreen(i, j, f);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}

	public boolean shouldHangupIntegratedServer() {
		return false;
	}

	public boolean canCloseGui() {
		return false;
	}

}
