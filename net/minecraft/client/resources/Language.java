package net.minecraft.client.resources;

import net.lax1dude.eaglercraft.v1_8.HString;

public class Language implements Comparable<Language> {
	private final String languageCode;
	private final String region;
	private final String name;
	private final boolean bidirectional;

	public Language(String languageCodeIn, String regionIn, String nameIn, boolean bidirectionalIn) {
		this.languageCode = languageCodeIn;
		this.region = regionIn;
		this.name = nameIn;
		this.bidirectional = bidirectionalIn;
	}

	public String getLanguageCode() {
		return this.languageCode;
	}

	public boolean isBidirectional() {
		return this.bidirectional;
	}

	public String toString() {
		return HString.format("%s (%s)", new Object[] { this.name, this.region });
	}

	public boolean equals(Object object) {
		return this == object ? true
				: (!(object instanceof Language) ? false : this.languageCode.equals(((Language) object).languageCode));
	}

	public int hashCode() {
		return this.languageCode.hashCode();
	}

	public int compareTo(Language language) {
		return this.languageCode.compareTo(language.languageCode);
	}
}
