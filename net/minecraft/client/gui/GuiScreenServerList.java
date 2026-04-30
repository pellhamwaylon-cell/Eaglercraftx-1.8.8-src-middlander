package net.minecraft.client.gui;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.minecraft.EnumInputEvent;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;

public class GuiScreenServerList extends GuiScreen {
	private final GuiScreen field_146303_a;
	private final ServerData field_146301_f;
	private GuiTextField field_146302_g;

	public GuiScreenServerList(GuiScreen parGuiScreen, ServerData parServerData) {
		this.field_146303_a = parGuiScreen;
		this.field_146301_f = parServerData;
	}

	public void updateScreen() {
		this.field_146302_g.updateCursorCounter();
	}

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + 12,
				I18n.format("selectServer.select", new Object[0])));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + 12,
				I18n.format("gui.cancel", new Object[0])));
		if (EagRuntime.requireSSL()) {
			this.field_146302_g = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, this.height / 4 + 35,
					200, 20);
		} else {
			this.field_146302_g = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 100, 116, 200, 20);
		}
		this.field_146302_g.setMaxStringLength(128);
		this.field_146302_g.setFocused(true);
		this.field_146302_g.setText(this.mc.gameSettings.lastServer);
		((GuiButton) this.buttonList.get(0)).enabled = this.field_146302_g.getText().trim().length() > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
		this.mc.gameSettings.lastServer = this.field_146302_g.getText();
		this.mc.gameSettings.saveOptions();
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 1) {
				this.field_146303_a.confirmClicked(false, 0);
			} else if (parGuiButton.id == 0) {
				this.field_146301_f.serverIP = this.field_146302_g.getText().trim();
				this.field_146303_a.confirmClicked(true, 0);
			}

		}
	}

	protected void keyTyped(char parChar1, int parInt1) {
		if (this.field_146302_g.textboxKeyTyped(parChar1, parInt1)) {
			((GuiButton) this.buttonList.get(0)).enabled = this.field_146302_g.getText().trim().length() > 0;
		} else if (parInt1 == 28 || parInt1 == 156) {
			this.actionPerformed((GuiButton) this.buttonList.get(0));
		}

	}

	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		super.mouseClicked(parInt1, parInt2, parInt3);
		this.field_146302_g.mouseClicked(parInt1, parInt2, parInt3);
	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("selectServer.direct", new Object[0]), this.width / 2,
				20, 16777215);
		if (EagRuntime.requireSSL()) {
			this.drawString(this.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), this.width / 2 - 100,
					this.height / 4 + 19, 10526880);
			this.drawCenteredString(this.fontRendererObj, I18n.format("addServer.SSLWarn1"), this.width / 2,
					this.height / 4 + 30 + 37, 0xccccff);
			this.drawCenteredString(this.fontRendererObj, I18n.format("addServer.SSLWarn2"), this.width / 2,
					this.height / 4 + 30 + 49, 0xccccff);
		} else {
			this.drawString(this.fontRendererObj, I18n.format("addServer.enterIp", new Object[0]), this.width / 2 - 100,
					100, 10526880);
		}
		this.field_146302_g.drawTextBox();
		super.drawScreen(i, j, f);
	}

	@Override
	public boolean showCopyPasteButtons() {
		return field_146302_g.isFocused();
	}

	@Override
	public void fireInputEvent(EnumInputEvent event, String param) {
		field_146302_g.fireInputEvent(event, param);
	}

}
