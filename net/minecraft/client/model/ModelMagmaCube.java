package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMagmaCube;

public class ModelMagmaCube extends ModelBase {
	ModelRenderer[] segments = new ModelRenderer[8];
	ModelRenderer core;

	public ModelMagmaCube() {
		for (int i = 0; i < this.segments.length; ++i) {
			byte b0 = 0;
			int j = i;
			if (i == 2) {
				b0 = 24;
				j = 10;
			} else if (i == 3) {
				b0 = 24;
				j = 19;
			}

			this.segments[i] = new ModelRenderer(this, b0, j);
			this.segments[i].addBox(-4.0F, (float) (16 + i), -4.0F, 8, 1, 8);
		}

		this.core = new ModelRenderer(this, 0, 16);
		this.core.addBox(-2.0F, 18.0F, -2.0F, 4, 4, 4);
	}

	public void setLivingAnimations(EntityLivingBase entitylivingbase, float var2, float var3, float f) {
		EntityMagmaCube entitymagmacube = (EntityMagmaCube) entitylivingbase;
		float f1 = entitymagmacube.prevSquishFactor
				+ (entitymagmacube.squishFactor - entitymagmacube.prevSquishFactor) * f;
		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		for (int i = 0; i < this.segments.length; ++i) {
			this.segments[i].rotationPointY = (float) (-(4 - i)) * f1 * 1.7F;
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.core.render(f5);

		for (int i = 0; i < this.segments.length; ++i) {
			this.segments[i].render(f5);
		}

	}
}
