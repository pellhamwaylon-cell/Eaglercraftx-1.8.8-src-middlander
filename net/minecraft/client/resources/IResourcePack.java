package net.minecraft.client.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import net.lax1dude.eaglercraft.v1_8.minecraft.ResourceIndex;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public interface IResourcePack {
	InputStream getInputStream(ResourceLocation var1) throws IOException;

	boolean resourceExists(ResourceLocation var1);

	Set<String> getResourceDomains();

	<T extends IMetadataSection> T getPackMetadata(IMetadataSerializer var1, String var2) throws IOException;

	ImageData getPackImage() throws IOException;

	String getPackName();

	ResourceIndex getEaglerFileIndex();
}
