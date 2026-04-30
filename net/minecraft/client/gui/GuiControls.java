package net.minecraft.client.gui;

import java.io.IOException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;

public class GuiControls extends GuiScreen {
	private static final GameSettings.Options[] optionsArr = new GameSettings.Options[] {
			GameSettings.Options.INVERT_MOUSE, GameSettings.Options.SENSITIVITY,
			GameSettings.Options.EAGLER_TOUCH_CONTROL_OPACITY };
	private GuiScreen parentScreen;
	protected String screenTitle = "Controls";
	private GameSettings options;
	public KeyBinding buttonId = null;
	public long time;
	private GuiKeyBindingList keyBindingList;
	private GuiButton buttonReset;

	public GuiControls(GuiScreen screen, GameSettings settings) {
		this.parentScreen = screen;
		this.options = settings;
	}

	public void initGui() {
		this.keyBindingList = new GuiKeyBindingList(this, this.mc);
		this.buttonList.add(new GuiButton(200, this.width / 2 - 155, this.height - 29, 150, 20,
				I18n.format("gui.done", new Object[0])));
		this.buttonList.add(this.buttonReset = new GuiButton(201, this.width / 2 - 155 + 160, this.height - 29, 150, 20,
				I18n.format("controls.resetAll", new Object[0])));
		this.screenTitle = I18n.format("controls.title", new Object[0]);
		int i = 0;

		for (GameSettings.Options gamesettings$options : optionsArr) {
			if (gamesettings$options.getEnumFloat()) {
				this.buttonList.add(new GuiOptionSlider(gamesettings$options.returnEnumOrdinal(),
						this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options));
			} else {
				this.buttonList.add(new GuiOptionButton(gamesettings$options.returnEnumOrdinal(),
						this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options,
						this.options.getKeyBinding(gamesettings$options)));
			}

			++i;
		}

	}

	public void handleMouseInput() throws IOException {
		super.handleMouseInput();
		this.keyBindingList.handleMouseInput();
	}

	public void handleTouchInput() throws IOException {
		super.handleTouchInput();
		this.keyBindingList.handleTouchInput();
	}

	protected void actionPerformed(GuiButton parGuiButton) {
		if (parGuiButton.id == 200) {
			this.mc.displayGuiScreen(this.parentScreen);
		} else if (parGuiButton.id == 201) {
			KeyBinding[] arr = this.mc.gameSettings.keyBindings;
			for (int i = 0; i < arr.length; ++i) {
				arr[i].setKeyCode(arr[i].getKeyCodeDefault());
			}

			KeyBinding.resetKeyBindingArrayAndHash();
		} else if (parGuiButton.id < 100 && parGuiButton instanceof GuiOptionButton) {
			this.options.setOptionValue(((GuiOptionButton) parGuiButton).returnEnumOptions(), 1);
			parGuiButton.displayString = this.options
					.getKeyBinding(GameSettings.Options.getEnumOptions(parGuiButton.id));
		}

	}

	protected void mouseClicked(int parInt1, int parInt2, int parInt3) {
		if (this.buttonId != null) {
			this.options.setOptionKeyBinding(this.buttonId, -100 + parInt3);
			this.buttonId = null;
			KeyBinding.resetKeyBindingArrayAndHash();
		} else if (parInt3 != 0 || !this.keyBindingList.mouseClicked(parInt1, parInt2, parInt3)) {
			super.mouseClicked(parInt1, parInt2, parInt3);
		}

	}

	protected void mouseReleased(int i, int j, int k) {
		if (k != 0 || !this.keyBindingList.mouseReleased(i, j, k)) {
			super.mouseReleased(i, j, k);
		}

	}

	protected void keyTyped(char parChar1, int parInt1) {
		if (this.buttonId != null) {
			if (parInt1 == 1) {
				this.options.setOptionKeyBinding(this.buttonId, 0);
			} else if (parInt1 != 0) {
				this.options.setOptionKeyBinding(this.buttonId, parInt1);
			} else if (parChar1 > 0) {
				this.options.setOptionKeyBinding(this.buttonId, parChar1 + 256);
			}

			this.buttonId = null;
			this.time = Minecraft.getSystemTime();
			KeyBinding.resetKeyBindingArrayAndHash();
		} else {
			super.keyTyped(parChar1, parInt1);
		}

	}

	public void drawScreen(int i, int j, float f) {
		this.drawDefaultBackground();
		this.keyBindingList.drawScreen(i, j, f);
		this.drawCenteredString(this.fontRendererObj, this.screenTitle, this.width / 2, 8, 16777215);
		boolean flag = true;

		KeyBinding[] arr = this.options.keyBindings;
		for (int k = 0; k < arr.length; ++k) {
			if (arr[k].getKeyCode() != arr[k].getKeyCodeDefault()) {
				flag = false;
				break;
			}
		}

		this.buttonReset.enabled = !flag;
		super.drawScreen(i, j, f);
	}
}
