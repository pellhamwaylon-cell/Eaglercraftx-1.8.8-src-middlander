package net.minecraft.client.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.HString;
import net.lax1dude.eaglercraft.v1_8.IOUtils;
import net.minecraft.util.ResourceLocation;

public class Locale {
	private static final Splitter splitter = Splitter.on('=').limit(2);
	private static final Pattern pattern = Pattern.compile("%(\\d+\\$)?[\\d\\.]*[df]");
	Map<String, String> properties = Maps.newHashMap();
	private boolean unicode;

	private static final Set<String> hasShownMissing = new HashSet<>();

	public synchronized void loadLocaleDataFiles(IResourceManager resourceManager, List<String> parList) {
		this.properties.clear();

		for (String s : parList) {
			String s1 = HString.format("lang/%s.lang", new Object[] { s });

			for (String s2 : resourceManager.getResourceDomains()) {
				try {
					List<IResource> res = resourceManager.getAllResources(new ResourceLocation(s2, s1));
					if (res.size() > 0) {
						this.loadLocaleData(res);
					} else {
						if (s2.equalsIgnoreCase("minecraft") && hasShownMissing.add(s)) {
							EagRuntime.showPopup("ERROR: language \"" + s + "\" is not available on this site!");
						}
					}
				} catch (IOException var9) {
					if (s2.equalsIgnoreCase("minecraft") && hasShownMissing.add(s)) {
						EagRuntime.showPopup("ERROR: language \"" + s + "\" is not available on this site!");
					}
				}
			}
		}

		this.checkUnicode();
	}

	public boolean isUnicode() {
		return this.unicode;
	}

	private void checkUnicode() {
		this.unicode = false;
		int i = 0;
		int j = 0;

		for (String s : this.properties.values()) {
			int k = s.length();
			j += k;

			for (int l = 0; l < k; ++l) {
				if (s.charAt(l) >= 256) {
					++i;
				}
			}
		}

		float f = (float) i / (float) j;
		this.unicode = (double) f > 0.1D;
	}

	private void loadLocaleData(List<IResource> parList) throws IOException {
		for (IResource iresource : parList) {
			InputStream inputstream = iresource.getInputStream();

			try {
				this.loadLocaleData(inputstream);
			} finally {
				IOUtils.closeQuietly(inputstream);
			}
		}

	}

	private void loadLocaleData(InputStream parInputStream) throws IOException {
		for (String s : IOUtils.readLines(parInputStream, Charsets.UTF_8)) {
			if (!s.isEmpty() && s.charAt(0) != 35) {
				String[] astring = (String[]) Iterables.toArray(splitter.split(s), String.class);
				if (astring != null && astring.length == 2) {
					String s1 = astring[0];
					String s2 = pattern.matcher(astring[1]).replaceAll("%s"); // TODO: originally "%$1s" but must be
																				// "%s" to work with TeaVM (why?)
					this.properties.put(s1, s2);
					if (s1.startsWith("eaglercraft.")) {
						this.properties.put(s1.substring(12), s2);
					}
				}
			}
		}

	}

	private String translateKeyPrivate(String parString1) {
		String s = (String) this.properties.get(parString1);
		return s == null ? parString1 : s;
	}

	public String formatMessage(String translateKey, Object[] parameters) {
		String s = this.translateKeyPrivate(translateKey);

		try {
			return HString.format(s, parameters);
		} catch (IllegalFormatException var5) {
			return "Format error: " + s;
		}
	}
}
