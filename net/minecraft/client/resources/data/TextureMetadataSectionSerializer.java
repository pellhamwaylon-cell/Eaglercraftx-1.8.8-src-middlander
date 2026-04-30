package net.minecraft.client.resources.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.carrotsearch.hppc.IntArrayList;

public class TextureMetadataSectionSerializer extends BaseMetadataSectionSerializer<TextureMetadataSection> {
	public TextureMetadataSection deserialize(JSONObject jsonobject) throws JSONException {
		boolean flag = jsonobject.optBoolean("blur", false);
		boolean flag1 = jsonobject.optBoolean("clamp", false);
		IntArrayList arraylist = new IntArrayList();
		if (jsonobject.has("mipmaps")) {
			try {
				JSONArray jsonarray = jsonobject.getJSONArray("mipmaps");

				for (int i = 0; i < jsonarray.length(); ++i) {
					Object jsonelement = jsonarray.get(i);
					if (jsonelement instanceof Number) {
						try {
							arraylist.add(((Number) jsonelement).intValue());
						} catch (NumberFormatException numberformatexception) {
							throw new JSONException(
									"Invalid texture->mipmap->" + i + ": expected number, was " + jsonelement,
									numberformatexception);
						}
					} else if (jsonelement instanceof JSONObject) {
						throw new JSONException(
								"Invalid texture->mipmap->" + i + ": expected number, was " + jsonelement);
					}
				}
			} catch (ClassCastException classcastexception) {
				throw new JSONException("Invalid texture->mipmaps: expected array, was " + jsonobject.get("mipmaps"),
						classcastexception);
			}
		}

		return new TextureMetadataSection(flag, flag1, arraylist);
	}

	public String getSectionName() {
		return "texture";
	}
}
