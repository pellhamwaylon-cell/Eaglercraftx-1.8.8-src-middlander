package net.minecraft.client.gui.spectator.categories;

import java.util.List;

import com.google.common.base.Objects;

import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.SpectatorMenu;

public class SpectatorDetails {
	private final ISpectatorMenuView field_178684_a;
	private final List<ISpectatorMenuObject> field_178682_b;
	private final int field_178683_c;

	public SpectatorDetails(ISpectatorMenuView parISpectatorMenuView, List<ISpectatorMenuObject> parList, int parInt1) {
		this.field_178684_a = parISpectatorMenuView;
		this.field_178682_b = parList;
		this.field_178683_c = parInt1;
	}

	public ISpectatorMenuObject func_178680_a(int parInt1) {
		return parInt1 >= 0 && parInt1 < this.field_178682_b.size() ? (ISpectatorMenuObject) Objects
				.firstNonNull(this.field_178682_b.get(parInt1), SpectatorMenu.field_178657_a)
				: SpectatorMenu.field_178657_a;
	}

	public int func_178681_b() {
		return this.field_178683_c;
	}
}
