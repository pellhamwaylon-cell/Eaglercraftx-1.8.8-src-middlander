package net.minecraft.client.renderer.texture;

import static net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import com.carrotsearch.hppc.ObjectIntHashMap;
import com.carrotsearch.hppc.ObjectIntMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.lax1dude.eaglercraft.v1_8.HString;
import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;

public class TextureManager implements ITickable, IResourceManagerReloadListener {
	private static final Logger logger = LogManager.getLogger();
	private final Map<ResourceLocation, ITextureObject> mapTextureObjects = Maps.newHashMap();
	private final List<ITickable> listTickables = Lists.newArrayList();
	private final ObjectIntMap<String> mapTextureCounters = new ObjectIntHashMap<>();
	private IResourceManager theResourceManager;

	public TextureManager(IResourceManager resourceManager) {
		this.theResourceManager = resourceManager;
	}

	public void bindTexture(ResourceLocation resource) {
		int glTex;
		if (resource.cachedPointerType == ResourceLocation.CACHED_POINTER_TEXTURE) {
			TextureUtil.bindTexture(glTex = ((ITextureObject) resource.cachedPointer).getGlTextureId());
		} else {
			Object object = (ITextureObject) this.mapTextureObjects.get(resource);
			if (object == null) {
				object = new SimpleTexture(resource);
				this.loadTexture(resource, (ITextureObject) object);
			}

			resource.cachedPointer = object;
			resource.cachedPointerType = ResourceLocation.CACHED_POINTER_TEXTURE;
			TextureUtil.bindTexture(glTex = ((ITextureObject) object).getGlTextureId());
		}
		if (DeferredStateManager.isInDeferredPass()) {
			TextureMap blocksTex = Minecraft.getMinecraft().getTextureMapBlocks();
			if (blocksTex != null) {
				if (blocksTex.getGlTextureId() == glTex) {
					DeferredStateManager.enableMaterialTexture();
					GlStateManager.quickBindTexture(GL_TEXTURE2, blocksTex.eaglerPBRMaterialTexture);
				} else {
					DeferredStateManager.disableMaterialTexture();
				}
			}
		}
	}

	public boolean loadTickableTexture(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
		if (this.loadTexture(textureLocation, textureObj)) {
			this.listTickables.add(textureObj);
			return true;
		} else {
			return false;
		}
	}

	public boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj) {
		boolean flag = true;

		try {
			((ITextureObject) textureObj).loadTexture(this.theResourceManager);
		} catch (IOException ioexception) {
			logger.warn("Failed to load texture: " + textureLocation, ioexception);
			textureObj = TextureUtil.missingTexture;
			this.mapTextureObjects.put(textureLocation, textureObj);
			flag = false;
		} catch (Throwable throwable) {
			CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Registering texture");
			CrashReportCategory crashreportcategory = crashreport.makeCategory("Resource location being registered");
			crashreportcategory.addCrashSection("Resource location", textureLocation);
			final ITextureObject textureObj2 = textureObj;
			crashreportcategory.addCrashSectionCallable("Texture object class", new Callable<String>() {
				public String call() throws Exception {
					return textureObj2.getClass().getName();
				}
			});
			throw new ReportedException(crashreport);
		}

		textureLocation.cachedPointerType = ResourceLocation.CACHED_POINTER_TEXTURE;
		textureLocation.cachedPointer = textureObj;
		this.mapTextureObjects.put(textureLocation, textureObj);
		return flag;
	}

	public ITextureObject getTexture(ResourceLocation textureLocation) {
		if (textureLocation.cachedPointerType == ResourceLocation.CACHED_POINTER_TEXTURE) {
			return (ITextureObject) textureLocation.cachedPointer;
		} else {
			textureLocation.cachedPointerType = ResourceLocation.CACHED_POINTER_TEXTURE;
			return (ITextureObject) (textureLocation.cachedPointer = this.mapTextureObjects.get(textureLocation));
		}
	}

	public ResourceLocation getDynamicTextureLocation(String name, ITextureObject texture) {
		int integer = this.mapTextureCounters.getOrDefault(name, 0) + 1;
		this.mapTextureCounters.put(name, integer);
		ResourceLocation resourcelocation = new ResourceLocation(
				HString.format("dynamic/%s_%d", new Object[] { name, integer }));
		this.loadTexture(resourcelocation, texture);
		return resourcelocation;
	}

	public void tick() {
		for (int i = 0, l = this.listTickables.size(); i < l; ++i) {
			this.listTickables.get(i).tick();
		}

	}

	public void deleteTexture(ResourceLocation textureLocation) {
		ITextureObject itextureobject = this.mapTextureObjects.remove(textureLocation);
		if (itextureobject != null) {
			TextureUtil.deleteTexture(itextureobject.getGlTextureId());
		}
	}

	public void onResourceManagerReload(IResourceManager var1) {
		for (Entry entry : this.mapTextureObjects.entrySet()) {
			this.loadTexture((ResourceLocation) entry.getKey(), (ITextureObject) entry.getValue());
		}

	}
}
