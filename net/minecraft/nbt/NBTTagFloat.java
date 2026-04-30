package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import net.minecraft.util.MathHelper;

public class NBTTagFloat extends NBTBase.NBTPrimitive {
	private float data;

	NBTTagFloat() {
	}

	public NBTTagFloat(float data) {
		this.data = data;
	}

	void write(DataOutput parDataOutput) throws IOException {
		parDataOutput.writeFloat(this.data);
	}

	void read(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker) throws IOException {
		parNBTSizeTracker.read(96L);
		this.data = parDataInput.readFloat();
	}

	public byte getId() {
		return (byte) 5;
	}

	public String toString() {
		return "" + this.data + "f";
	}

	public NBTBase copy() {
		return new NBTTagFloat(this.data);
	}

	public boolean equals(Object object) {
		if (super.equals(object)) {
			NBTTagFloat nbttagfloat = (NBTTagFloat) object;
			return this.data == nbttagfloat.data;
		} else {
			return false;
		}
	}

	public int hashCode() {
		return super.hashCode() ^ Float.floatToIntBits(this.data);
	}

	public long getLong() {
		return (long) this.data;
	}

	public int getInt() {
		return MathHelper.floor_float(this.data);
	}

	public short getShort() {
		return (short) (MathHelper.floor_float(this.data) & '\uffff');
	}

	public byte getByte() {
		return (byte) (MathHelper.floor_float(this.data) & 255);
	}

	public double getDouble() {
		return (double) this.data;
	}

	public float getFloat() {
		return this.data;
	}
}
