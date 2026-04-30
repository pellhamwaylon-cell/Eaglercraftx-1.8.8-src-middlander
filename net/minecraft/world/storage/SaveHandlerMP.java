package net.minecraft.world.storage;

import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFile2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public class SaveHandlerMP implements ISaveHandler {
	public WorldInfo loadWorldInfo() {
		return null;
	}

	public void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2) {
	}

	public void saveWorldInfo(WorldInfo var1) {
	}

	public IPlayerFileData getPlayerNBTManager() {
		return null;
	}

	public void flush() {
	}

	public String getWorldDirectoryName() {
		return "none";
	}

	@Override
	public IChunkLoader getChunkLoader(WorldProvider var1) {
		return null;
	}

	@Override
	public VFile2 getWorldDirectory() {
		return null;
	}

	@Override
	public VFile2 getMapFileFromName(String var1) {
		return null;
	}

}
