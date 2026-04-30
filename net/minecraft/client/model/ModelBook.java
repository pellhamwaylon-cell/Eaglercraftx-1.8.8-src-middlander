package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBook extends ModelBase {
	public ModelRenderer coverRight = (new ModelRenderer(this)).setTextureOffset(0, 0).addBox(-6.0F, -5.0F, 0.0F, 6, 10,
			0);
	public ModelRenderer coverLeft = (new ModelRenderer(this)).setTextureOffset(16, 0).addBox(0.0F, -5.0F, 0.0F, 6, 10,
			0);
	public ModelRenderer pagesRight = (new ModelRenderer(this)).setTextureOffset(0, 10).addBox(0.0F, -4.0F, -0.99F, 5,
			8, 1);
	public ModelRenderer pagesLeft = (new ModelRenderer(this)).setTextureOffset(12, 10).addBox(0.0F, -4.0F, -0.01F, 5,
			8, 1);
	public ModelRenderer flippingPageRight = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F,
			0.0F, 5, 8, 0);
	public ModelRenderer flippingPageLeft = (new ModelRenderer(this)).setTextureOffset(24, 10).addBox(0.0F, -4.0F, 0.0F,
			5, 8, 0);
	public ModelRenderer bookSpine = (new ModelRenderer(this)).setTextureOffset(12, 0).addBox(-1.0F, -5.0F, 0.0F, 2, 10,
			0);

	public ModelBook() {
		this.coverRight.setRotationPoint(0.0F, 0.0F, -1.0F);
		this.coverLeft.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.bookSpine.rotateAngleY = 1.5707964F;
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.coverRight.render(f5);
		this.coverLeft.render(f5);
		this.bookSpine.render(f5);
		this.pagesRight.render(f5);
		this.pagesLeft.render(f5);
		this.flippingPageRight.render(f5);
		this.flippingPageLeft.render(f5);
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float var5, float var6, Entity var7) {
		float f4 = (MathHelper.sin(f * 0.02F) * 0.1F + 1.25F) * f3;
		this.coverRight.rotateAngleY = 3.1415927F + f4;
		this.coverLeft.rotateAngleY = -f4;
		this.pagesRight.rotateAngleY = f4;
		this.pagesLeft.rotateAngleY = -f4;
		this.flippingPageRight.rotateAngleY = f4 - f4 * 2.0F * f1;
		this.flippingPageLeft.rotateAngleY = f4 - f4 * 2.0F * f2;
		this.pagesRight.rotationPointX = MathHelper.sin(f4);
		this.pagesLeft.rotationPointX = MathHelper.sin(f4);
		this.flippingPageRight.rotationPointX = MathHelper.sin(f4);
		this.flippingPageLeft.rotationPointX = MathHelper.sin(f4);
	}
}
