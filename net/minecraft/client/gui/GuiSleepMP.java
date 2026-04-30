package net.minecraft.client.gui;

import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.client.C0BPacketEntityAction;

public class GuiSleepMP extends GuiChat {
	public void initGui() {
		super.initGui();
		this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height - 40,
				I18n.format("multiplayer.stopSleeping", new Object[0])));
	}

	protected void keyTyped(char parChar1, int parInt1) {
		if (parInt1 == 1) {
			this.wakeFromSleep();
		} else if (parInt1 != 28 && parInt1 != 156) {
			super.keyTyped(parChar1, parInt1);
		} else {
			String s = this.inputField.getText().trim();
			if (!s.isEmpty()) {
				this.mc.thePlayer.sendChatMessage(s);
			}

			this.inputField.setText("");
			this.mc.ingameGUI.getChatGUI().resetScroll();
		}

	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.id == 1) {
			this.wakeFromSleep();
		} else {
			super.actionPerformed(parGuiButton);
		}

	}

	private void wakeFromSleep() {
		NetHandlerPlayClient nethandlerplayclient = this.mc.thePlayer.sendQueue;
		nethandlerplayclient.addToSendQueue(
				new C0BPacketEntityAction(this.mc.thePlayer, C0BPacketEntityAction.Action.STOP_SLEEPING));
	}
}
