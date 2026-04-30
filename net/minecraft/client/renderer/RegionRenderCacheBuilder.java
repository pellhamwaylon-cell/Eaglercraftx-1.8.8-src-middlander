package net.minecraft.client.renderer;

import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.minecraft.util.EnumWorldBlockLayer;

public class RegionRenderCacheBuilder {
	private final WorldRenderer[] worldRenderers = new WorldRenderer[EnumWorldBlockLayer._VALUES.length];

	public RegionRenderCacheBuilder() {
		this.worldRenderers[EnumWorldBlockLayer.SOLID.ordinal()] = new WorldRenderer(2097152);
		this.worldRenderers[EnumWorldBlockLayer.CUTOUT.ordinal()] = new WorldRenderer(131072);
		this.worldRenderers[EnumWorldBlockLayer.CUTOUT_MIPPED.ordinal()] = new WorldRenderer(131072);
		this.worldRenderers[EnumWorldBlockLayer.TRANSLUCENT.ordinal()] = new WorldRenderer(262144);
		this.worldRenderers[EnumWorldBlockLayer.REALISTIC_WATER.ordinal()] = new WorldRenderer(262145);
		this.worldRenderers[EnumWorldBlockLayer.GLASS_HIGHLIGHTS.ordinal()] = new WorldRenderer(131072);
	}

	public WorldRenderer getWorldRendererByLayer(EnumWorldBlockLayer layer) {
		return this.worldRenderers[layer.ordinal()];
	}

	public WorldRenderer getWorldRendererByLayerId(int id) {
		return this.worldRenderers[id];
	}
}
