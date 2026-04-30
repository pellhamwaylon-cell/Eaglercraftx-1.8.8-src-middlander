package net.minecraft.util;

public class IntegerCache {
	private static final Integer[] field_181757_a = new Integer['\uffff'];

	public static Integer func_181756_a(int parInt1) {
		return parInt1 > 0 && parInt1 < field_181757_a.length ? field_181757_a[parInt1] : Integer.valueOf(parInt1);
	}

	static {
		int i = 0;

		for (int j = field_181757_a.length; i < j; ++i) {
			field_181757_a[i] = Integer.valueOf(i);
		}

	}
}
