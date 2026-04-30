package net.minecraft.client.renderer.chunk;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class ListedRenderChunk extends RenderChunk {
	private final int[] baseDisplayList;

	public ListedRenderChunk(World worldIn, RenderGlobal renderGlobalIn, BlockPos pos, int indexIn) {
		super(worldIn, renderGlobalIn, pos, indexIn);
		this.baseDisplayList = new int[EnumWorldBlockLayer._VALUES.length];
		for (int i = 0; i < this.baseDisplayList.length; ++i) {
			this.baseDisplayList[i] = GLAllocation.generateDisplayLists();
		}
	}

	public int getDisplayList(EnumWorldBlockLayer layer, CompiledChunk parCompiledChunk) {
		return !parCompiledChunk.isLayerEmpty(layer) ? this.baseDisplayList[layer.ordinal()] : -1;
	}

	public void deleteGlResources() {
		super.deleteGlResources();
		for (int i = 0; i < this.baseDisplayList.length; ++i) {
			GLAllocation.deleteDisplayLists(this.baseDisplayList[i]);
		}
	}

	public void rebuildChunk(float x, float y, float z, ChunkCompileTaskGenerator generator) {
		super.rebuildChunk(x, y, z, generator);
		EnumWorldBlockLayer[] layers = EnumWorldBlockLayer._VALUES;
		for (int i = 0; i < layers.length; ++i) {
			if (generator.getCompiledChunk().isLayerEmpty(layers[i])) {
				EaglercraftGPU.flushDisplayList(this.baseDisplayList[i]);
			}
		}
	}
}
