package net.minecraft.client.resources.data;

import org.json.JSONObject;

import net.lax1dude.eaglercraft.v1_8.json.JSONTypeDeserializer;

public interface IMetadataSectionSerializer<T extends IMetadataSection> extends JSONTypeDeserializer<JSONObject, T> {
	String getSectionName();
}
