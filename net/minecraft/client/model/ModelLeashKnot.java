package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelLeashKnot extends ModelBase {
	public ModelRenderer field_110723_a;

	public ModelLeashKnot() {
		this(0, 0, 32, 32);
	}

	public ModelLeashKnot(int parInt1, int parInt2, int parInt3, int parInt4) {
		this.textureWidth = parInt3;
		this.textureHeight = parInt4;
		this.field_110723_a = new ModelRenderer(this, parInt1, parInt2);
		this.field_110723_a.addBox(-3.0F, -6.0F, -3.0F, 6, 8, 6, 0.0F);
		this.field_110723_a.setRotationPoint(0.0F, 0.0F, 0.0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.field_110723_a.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.field_110723_a.rotateAngleY = f3 / 57.295776F;
		this.field_110723_a.rotateAngleX = f4 / 57.295776F;
	}
}
