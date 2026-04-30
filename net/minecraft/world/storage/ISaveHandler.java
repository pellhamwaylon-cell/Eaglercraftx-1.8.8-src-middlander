package net.minecraft.world.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFile2;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.storage.IChunkLoader;

public interface ISaveHandler {
	WorldInfo loadWorldInfo();

	IChunkLoader getChunkLoader(WorldProvider var1);

	void saveWorldInfoWithPlayer(WorldInfo var1, NBTTagCompound var2);

	void saveWorldInfo(WorldInfo var1);

	IPlayerFileData getPlayerNBTManager();

	void flush();

	VFile2 getWorldDirectory();

	VFile2 getMapFileFromName(String var1);

	String getWorldDirectoryName();
}
