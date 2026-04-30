package net.minecraft.client.gui;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;

public class GuiResourcePackSelected extends GuiResourcePackList {
	public GuiResourcePackSelected(Minecraft mcIn, int parInt1, int parInt2, List<ResourcePackListEntry> parList) {
		super(mcIn, parInt1, parInt2, parList);
	}

	protected String getListHeader() {
		return I18n.format("resourcePack.selected.title", new Object[0]);
	}
}
