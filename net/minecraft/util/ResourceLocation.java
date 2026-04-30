package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class ResourceLocation {
	protected final String resourceDomain;
	protected final String resourcePath;

	public Object cachedPointer = null;
	public int cachedPointerType = 0;

	public static final int CACHED_POINTER_NONE = 0;
	public static final int CACHED_POINTER_TEXTURE = 1;
	public static final int CACHED_POINTER_EAGLER_MESH = 2;

	protected ResourceLocation(int parInt1, String... resourceName) {
		this.resourceDomain = org.apache.commons.lang3.StringUtils.isEmpty(resourceName[0]) ? "minecraft"
				: resourceName[0].toLowerCase();
		this.resourcePath = resourceName[1];
		Validate.notNull(this.resourcePath);
	}

	public ResourceLocation(String resourceName) {
		this(0, splitObjectName(resourceName));
	}

	public ResourceLocation(String resourceDomainIn, String resourcePathIn) {
		this(0, new String[] { resourceDomainIn, resourcePathIn });
	}

	protected static String[] splitObjectName(String toSplit) {
		String[] astring = new String[] { null, toSplit };
		int i = toSplit.indexOf(58);
		if (i >= 0) {
			astring[1] = toSplit.substring(i + 1, toSplit.length());
			if (i > 1) {
				astring[0] = toSplit.substring(0, i);
			}
		}

		return astring;
	}

	public String getResourcePath() {
		return this.resourcePath;
	}

	public String getResourceDomain() {
		return this.resourceDomain;
	}

	public String toString() {
		return this.resourceDomain + ':' + this.resourcePath;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof ResourceLocation)) {
			return false;
		} else {
			ResourceLocation resourcelocation = (ResourceLocation) object;
			return this.resourceDomain.equals(resourcelocation.resourceDomain)
					&& this.resourcePath.equals(resourcelocation.resourcePath);
		}
	}

	public int hashCode() {
		return 31 * this.resourceDomain.hashCode() + this.resourcePath.hashCode();
	}
}
