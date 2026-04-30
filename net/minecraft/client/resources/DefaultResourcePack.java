package net.minecraft.client.resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

import com.google.common.collect.ImmutableSet;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerFolderResourcePack;
import net.lax1dude.eaglercraft.v1_8.minecraft.ResourceIndex;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.util.ResourceLocation;

public class DefaultResourcePack extends ResourceIndex implements IResourcePack {
	public static final Set<String> defaultResourceDomains = ImmutableSet.of("minecraft", "eagler");

	private final Collection<String> propertyFilesIndex;

	public DefaultResourcePack() {
		String str = EagRuntime.getResourceString("/assets/minecraft/optifine/_property_files_index.json");
		if (str != null) {
			Collection<String> lst = EaglerFolderResourcePack.loadPropertyFileList(str);
			if (lst != null) {
				propertyFilesIndex = lst;
				return;
			}
		}
		propertyFilesIndex = Collections.emptyList();
	}

	public InputStream getInputStream(ResourceLocation parResourceLocation) throws IOException {
		InputStream inputstream = this.getResourceStream(parResourceLocation);
		if (inputstream != null) {
			return inputstream;
		} else {
			InputStream inputstream1 = this.getInputStreamAssets(parResourceLocation);
			if (inputstream1 != null) {
				return inputstream1;
			} else {
				throw new FileNotFoundException(parResourceLocation.getResourcePath());
			}
		}
	}

	public InputStream getInputStreamAssets(ResourceLocation location) throws FileNotFoundException {
		return null;
	}

	private InputStream getResourceStream(ResourceLocation location) {
		return EagRuntime
				.getResourceStream("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
	}

	public boolean resourceExists(ResourceLocation location) {
		return EagRuntime
				.getResourceExists("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
	}

	public Set<String> getResourceDomains() {
		return defaultResourceDomains;
	}

	public <T extends IMetadataSection> T getPackMetadata(IMetadataSerializer parIMetadataSerializer, String parString1)
			throws IOException {
		try {
			return AbstractResourcePack.readMetadata(parIMetadataSerializer,
					EagRuntime.getRequiredResourceStream("pack.mcmeta"), parString1);
		} catch (RuntimeException var4) {
			return (T) null;
		}
	}

	public ImageData getPackImage() throws IOException {
		return TextureUtil.readBufferedImage(EagRuntime.getRequiredResourceStream("pack.png"));
	}

	public String getPackName() {
		return "Default";
	}

	@Override
	public ResourceIndex getEaglerFileIndex() {
		return this;
	}

	@Override
	protected Collection<String> getPropertiesFiles0() {
		return propertyFilesIndex;
	}

	@Override
	protected Collection<String> getCITPotionsFiles0() {
		return Collections.emptyList();
	}
}
