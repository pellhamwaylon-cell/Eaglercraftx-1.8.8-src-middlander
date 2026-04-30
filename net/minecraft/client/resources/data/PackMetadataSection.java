package net.minecraft.client.resources.data;

import net.minecraft.util.IChatComponent;

public class PackMetadataSection implements IMetadataSection {
	private final IChatComponent packDescription;
	private final int packFormat;

	public PackMetadataSection(IChatComponent parIChatComponent, int parInt1) {
		this.packDescription = parIChatComponent;
		this.packFormat = parInt1;
	}

	public IChatComponent getPackDescription() {
		return this.packDescription;
	}

	public int getPackFormat() {
		return this.packFormat;
	}
}
