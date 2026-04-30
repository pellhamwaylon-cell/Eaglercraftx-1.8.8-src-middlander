package net.minecraft.world.storage;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.WorldSettings;

public class SaveFormatComparator implements Comparable<SaveFormatComparator> {
	private final String fileName;
	private final String displayName;
	private final long lastTimePlayed;
	private final long sizeOnDisk;
	private final boolean requiresConversion;
	private final WorldSettings.GameType theEnumGameType;
	private final boolean hardcore;
	private final boolean cheatsEnabled;
	public final NBTTagCompound levelDat;

	public SaveFormatComparator(String fileNameIn, String displayNameIn, long lastTimePlayedIn, long sizeOnDiskIn,
			WorldSettings.GameType theEnumGameTypeIn, boolean requiresConversionIn, boolean hardcoreIn,
			boolean cheatsEnabledIn, NBTTagCompound levelDat) {
		this.fileName = fileNameIn;
		this.displayName = displayNameIn;
		this.lastTimePlayed = lastTimePlayedIn;
		this.sizeOnDisk = sizeOnDiskIn;
		this.theEnumGameType = theEnumGameTypeIn;
		this.requiresConversion = requiresConversionIn;
		this.hardcore = hardcoreIn;
		this.cheatsEnabled = cheatsEnabledIn;
		this.levelDat = levelDat;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public long getSizeOnDisk() {
		return this.sizeOnDisk;
	}

	public boolean requiresConversion() {
		return this.requiresConversion;
	}

	public long getLastTimePlayed() {
		return this.lastTimePlayed;
	}

	public int compareTo(SaveFormatComparator saveformatcomparator) {
		return this.lastTimePlayed < saveformatcomparator.lastTimePlayed ? 1
				: (this.lastTimePlayed > saveformatcomparator.lastTimePlayed ? -1
						: this.fileName.compareTo(saveformatcomparator.fileName));
	}

	public WorldSettings.GameType getEnumGameType() {
		return this.theEnumGameType;
	}

	public boolean isHardcoreModeEnabled() {
		return this.hardcore;
	}

	public boolean getCheatsEnabled() {
		return this.cheatsEnabled;
	}
}
