package net.minecraft.client.renderer.chunk;

import java.util.BitSet;
import java.util.Set;

import net.minecraft.util.EnumFacing;

public class SetVisibility {
	private static final int COUNT_FACES = EnumFacing._VALUES.length;
	private final BitSet bitSet;

	public SetVisibility() {
		this.bitSet = new BitSet(COUNT_FACES * COUNT_FACES);
	}

	public void setManyVisible(Set<EnumFacing> parSet) {
		EnumFacing[] facings = EnumFacing._VALUES;
		for (int i = 0; i < facings.length; ++i) {
			for (int j = 0; j < facings.length; ++j) {
				this.setVisible(facings[i], facings[j], true);
			}
		}

	}

	public void setVisible(EnumFacing facing, EnumFacing facing2, boolean parFlag) {
		this.bitSet.set(facing.ordinal() + facing2.ordinal() * COUNT_FACES, parFlag);
		this.bitSet.set(facing2.ordinal() + facing.ordinal() * COUNT_FACES, parFlag);
	}

	public void setAllVisible(boolean visible) {
		this.bitSet.set(0, this.bitSet.size(), visible);
	}

	public boolean isVisible(EnumFacing facing, EnumFacing facing2) {
		return this.bitSet.get(facing.ordinal() + facing2.ordinal() * COUNT_FACES);
	}

	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(' ');

		EnumFacing[] facings = EnumFacing._VALUES;
		for (int i = 0; i < facings.length; ++i) {
			stringbuilder.append(' ').append(facings[i].toString().toUpperCase().charAt(0));
		}

		stringbuilder.append('\n');

		for (int i = 0; i < facings.length; ++i) {
			EnumFacing enumfacing2 = facings[i];
			stringbuilder.append(enumfacing2.toString().toUpperCase().charAt(0));

			for (int j = 0; j < facings.length; ++j) {
				if (enumfacing2 == facings[j]) {
					stringbuilder.append("  ");
				} else {
					boolean flag = this.isVisible(enumfacing2, facings[j]);
					stringbuilder.append(' ').append((char) (flag ? 'Y' : 'n'));
				}
			}

			stringbuilder.append('\n');
		}

		return stringbuilder.toString();
	}
}
