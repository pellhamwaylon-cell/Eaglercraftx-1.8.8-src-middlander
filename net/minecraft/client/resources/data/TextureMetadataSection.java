package net.minecraft.client.resources.data;

import com.carrotsearch.hppc.IntIndexedContainer;

public class TextureMetadataSection implements IMetadataSection {
	private final boolean textureBlur;
	private final boolean textureClamp;
	private final IntIndexedContainer listMipmaps;

	public TextureMetadataSection(boolean parFlag, boolean parFlag2, IntIndexedContainer parList) {
		this.textureBlur = parFlag;
		this.textureClamp = parFlag2;
		this.listMipmaps = parList;
	}

	public boolean getTextureBlur() {
		return this.textureBlur;
	}

	public boolean getTextureClamp() {
		return this.textureClamp;
	}

	public IntIndexedContainer getListMipmaps() {
		return listMipmaps;
	}
}
