package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelSkeletonHead extends ModelBase {
	public ModelRenderer skeletonHead;

	public ModelSkeletonHead() {
		this(0, 35, 64, 64);
	}

	public ModelSkeletonHead(int parInt1, int parInt2, int parInt3, int parInt4) {
		this.textureWidth = parInt3;
		this.textureHeight = parInt4;
		this.skeletonHead = new ModelRenderer(this, parInt1, parInt2);
		this.skeletonHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.skeletonHead.setRotationPoint(0.0F, 0.0F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.skeletonHead.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.skeletonHead.rotateAngleY = f3 / 57.295776F;
		this.skeletonHead.rotateAngleX = f4 / 57.295776F;
	}
}
