package net.minecraft.client.main;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;

public class Main {
	public static void appMain() {
		System.setProperty("java.net.preferIPv6Addresses", "true");
		GameConfiguration gameconfiguration = new GameConfiguration(
				new GameConfiguration.UserInformation(new Session()),
				new GameConfiguration.DisplayInformation(854, 480, false, true),
				new GameConfiguration.GameInformation(false, "1.8.8"));
		PlatformRuntime.setThreadName("Client thread");
		(new Minecraft(gameconfiguration)).run();
	}
}
