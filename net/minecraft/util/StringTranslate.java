package net.minecraft.util;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.HString;
import net.lax1dude.eaglercraft.v1_8.IOUtils;
import net.lax1dude.eaglercraft.v1_8.sp.SingleplayerServerController;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class StringTranslate {
	private static final Pattern numericVariablePattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
	private static final Splitter equalSignSplitter = Splitter.on('=').limit(2);
	private static StringTranslate instance = new StringTranslate();
	static StringTranslate fallbackInstance = null;
	private final Map<String, String> languageList = Maps.newHashMap();
	private long lastUpdateTimeInMilliseconds;

	private StringTranslate() {
	}

	public static void initClient() {
		try (InputStream inputstream = EagRuntime.getRequiredResourceStream("/assets/minecraft/lang/en_US.lang")) {
			initServer(IOUtils.readLines(inputstream, StandardCharsets.UTF_8));
			fallbackInstance = new StringTranslate();
			fallbackInstance.replaceWith(instance.languageList);
		} catch (IOException e) {
			EagRuntime.debugPrintStackTrace(e);
		}
	}

	public static void initServer(List<String> strs) {
		instance.languageList.clear();
		for (int i = 0, l = strs.size(); i < l; ++i) {
			String s = strs.get(i);
			if (!s.isEmpty() && s.charAt(0) != 35) {
				String[] astring = (String[]) Iterables.toArray(equalSignSplitter.split(s), String.class);
				if (astring != null && astring.length == 2) {
					String s1 = astring[0];
					String s2 = numericVariablePattern.matcher(astring[1]).replaceAll("%s"); // TODO: originally "%$1s"
																								// but must be "%s" to
																								// work with TeaVM
																								// (why?)
					instance.languageList.put(s1, s2);
				}
			}
		}

		instance.lastUpdateTimeInMilliseconds = EagRuntime.steadyTimeMillis();
	}

	static StringTranslate getInstance() {
		return instance;
	}

	public static void replaceWith(Map<String, String> parMap) {
		instance.languageList.clear();
		instance.languageList.putAll(parMap);
		instance.lastUpdateTimeInMilliseconds = EagRuntime.steadyTimeMillis();
		SingleplayerServerController.updateLocale(dump());
	}

	public String translateKey(String key) {
		return this.tryTranslateKey(key);
	}

	public String translateKeyFormat(String key, Object... format) {
		String s = this.tryTranslateKey(key);

		try {
			return HString.format(s, format);
		} catch (IllegalFormatException var5) {
			return "Format error: " + s;
		}
	}

	private String tryTranslateKey(String key) {
		String s = (String) this.languageList.get(key);
		return s == null ? key : s;
	}

	public boolean isKeyTranslated(String key) {
		return this.languageList.containsKey(key);
	}

	public long getLastUpdateTimeInMilliseconds() {
		return this.lastUpdateTimeInMilliseconds;
	}

	public static List<String> dump() {
		List<String> ret = new ArrayList(instance.languageList.size());
		for (Entry<String, String> etr : instance.languageList.entrySet()) {
			ret.add(etr.getKey() + "=" + etr.getValue());
		}
		return ret;
	}
}
