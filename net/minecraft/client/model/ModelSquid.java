package net.minecraft.client.model;

import net.minecraft.entity.Entity;

public class ModelSquid extends ModelBase {
	ModelRenderer squidBody;
	ModelRenderer[] squidTentacles = new ModelRenderer[8];

	public ModelSquid() {
		byte b0 = -16;
		this.squidBody = new ModelRenderer(this, 0, 0);
		this.squidBody.addBox(-6.0F, -8.0F, -6.0F, 12, 16, 12);
		this.squidBody.rotationPointY += (float) (24 + b0);

		for (int i = 0; i < this.squidTentacles.length; ++i) {
			this.squidTentacles[i] = new ModelRenderer(this, 48, 0);
			double d0 = (double) i * 3.141592653589793D * 2.0D / (double) this.squidTentacles.length;
			float f = (float) Math.cos(d0) * 5.0F;
			float f1 = (float) Math.sin(d0) * 5.0F;
			this.squidTentacles[i].addBox(-1.0F, 0.0F, -1.0F, 2, 18, 2);
			this.squidTentacles[i].rotationPointX = f;
			this.squidTentacles[i].rotationPointZ = f1;
			this.squidTentacles[i].rotationPointY = (float) (31 + b0);
			d0 = (double) i * 3.141592653589793D * -2.0D / (double) this.squidTentacles.length + 1.5707963267948966D;
			this.squidTentacles[i].rotateAngleY = (float) d0;
		}

	}

	public void setRotationAngles(float var1, float var2, float f, float var4, float var5, float var6, Entity var7) {
		for (int i = 0; i < this.squidTentacles.length; ++i) {
			this.squidTentacles[i].rotateAngleX = f;
		}

	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.squidBody.render(f5);

		for (int i = 0; i < this.squidTentacles.length; ++i) {
			this.squidTentacles[i].render(f5);
		}

	}
}
