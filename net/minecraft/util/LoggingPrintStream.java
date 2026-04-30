package net.minecraft.util;

import java.io.OutputStream;
import java.io.PrintStream;

import net.lax1dude.eaglercraft.v1_8.internal.PlatformRuntime;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class LoggingPrintStream extends PrintStream {
	private final String domain;
	private final Logger logger;
	private final boolean err;

	public LoggingPrintStream(String domainIn, boolean err, OutputStream outStream) {
		super(outStream);
		this.domain = domainIn;
		this.logger = LogManager.getLogger(domainIn);
		this.err = err;
	}

	public void println(String s) {
		this.logString(s);
	}

	public void println(Object parObject) {
		this.logString(String.valueOf(parObject));
	}

	private void logString(String string) {
		String callingClass = PlatformRuntime.getCallingClass(3);
		if (callingClass == null) {
			if (err) {
				logger.error(string);
			} else {
				logger.info(string);
			}
		} else {
			if (err) {
				logger.error("@({}): {}", new Object[] { callingClass, string });
			} else {
				logger.info("@({}): {}", new Object[] { callingClass, string });
			}
		}
	}
}
