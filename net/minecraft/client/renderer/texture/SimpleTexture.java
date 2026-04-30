package net.minecraft.client.renderer.texture;

import java.io.IOException;
import java.io.InputStream;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.data.TextureMetadataSection;
import net.minecraft.util.ResourceLocation;

public class SimpleTexture extends AbstractTexture {
	private static final Logger logger = LogManager.getLogger();
	protected final ResourceLocation textureLocation;

	public SimpleTexture(ResourceLocation textureResourceLocation) {
		this.textureLocation = textureResourceLocation;
	}

	public void loadTexture(IResourceManager parIResourceManager) throws IOException {
		this.deleteGlTexture();
		InputStream inputstream = null;

		try {
			IResource iresource = parIResourceManager.getResource(this.textureLocation);
			inputstream = iresource.getInputStream();
			ImageData bufferedimage = TextureUtil.readBufferedImage(inputstream);
			boolean flag = false;
			boolean flag1 = false;
			if (iresource.hasMetadata()) {
				try {
					TextureMetadataSection texturemetadatasection = (TextureMetadataSection) iresource
							.getMetadata("texture");
					if (texturemetadatasection != null) {
						flag = texturemetadatasection.getTextureBlur();
						flag1 = texturemetadatasection.getTextureClamp();
					}
				} catch (RuntimeException runtimeexception) {
					logger.warn("Failed reading metadata of: " + this.textureLocation, runtimeexception);
				}
			}

			regenerateIfNotAllocated();
			TextureUtil.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, flag, flag1);
		} finally {
			if (inputstream != null) {
				inputstream.close();
			}

		}

	}
}
