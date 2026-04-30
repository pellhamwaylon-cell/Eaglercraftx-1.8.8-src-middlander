package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelEnderMite extends ModelBase {
	private static final int[][] field_178716_a = new int[][] { { 4, 3, 2 }, { 6, 4, 5 }, { 3, 3, 1 }, { 1, 2, 1 } };
	private static final int[][] field_178714_b = new int[][] { { 0, 0 }, { 0, 5 }, { 0, 14 }, { 0, 18 } };
	private static final int field_178715_c = field_178716_a.length;
	private final ModelRenderer[] field_178713_d;

	public ModelEnderMite() {
		this.field_178713_d = new ModelRenderer[field_178715_c];
		float f = -3.5F;

		for (int i = 0; i < this.field_178713_d.length; ++i) {
			this.field_178713_d[i] = new ModelRenderer(this, field_178714_b[i][0], field_178714_b[i][1]);
			this.field_178713_d[i].addBox((float) field_178716_a[i][0] * -0.5F, 0.0F,
					(float) field_178716_a[i][2] * -0.5F, field_178716_a[i][0], field_178716_a[i][1],
					field_178716_a[i][2]);
			this.field_178713_d[i].setRotationPoint(0.0F, (float) (24 - field_178716_a[i][1]), f);
			if (i < this.field_178713_d.length - 1) {
				f += (float) (field_178716_a[i][2] + field_178716_a[i + 1][2]) * 0.5F;
			}
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

		for (int i = 0; i < this.field_178713_d.length; ++i) {
			this.field_178713_d[i].render(f5);
		}

	}

	public void setRotationAngles(float var1, float var2, float f, float var4, float var5, float var6, Entity var7) {
		for (int i = 0; i < this.field_178713_d.length; ++i) {
			this.field_178713_d[i].rotateAngleY = MathHelper.cos(f * 0.9F + (float) i * 0.15F * 3.1415927F) * 3.1415927F
					* 0.01F * (float) (1 + Math.abs(i - 2));
			this.field_178713_d[i].rotationPointX = MathHelper.sin(f * 0.9F + (float) i * 0.15F * 3.1415927F)
					* 3.1415927F * 0.1F * (float) Math.abs(i - 2);
		}

	}
}
