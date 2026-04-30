package net.minecraft.client.gui;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.sp.SingleplayerServerController;
import net.lax1dude.eaglercraft.v1_8.sp.gui.GuiScreenIntegratedServerBusy;
import net.lax1dude.eaglercraft.v1_8.sp.ipc.IPCPacket15Crashed;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class GuiDisconnected extends GuiScreen {
	private String reason;
	private IChatComponent message;
	private List<String> multilineMessage;
	private final GuiScreen parentScreen;
	private int field_175353_i;

	public GuiDisconnected(GuiScreen screen, String reasonLocalizationKey, IChatComponent chatComp) {
		this.parentScreen = screen;
		this.reason = I18n.format(reasonLocalizationKey, new Object[0]);
		this.message = chatComp;
	}

	protected void keyTyped(char parChar1, int parInt1) {
	}

	public void initGui() {
		this.buttonList.clear();
		this.multilineMessage = this.fontRendererObj.listFormattedStringToWidth(this.message.getFormattedText(),
				this.width - 50);
		this.field_175353_i = this.multilineMessage.size() * this.fontRendererObj.FONT_HEIGHT;
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100,
				this.height / 2 + this.field_175353_i / 2 + this.fontRendererObj.FONT_HEIGHT,
				I18n.format("gui.toMenu", new Object[0])));
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.id == 0) {
			this.mc.displayGuiScreen(this.parentScreen);
		}

	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, this.reason, this.width / 2,
				this.height / 2 - this.field_175353_i / 2 - this.fontRendererObj.FONT_HEIGHT * 2, 11184810);
		int k = this.height / 2 - this.field_175353_i / 2;
		if (this.multilineMessage != null) {
			for (int l = 0, m = this.multilineMessage.size(); l < m; ++l) {
				this.drawCenteredString(this.fontRendererObj, this.multilineMessage.get(l), this.width / 2, k,
						16777215);
				k += this.fontRendererObj.FONT_HEIGHT;
			}
		}

		super.drawScreen(i, j, f);
	}

	public void updateScreen() {
		IPCPacket15Crashed[] pkt = SingleplayerServerController.worldStatusErrors();
		if (pkt != null && pkt.length > 0) {
			mc.displayGuiScreen(
					GuiScreenIntegratedServerBusy.createException(this, "singleplayer.failed.serverCrash", pkt));
		}
	}

	public static GuiScreen createRateLimitKick(GuiScreen prev) {
		return new GuiDisconnected(prev, "connect.failed", new ChatComponentTranslation("disconnect.tooManyRequests"));
	}
}
