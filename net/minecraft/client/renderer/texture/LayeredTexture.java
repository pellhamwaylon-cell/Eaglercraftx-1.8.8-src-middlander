package net.minecraft.client.renderer.texture;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.google.common.collect.Lists;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.ImageData;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class LayeredTexture extends AbstractTexture {
	private static final Logger logger = LogManager.getLogger();
	public final List<String> layeredTextureNames;

	public LayeredTexture(String... textureNames) {
		this.layeredTextureNames = Lists.newArrayList(textureNames);
	}

	public void loadTexture(IResourceManager parIResourceManager) throws IOException {
		this.deleteGlTexture();
		ImageData bufferedimage = null;

		try {
			for (int i = 0, l = this.layeredTextureNames.size(); i < l; ++i) {
				String s = this.layeredTextureNames.get(i);
				if (s != null) {
					InputStream inputstream = parIResourceManager.getResource(new ResourceLocation(s)).getInputStream();
					ImageData bufferedimage1 = TextureUtil.readBufferedImage(inputstream);
					if (bufferedimage == null) {
						bufferedimage = new ImageData(bufferedimage1.width, bufferedimage1.height, true);
					}

					bufferedimage.drawLayer(bufferedimage1, 0, 0, bufferedimage1.width, bufferedimage1.height, 0, 0,
							bufferedimage1.width, bufferedimage1.height);
				}
			}
		} catch (IOException ioexception) {
			logger.error("Couldn\'t load layered image", ioexception);
			return;
		}

		regenerateIfNotAllocated();
		TextureUtil.uploadTextureImage(this.getGlTextureId(), bufferedimage);
	}
}
