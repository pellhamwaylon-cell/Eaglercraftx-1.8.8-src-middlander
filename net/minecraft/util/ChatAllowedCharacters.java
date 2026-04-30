package net.minecraft.util;

public class ChatAllowedCharacters {
	public static final char[] allowedCharactersArray = new char[] { '/', '\n', '\r', '\t', '\u0000', '\f', '`', '?',
			'*', '\\', '<', '>', '|', '\"', ':' };

	public static boolean isAllowedCharacter(char character) {
		return character != 167 && character >= 32 && character != 127;
	}

	public static String filterAllowedCharacters(String input) {
		StringBuilder stringbuilder = new StringBuilder();

		char[] chars = input.toCharArray();
		for (int i = 0; i < chars.length; ++i) {
			char c0 = chars[i];
			if (isAllowedCharacter(c0)) {
				stringbuilder.append(c0);
			}
		}

		return stringbuilder.toString();
	}
}
