package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagString extends NBTBase {
	private String data;

	public NBTTagString() {
		this.data = "";
	}

	public NBTTagString(String data) {
		this.data = data;
		if (data == null) {
			throw new IllegalArgumentException("Empty string not allowed");
		}
	}

	void write(DataOutput parDataOutput) throws IOException {
		parDataOutput.writeUTF(this.data);
	}

	void read(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker) throws IOException {
		parNBTSizeTracker.read(288L);
		this.data = parDataInput.readUTF();
		parNBTSizeTracker.read((long) (16 * this.data.length()));
	}

	public byte getId() {
		return (byte) 8;
	}

	public String toString() {
		return "\"" + this.data.replace("\"", "\\\"") + "\"";
	}

	public NBTBase copy() {
		return new NBTTagString(this.data);
	}

	public boolean hasNoTags() {
		return this.data.isEmpty();
	}

	public boolean equals(Object object) {
		if (!super.equals(object)) {
			return false;
		} else {
			NBTTagString nbttagstring = (NBTTagString) object;
			return this.data == null && nbttagstring.data == null
					|| this.data != null && this.data.equals(nbttagstring.data);
		}
	}

	public int hashCode() {
		return super.hashCode() ^ this.data.hashCode();
	}

	public String getString() {
		return this.data;
	}
}
