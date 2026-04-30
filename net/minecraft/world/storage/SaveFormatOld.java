package net.minecraft.world.storage;

import com.google.common.collect.Lists;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.lax1dude.eaglercraft.v1_8.sp.server.EaglerIntegratedServerWorker;
import net.lax1dude.eaglercraft.v1_8.sp.server.WorldsDB;
import net.minecraft.util.IProgressUpdate;
import net.lax1dude.eaglercraft.v1_8.EagUtils;
import net.lax1dude.eaglercraft.v1_8.internal.vfs2.VFile2;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class SaveFormatOld implements ISaveFormat {
	private static final Logger logger = LogManager.getLogger();
	protected final VFile2 savesDirectory;

	public SaveFormatOld(VFile2 parFile) {
		this.savesDirectory = parFile;
	}

	public String getName() {
		return "Old Format";
	}

	public List<SaveFormatComparator> getSaveList() {
		ArrayList arraylist = Lists.newArrayList();

		for (int i = 0; i < 5; ++i) {
			String s = "World" + (i + 1);
			WorldInfo worldinfo = this.getWorldInfo(s);
			if (worldinfo != null) {
				arraylist.add(new SaveFormatComparator(s, "", worldinfo.getLastTimePlayed(), worldinfo.getSizeOnDisk(),
						worldinfo.getGameType(), false, worldinfo.isHardcoreModeEnabled(),
						worldinfo.areCommandsAllowed(), null));
			}
		}

		return arraylist;
	}

	public void flushCache() {
	}

	public WorldInfo getWorldInfo(String saveName) {
		VFile2 file1 = WorldsDB.newVFile(this.savesDirectory, saveName);
		VFile2 file2 = WorldsDB.newVFile(file1, "level.dat");
		if (file2.exists()) {
			try {
				NBTTagCompound nbttagcompound2;
				try (InputStream is = file2.getInputStream()) {
					nbttagcompound2 = CompressedStreamTools.readCompressed(is);
				}
				NBTTagCompound nbttagcompound3 = nbttagcompound2.getCompoundTag("Data");
				return new WorldInfo(nbttagcompound3);
			} catch (Exception exception1) {
				logger.error("Exception reading " + file2);
				logger.error(exception1);
			}
		}

		file2 = WorldsDB.newVFile(file1, "level.dat_old");
		if (file2.exists()) {
			try {
				NBTTagCompound nbttagcompound;
				try (InputStream is = file2.getInputStream()) {
					nbttagcompound = CompressedStreamTools.readCompressed(is);
				}
				NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
				return new WorldInfo(nbttagcompound1);
			} catch (Exception exception) {
				logger.error("Exception reading " + file2);
				logger.error(exception);
			}
		}

		return null;
	}

	public boolean renameWorld(String dirName, String newName) {
		VFile2 file1 = WorldsDB.newVFile(this.savesDirectory, dirName);
		VFile2 file2 = WorldsDB.newVFile(file1, "level.dat");
		{
			if (file2.exists()) {
				try {
					NBTTagCompound nbttagcompound;
					try (InputStream is = file2.getInputStream()) {
						nbttagcompound = CompressedStreamTools.readCompressed(is);
					}
					NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Data");
					nbttagcompound1.setString("LevelName", newName);
					try (OutputStream os = file2.getOutputStream()) {
						CompressedStreamTools.writeCompressed(nbttagcompound, os);
					}
					return true;
				} catch (Throwable exception) {
					logger.error("Failed to rename world \"{}\"!", dirName);
					logger.error(exception);
				}
			}

		}
		return false;
	}

	public boolean func_154335_d(String parString1) {
		return !canLoadWorld(parString1);
	}

	public boolean deleteWorldDirectory(String parString1) {
		VFile2 file1 = WorldsDB.newVFile(this.savesDirectory, parString1);
		logger.info("Deleting level " + parString1);

		for (int i = 1; i <= 5; ++i) {
			logger.info("Attempt " + i + "...");
			if (deleteFiles(file1.listFiles(true), "singleplayer.busy.deleting")) {
				return true;
			}

			logger.warn("Unsuccessful in deleting contents.");
			if (i < 5) {
				EagUtils.sleep(500);
			}
		}

		return false;
	}

	protected static boolean deleteFiles(List<VFile2> files, String progressString) {
		long totalSize = 0l;
		long lastUpdate = 0;
		for (int i = 0, l = files.size(); i < l; ++i) {
			VFile2 file1 = files.get(i);
			if (progressString != null) {
				totalSize += file1.length();
				if (totalSize - lastUpdate > 10000) {
					lastUpdate = totalSize;
					EaglerIntegratedServerWorker.sendProgress(progressString, totalSize);
				}
			}
			if (!file1.delete()) {
				logger.warn("Couldn\'t delete file " + file1);
				return false;
			}
		}

		return true;
	}

	public ISaveHandler getSaveLoader(String s, boolean flag) {
		return new SaveHandler(this.savesDirectory, s);
	}

	public boolean func_154334_a(String var1) {
		return false;
	}

	public boolean isOldMapFormat(String var1) {
		return false;
	}

	public boolean convertMapFormat(String var1, IProgressUpdate var2) {
		return false;
	}

	public boolean canLoadWorld(String parString1) {
		return (WorldsDB.newVFile(this.savesDirectory, parString1, "level.dat")).exists()
				|| (WorldsDB.newVFile(this.savesDirectory, parString1, "level.dat_old")).exists();
	}
}
