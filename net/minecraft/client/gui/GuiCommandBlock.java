package net.minecraft.client.gui;

import net.lax1dude.eaglercraft.v1_8.netty.Unpooled;
import net.lax1dude.eaglercraft.v1_8.Keyboard;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.minecraft.EnumInputEvent;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.IChatComponent;

public class GuiCommandBlock extends GuiScreen {
	private static final Logger field_146488_a = LogManager.getLogger();
	private GuiTextField commandTextField;
	private GuiTextField previousOutputTextField;
	private final CommandBlockLogic localCommandBlock;
	private GuiButton doneBtn;
	private GuiButton cancelBtn;
	private GuiButton field_175390_s;
	private boolean field_175389_t;

	public GuiCommandBlock(CommandBlockLogic parCommandBlockLogic) {
		this.localCommandBlock = parCommandBlockLogic;
	}

	public void updateScreen() {
		this.commandTextField.updateCursorCounter();
	}

	public void initGui() {
		Keyboard.enableRepeatEvents(true);
		this.buttonList.clear();
		this.buttonList.add(this.doneBtn = new GuiButton(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150,
				20, I18n.format("gui.done", new Object[0])));
		this.buttonList.add(this.cancelBtn = new GuiButton(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20,
				I18n.format("gui.cancel", new Object[0])));
		this.buttonList.add(this.field_175390_s = new GuiButton(4, this.width / 2 + 150 - 20, 150, 20, 20, "O"));
		this.commandTextField = new GuiTextField(2, this.fontRendererObj, this.width / 2 - 150, 50, 300, 20);
		this.commandTextField.setMaxStringLength(32767);
		this.commandTextField.setFocused(true);
		this.commandTextField.setText(this.localCommandBlock.getCommand());
		this.previousOutputTextField = new GuiTextField(3, this.fontRendererObj, this.width / 2 - 150, 150, 276, 20);
		this.previousOutputTextField.setMaxStringLength(32767);
		this.previousOutputTextField.setEnabled(false);
		this.previousOutputTextField.setText("-");
		this.field_175389_t = this.localCommandBlock.shouldTrackOutput();
		this.func_175388_a();
		this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
	}

	public void onGuiClosed() {
		Keyboard.enableRepeatEvents(false);
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.enabled) {
			if (parGuiButton.id == 1) {
				this.localCommandBlock.setTrackOutput(this.field_175389_t);
				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (parGuiButton.id == 0) {
				PacketBuffer packetbuffer = new PacketBuffer(Unpooled.buffer());
				packetbuffer.writeByte(this.localCommandBlock.func_145751_f());
				this.localCommandBlock.func_145757_a(packetbuffer);
				packetbuffer.writeString(this.commandTextField.getText());
				packetbuffer.writeBoolean(this.localCommandBlock.shouldTrackOutput());
				this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("MC|AdvCdm", packetbuffer));
				if (!this.localCommandBlock.shouldTrackOutput()) {
					this.localCommandBlock.setLastOutput((IChatComponent) null);
				}

				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (parGuiButton.id == 4) {
				this.localCommandBlock.setTrackOutput(!this.localCommandBlock.shouldTrackOutput());
				this.func_175388_a();
			}

		}
	}

	protected void keyTyped(char parChar1, int parInt1) {
		this.commandTextField.textboxKeyTyped(parChar1, parInt1);
		this.previousOutputTextField.textboxKeyTyped(parChar1, parInt1);
		this.doneBtn.enabled = this.commandTextField.getText().trim().length() > 0;
		if (parInt1 != 28 && parInt1 != 156) {
			if (parInt1 == 1) {
				this.actionPerformed(this.cancelBtn);
			}
		} else {
			this.actionPerformed(this.doneBtn);
		}

	}

	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		super.mouseClicked(parInt1, parInt2, parInt3);
		this.commandTextField.mouseClicked(parInt1, parInt2, parInt3);
		this.previousOutputTextField.mouseClicked(parInt1, parInt2, parInt3);
	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, I18n.format("advMode.setCommand", new Object[0]), this.width / 2,
				20, 16777215);
		this.drawString(this.fontRendererObj, I18n.format("advMode.command", new Object[0]), this.width / 2 - 150, 37,
				10526880);
		this.commandTextField.drawTextBox();
		int k = 75;
		int l = 0;
		this.drawString(this.fontRendererObj, I18n.format("advMode.nearestPlayer", new Object[0]), this.width / 2 - 150,
				k + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj, I18n.format("advMode.randomPlayer", new Object[0]), this.width / 2 - 150,
				k + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj, I18n.format("advMode.allPlayers", new Object[0]), this.width / 2 - 150,
				k + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj, I18n.format("advMode.allEntities", new Object[0]), this.width / 2 - 150,
				k + l++ * this.fontRendererObj.FONT_HEIGHT, 10526880);
		this.drawString(this.fontRendererObj, "", this.width / 2 - 150, k + l++ * this.fontRendererObj.FONT_HEIGHT,
				10526880);
		if (this.previousOutputTextField.getText().length() > 0) {
			k = k + l * this.fontRendererObj.FONT_HEIGHT + 16;
			this.drawString(this.fontRendererObj, I18n.format("advMode.previousOutput", new Object[0]),
					this.width / 2 - 150, k, 10526880);
			this.previousOutputTextField.drawTextBox();
		}

		super.drawScreen(i, j, f);
	}

	private void func_175388_a() {
		if (this.localCommandBlock.shouldTrackOutput()) {
			this.field_175390_s.displayString = "O";
			if (this.localCommandBlock.getLastOutput() != null) {
				this.previousOutputTextField.setText(this.localCommandBlock.getLastOutput().getUnformattedText());
			}
		} else {
			this.field_175390_s.displayString = "X";
			this.previousOutputTextField.setText("-");
		}

	}

	public boolean blockPTTKey() {
		return commandTextField.isFocused() || previousOutputTextField.isFocused();
	}

	@Override
	public boolean showCopyPasteButtons() {
		return commandTextField.isFocused() || previousOutputTextField.isFocused();
	}

	@Override
	public void fireInputEvent(EnumInputEvent event, String param) {
		commandTextField.fireInputEvent(event, param);
		previousOutputTextField.fireInputEvent(event, param);
	}

}
