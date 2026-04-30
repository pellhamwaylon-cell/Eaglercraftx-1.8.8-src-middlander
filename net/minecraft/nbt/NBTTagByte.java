package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagByte extends NBTBase.NBTPrimitive {
	private byte data;

	NBTTagByte() {
	}

	public NBTTagByte(byte data) {
		this.data = data;
	}

	void write(DataOutput parDataOutput) throws IOException {
		parDataOutput.writeByte(this.data);
	}

	void read(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker) throws IOException {
		parNBTSizeTracker.read(72L);
		this.data = parDataInput.readByte();
	}

	public byte getId() {
		return (byte) 1;
	}

	public String toString() {
		return "" + this.data + "b";
	}

	public NBTBase copy() {
		return new NBTTagByte(this.data);
	}

	public boolean equals(Object object) {
		if (super.equals(object)) {
			NBTTagByte nbttagbyte = (NBTTagByte) object;
			return this.data == nbttagbyte.data;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return super.hashCode() ^ this.data;
	}

	public long getLong() {
		return (long) this.data;
	}

	public int getInt() {
		return this.data;
	}

	public short getShort() {
		return (short) this.data;
	}

	public byte getByte() {
		return this.data;
	}

	public double getDouble() {
		return (double) this.data;
	}

	public float getFloat() {
		return (float) this.data;
	}
}
