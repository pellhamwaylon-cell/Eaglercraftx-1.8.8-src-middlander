package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class NBTTagInt extends NBTBase.NBTPrimitive {
	private int data;

	NBTTagInt() {
	}

	public NBTTagInt(int data) {
		this.data = data;
	}

	void write(DataOutput parDataOutput) throws IOException {
		parDataOutput.writeInt(this.data);
	}

	void read(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker) throws IOException {
		parNBTSizeTracker.read(96L);
		this.data = parDataInput.readInt();
	}

	public byte getId() {
		return (byte) 3;
	}

	public String toString() {
		return "" + this.data;
	}

	public NBTBase copy() {
		return new NBTTagInt(this.data);
	}

	public boolean equals(Object object) {
		if (super.equals(object)) {
			NBTTagInt nbttagint = (NBTTagInt) object;
			return this.data == nbttagint.data;
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
		return (short) (this.data & '\uffff');
	}

	public byte getByte() {
		return (byte) (this.data & 255);
	}

	public double getDouble() {
		return (double) this.data;
	}

	public float getFloat() {
		return (float) this.data;
	}
}
