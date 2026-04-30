package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelHumanoidHead extends ModelSkeletonHead {
	private final ModelRenderer head = new ModelRenderer(this, 32, 0);

	public ModelHumanoidHead() {
		super(0, 0, 64, 64);
		this.head.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
		this.head.setRotationPoint(0.0F, 0.0F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		this.head.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.head.rotateAngleY = this.skeletonHead.rotateAngleY;
		this.head.rotateAngleX = this.skeletonHead.rotateAngleX;
	}
}
