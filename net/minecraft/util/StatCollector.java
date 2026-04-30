package net.minecraft.util;

public class StatCollector {
	private static StringTranslate localizedName = StringTranslate.getInstance();

	public static String translateToLocal(String key) {
		return localizedName.translateKey(key);
	}

	public static String translateToLocalFormatted(String key, Object... format) {
		return localizedName.translateKeyFormat(key, format);
	}

	public static String translateToFallback(String key) {
		return StringTranslate.fallbackInstance != null ? StringTranslate.fallbackInstance.translateKey(key)
				: localizedName.translateKey(key);
	}

	public static boolean canTranslate(String key) {
		return localizedName.isKeyTranslated(key);
	}

	public static long getLastTranslationUpdateTimeInMilliseconds() {
		return localizedName.getLastUpdateTimeInMilliseconds();
	}
}
