package net.minecraft.client.resources.data;

import org.json.JSONException;
import org.json.JSONObject;

import net.lax1dude.eaglercraft.v1_8.json.JSONTypeSerializer;
import net.lax1dude.eaglercraft.v1_8.json.JSONTypeProvider;
import net.minecraft.util.IChatComponent;

public class PackMetadataSectionSerializer extends BaseMetadataSectionSerializer<PackMetadataSection>
		implements JSONTypeSerializer<PackMetadataSection, JSONObject> {
	public PackMetadataSection deserialize(JSONObject jsonobject) throws JSONException {
		IChatComponent ichatcomponent = JSONTypeProvider.deserialize(jsonobject.get("description"),
				IChatComponent.class);
		if (ichatcomponent == null) {
			throw new JSONException("Invalid/missing description!");
		} else {
			int i = jsonobject.getInt("pack_format");
			return new PackMetadataSection(ichatcomponent, i);
		}
	}

	public JSONObject serialize(PackMetadataSection packmetadatasection) {
		JSONObject jsonobject = new JSONObject();
		jsonobject.put("pack_format", packmetadatasection.getPackFormat());
		jsonobject.put("description",
				(JSONObject) JSONTypeProvider.serialize(packmetadatasection.getPackDescription()));
		return jsonobject;
	}

	public String getSectionName() {
		return "pack";
	}
}
