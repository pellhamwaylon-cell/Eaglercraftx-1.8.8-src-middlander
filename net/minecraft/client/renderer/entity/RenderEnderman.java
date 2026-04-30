package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving<EntityEnderman> {
	private static final ResourceLocation endermanTextures = new ResourceLocation(
			"textures/entity/enderman/enderman.png");
	private ModelEnderman endermanModel;
	private EaglercraftRandom rnd = new EaglercraftRandom();

	public RenderEnderman(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
		this.endermanModel = (ModelEnderman) super.mainModel;
		this.addLayer(new LayerEndermanEyes(this));
		this.addLayer(new LayerHeldBlock(this));
	}

	public void doRender(EntityEnderman entityenderman, double d0, double d1, double d2, float f, float f1) {
		this.endermanModel.isCarrying = entityenderman.getHeldBlockState().getBlock().getMaterial() != Material.air;
		this.endermanModel.isAttacking = entityenderman.isScreaming();
		if (entityenderman.isScreaming()) {
			double d3 = 0.02D;
			d0 += this.rnd.nextGaussian() * d3;
			d2 += this.rnd.nextGaussian() * d3;
		}

		super.doRender(entityenderman, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityEnderman var1) {
		return endermanTextures;
	}
}
