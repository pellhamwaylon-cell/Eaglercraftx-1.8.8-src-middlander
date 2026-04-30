package net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.HString;

public class ChatComponentTranslationFormatException extends IllegalArgumentException {
	public ChatComponentTranslationFormatException(ChatComponentTranslation component, String message) {
		super(HString.format("Error parsing: %s: %s", new Object[] { component, message }));
	}

	public ChatComponentTranslationFormatException(ChatComponentTranslation component, int index) {
		super(HString.format("Invalid index %d requested for %s", new Object[] { Integer.valueOf(index), component }));
	}

	public ChatComponentTranslationFormatException(ChatComponentTranslation component, Throwable cause) {
		super(HString.format("Error while parsing: %s", new Object[] { component }), cause);
	}
}
