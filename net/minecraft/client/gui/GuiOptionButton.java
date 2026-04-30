package net.minecraft.client.gui;

import net.minecraft.client.settings.GameSettings;

public class GuiOptionButton extends GuiButton {
	private final GameSettings.Options enumOptions;

	public GuiOptionButton(int parInt1, int parInt2, int parInt3, String parString1) {
		this(parInt1, parInt2, parInt3, (GameSettings.Options) null, parString1);
	}

	public GuiOptionButton(int parInt1, int parInt2, int parInt3, int parInt4, int parInt5, String parString1) {
		super(parInt1, parInt2, parInt3, parInt4, parInt5, parString1);
		this.enumOptions = null;
	}

	public GuiOptionButton(int parInt1, int parInt2, int parInt3, GameSettings.Options parOptions, String parString1) {
		super(parInt1, parInt2, parInt3, 150, 20, parString1);
		this.enumOptions = parOptions;
	}

	public GameSettings.Options returnEnumOptions() {
		return this.enumOptions;
	}
}
