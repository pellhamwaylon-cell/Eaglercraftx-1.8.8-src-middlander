package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DynamicLightManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.texture.EmissiveItems;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderSnowball<T extends Entity> extends Render<T> {
	protected final Item field_177084_a;
	private final RenderItem field_177083_e;

	public RenderSnowball(RenderManager renderManagerIn, Item parItem, RenderItem parRenderItem) {
		super(renderManagerIn);
		this.field_177084_a = parItem;
		this.field_177083_e = parRenderItem;
	}

	public void doRender(T entity, double d0, double d1, double d2, float f, float f1) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) d0, (float) d1, (float) d2);
		GlStateManager.enableRescaleNormal();
		GlStateManager.scale(0.5F, 0.5F, 0.5F);
		GlStateManager.rotate(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
		GlStateManager.rotate(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		this.bindTexture(TextureMap.locationBlocksTexture);
		ItemStack itm = this.func_177082_d(entity);
		this.field_177083_e.func_181564_a(itm, ItemCameraTransforms.TransformType.GROUND);
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		if (DynamicLightManager.isRenderingLights()) {
			float[] emission = EmissiveItems.getItemEmission(itm);
			if (emission != null) {
				float mag = 0.1f;
				DynamicLightManager.renderDynamicLight("entity_" + entity.getEntityId() + "_item_throw", d0, d1, d2,
						emission[0] * mag, emission[1] * mag, emission[2] * mag, false);
			}
		}
		super.doRender(entity, d0, d1, d2, f, f1);
	}

	public ItemStack func_177082_d(T entityIn) {
		return new ItemStack(this.field_177084_a, 1, 0);
	}

	protected ResourceLocation getEntityTexture(Entity var1) {
		return TextureMap.locationBlocksTexture;
	}
}
