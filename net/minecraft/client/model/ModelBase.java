package net.minecraft.client.model;

import java.util.List;
import java.util.Map;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public abstract class ModelBase {
	public float swingProgress;
	public boolean isRiding;
	public boolean isChild = true;
	public List<ModelRenderer> boxList = Lists.newArrayList();
	private Map<String, TextureOffset> modelTextureMap = Maps.newHashMap();
	public int textureWidth = 64;
	public int textureHeight = 32;

	public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {
	}

	public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
	}

	public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
	}

	public ModelRenderer getRandomModelBox(EaglercraftRandom rand) {
		return (ModelRenderer) this.boxList.get(rand.nextInt(this.boxList.size()));
	}

	protected void setTextureOffset(String partName, int x, int y) {
		this.modelTextureMap.put(partName, new TextureOffset(x, y));
	}

	public TextureOffset getTextureOffset(String partName) {
		return (TextureOffset) this.modelTextureMap.get(partName);
	}

	public static void copyModelAngles(ModelRenderer source, ModelRenderer dest) {
		dest.rotateAngleX = source.rotateAngleX;
		dest.rotateAngleY = source.rotateAngleY;
		dest.rotateAngleZ = source.rotateAngleZ;
		dest.rotationPointX = source.rotationPointX;
		dest.rotationPointY = source.rotationPointY;
		dest.rotationPointZ = source.rotationPointZ;
	}

	public void setModelAttributes(ModelBase modelbase) {
		this.swingProgress = modelbase.swingProgress;
		this.isRiding = modelbase.isRiding;
		this.isChild = modelbase.isChild;
	}
}
