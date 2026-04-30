package net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformApplication;

public class ScreenShotHelper {

	public static IChatComponent saveScreenshot() {
		return new ChatComponentText("Saved Screenshot As: " + PlatformApplication.saveScreenshot());
	}
}
