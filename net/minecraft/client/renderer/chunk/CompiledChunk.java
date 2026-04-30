package net.minecraft.client.renderer.chunk;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;

public class CompiledChunk {
	public static final CompiledChunk DUMMY = new CompiledChunk(null) {
		protected void setLayerUsed(EnumWorldBlockLayer layer) {
			throw new UnsupportedOperationException();
		}

		public void setLayerStarted(EnumWorldBlockLayer layer) {
			throw new UnsupportedOperationException();
		}

		public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
			return true;
		}
	};
	private final RenderChunk chunk;
	private final boolean[] layersUsed = new boolean[EnumWorldBlockLayer._VALUES.length];
	private final boolean[] layersStarted = new boolean[EnumWorldBlockLayer._VALUES.length];
	private boolean empty = true;
	private final List<TileEntity> tileEntities = Lists.newArrayList();
	private SetVisibility setVisibility = new SetVisibility();
	private WorldRenderer.State state;
	private WorldRenderer.State stateWater;

	public CompiledChunk(RenderChunk chunk) {
		this.chunk = chunk;
	}

	public void reset() {
		Arrays.fill(layersUsed, false);
		Arrays.fill(layersStarted, false);
		empty = true;
		tileEntities.clear();
		setVisibility.setAllVisible(false);
		setState(null);
		setStateRealisticWater(null);
	}

	public boolean isEmpty() {
		return this.empty;
	}

	protected void setLayerUsed(EnumWorldBlockLayer enumworldblocklayer) {
		this.empty = false;
		this.layersUsed[enumworldblocklayer.ordinal()] = true;
	}

	public boolean isLayerEmpty(EnumWorldBlockLayer layer) {
		return !this.layersUsed[layer.ordinal()];
	}

	public void setLayerStarted(EnumWorldBlockLayer enumworldblocklayer) {
		this.layersStarted[enumworldblocklayer.ordinal()] = true;
	}

	public boolean isLayerStarted(EnumWorldBlockLayer layer) {
		return this.layersStarted[layer.ordinal()];
	}

	public List<TileEntity> getTileEntities() {
		return this.tileEntities;
	}

	public void addTileEntity(TileEntity tileEntityIn) {
		this.tileEntities.add(tileEntityIn);
	}

	public boolean isVisible(EnumFacing enumfacing, EnumFacing enumfacing1) {
		return this.setVisibility.isVisible(enumfacing, enumfacing1);
	}

	public void setVisibility(SetVisibility visibility) {
		this.setVisibility = visibility;
	}

	public WorldRenderer.State getState() {
		return this.state;
	}

	public void setState(WorldRenderer.State stateIn) {
		if (this.state != stateIn && this.state != null) {
			this.state.release();
		}
		this.state = stateIn;
	}

	public WorldRenderer.State getStateRealisticWater() {
		return this.stateWater;
	}

	public void setStateRealisticWater(WorldRenderer.State stateIn) {
		if (this.stateWater != stateIn && this.stateWater != null) {
			this.stateWater.release();
		}
		this.stateWater = stateIn;
	}
}
