package net.minecraft.client.resources.data;

import java.util.HashSet;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Sets;

import net.minecraft.client.resources.Language;

public class LanguageMetadataSectionSerializer extends BaseMetadataSectionSerializer<LanguageMetadataSection> {
	public LanguageMetadataSection deserialize(JSONObject jsonobject) throws JSONException {
		HashSet hashset = Sets.newHashSet();

		for (String s : jsonobject.keySet()) {
			JSONObject jsonobject1 = jsonobject.getJSONObject(s);
			String s1 = jsonobject1.getString("region");
			String s2 = jsonobject1.getString("name");
			boolean flag = jsonobject1.optBoolean("bidirectional", false);
			if (s1.isEmpty()) {
				throw new JSONException("Invalid language->\'" + s + "\'->region: empty value");
			}

			if (s2.isEmpty()) {
				throw new JSONException("Invalid language->\'" + s + "\'->name: empty value");
			}

			if (!hashset.add(new Language(s, s1, s2, flag))) {
				throw new JSONException("Duplicate language->\'" + s + "\' defined");
			}
		}

		return new LanguageMetadataSection(hashset);
	}

	public String getSectionName() {
		return "language";
	}
}
