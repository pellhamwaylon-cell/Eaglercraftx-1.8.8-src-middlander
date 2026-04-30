package net.minecraft.util;

public class Vec4b {
	private byte field_176117_a;
	private byte field_176115_b;
	private byte field_176116_c;
	private byte field_176114_d;

	public Vec4b(byte parByte1, byte parByte2, byte parByte3, byte parByte4) {
		this.field_176117_a = parByte1;
		this.field_176115_b = parByte2;
		this.field_176116_c = parByte3;
		this.field_176114_d = parByte4;
	}

	public Vec4b(Vec4b parVec4b) {
		this.field_176117_a = parVec4b.field_176117_a;
		this.field_176115_b = parVec4b.field_176115_b;
		this.field_176116_c = parVec4b.field_176116_c;
		this.field_176114_d = parVec4b.field_176114_d;
	}

	public byte func_176110_a() {
		return this.field_176117_a;
	}

	public byte func_176112_b() {
		return this.field_176115_b;
	}

	public byte func_176113_c() {
		return this.field_176116_c;
	}

	public byte func_176111_d() {
		return this.field_176114_d;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof Vec4b)) {
			return false;
		} else {
			Vec4b vec4b = (Vec4b) object;
			return this.field_176117_a != vec4b.field_176117_a ? false
					: (this.field_176114_d != vec4b.field_176114_d ? false
							: (this.field_176115_b != vec4b.field_176115_b ? false
									: this.field_176116_c == vec4b.field_176116_c));
		}
	}

	public int hashCode() {
		int i = this.field_176117_a;
		i = 31 * i + this.field_176115_b;
		i = 31 * i + this.field_176116_c;
		i = 31 * i + this.field_176114_d;
		return i;
	}
}
