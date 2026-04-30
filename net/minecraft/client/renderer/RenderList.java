package net.minecraft.client.renderer;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.util.EnumWorldBlockLayer;

public class RenderList extends ChunkRenderContainer {
	public void renderChunkLayer(EnumWorldBlockLayer enumworldblocklayer) {
		if (this.initialized) {
			for (int i = 0, l = this.renderChunks.size(); i < l; ++i) {
				ListedRenderChunk listedrenderchunk = (ListedRenderChunk) this.renderChunks.get(i);
				GlStateManager.pushMatrix();
				this.preRenderChunk(listedrenderchunk, enumworldblocklayer);
				EaglercraftGPU.glCallList(
						listedrenderchunk.getDisplayList(enumworldblocklayer, listedrenderchunk.getCompiledChunk()));
				GlStateManager.popMatrix();
			}

			GlStateManager.resetColor();
			this.renderChunks.clear();
		}
	}
}
