package net.minecraft.client.renderer.texture;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;

import net.lax1dude.eaglercraft.v1_8.opengl.EaglercraftGPU;

public abstract class AbstractTexture implements ITextureObject {
	protected int glTextureId = -1;
	protected boolean blur;
	protected boolean mipmap;
	protected boolean blurLast;
	protected boolean mipmapLast;
	protected boolean hasAllocated;

	public void setBlurMipmapDirect(boolean parFlag, boolean parFlag2) {
		if (blur != parFlag || mipmap != parFlag2) {
			this.blur = parFlag;
			this.mipmap = parFlag2;
			setBlurMipmapDirect0(parFlag, parFlag2);
		}
	}

	protected void setBlurMipmapDirect0(boolean parFlag, boolean parFlag2) {
		int i = -1;
		short short1 = -1;
		if (parFlag) {
			i = parFlag2 ? 9987 : 9729;
			short1 = 9729;
		} else {
			i = parFlag2 ? 9986 : 9728;
			short1 = 9728;
		}

		EaglercraftGPU.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, i);
		EaglercraftGPU.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, short1);
	}

	public void setBlurMipmap(boolean parFlag, boolean parFlag2) {
		this.blurLast = this.blur;
		this.mipmapLast = this.mipmap;
		this.setBlurMipmapDirect(parFlag, parFlag2);
	}

	public void restoreLastBlurMipmap() {
		this.setBlurMipmapDirect(this.blurLast, this.mipmapLast);
	}

	public int getGlTextureId() {
		if (this.glTextureId == -1) {
			this.glTextureId = TextureUtil.glGenTextures();
			hasAllocated = false;
		}

		return this.glTextureId;
	}

	public void deleteGlTexture() {
		if (this.glTextureId != -1) {
			TextureUtil.deleteTexture(this.glTextureId);
			this.glTextureId = -1;
		}

	}

	/**
	 * This function is needed due to EaglercraftX's use of glTexStorage2D to
	 * allocate memory for textures, some OpenGL implementations don't like it when
	 * you call glTexStorage2D on the same texture object more than once
	 */
	protected void regenerateIfNotAllocated() {
		if (this.glTextureId != -1) {
			if (hasAllocated) {
				if (EaglercraftGPU.checkTexStorageCapable()) {
					EaglercraftGPU.regenerateTexture(glTextureId);
				}
			}
			hasAllocated = true;
		}
	}
}
