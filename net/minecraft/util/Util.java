package net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.futures.ExecutionException;
import net.lax1dude.eaglercraft.v1_8.futures.FutureTask;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class Util {
	public static Util.EnumOS getOSType() {
		return EagRuntime.getPlatformOS().getMinecraftEnum();
	}

	public static <V> V func_181617_a(FutureTask<V> parFutureTask, Logger parLogger) {
		try {
			parFutureTask.run();
			return (V) parFutureTask.get();
		} catch (ExecutionException executionexception) {
			parLogger.fatal("Error executing task", executionexception);
		} catch (InterruptedException interruptedexception) {
			parLogger.fatal("Error executing task", interruptedexception);
		}

		return (V) null;
	}

	public static enum EnumOS {
		LINUX, SOLARIS, WINDOWS, OSX, UNKNOWN;
	}
}
