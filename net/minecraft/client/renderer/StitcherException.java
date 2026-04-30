package net.minecraft.client.renderer;

import net.minecraft.client.renderer.texture.Stitcher;

public class StitcherException extends RuntimeException {
	private final Stitcher.Holder holder;

	public StitcherException(Stitcher.Holder parHolder, String parString1) {
		super(parString1);
		this.holder = parHolder;
	}
}
