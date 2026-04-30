package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;

public class NBTTagIntArray extends NBTBase {
	private int[] intArray;

	NBTTagIntArray() {
	}

	public NBTTagIntArray(int[] parArrayOfInt) {
		this.intArray = parArrayOfInt;
	}

	void write(DataOutput parDataOutput) throws IOException {
		parDataOutput.writeInt(this.intArray.length);

		for (int i = 0; i < this.intArray.length; ++i) {
			parDataOutput.writeInt(this.intArray[i]);
		}

	}

	void read(DataInput parDataInput, int parInt1, NBTSizeTracker parNBTSizeTracker) throws IOException {
		parNBTSizeTracker.read(192L);
		int i = parDataInput.readInt();
		parNBTSizeTracker.read((long) (32 * i));
		this.intArray = new int[i];

		for (int j = 0; j < i; ++j) {
			this.intArray[j] = parDataInput.readInt();
		}

	}

	public byte getId() {
		return (byte) 11;
	}

	public String toString() {
		String s = "[";

		for (int i = 0; i < this.intArray.length; ++i) {
			s = s + this.intArray[i] + ",";
		}

		return s + "]";
	}

	public NBTBase copy() {
		int[] aint = new int[this.intArray.length];
		System.arraycopy(this.intArray, 0, aint, 0, this.intArray.length);
		return new NBTTagIntArray(aint);
	}

	public boolean equals(Object object) {
		return super.equals(object) ? Arrays.equals(this.intArray, ((NBTTagIntArray) object).intArray) : false;
	}

	public int hashCode() {
		return super.hashCode() ^ Arrays.hashCode(this.intArray);
	}

	public int[] getIntArray() {
		return this.intArray;
	}
}
