package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;

public class ArmorStandRenderer extends RendererLivingEntity<EntityArmorStand> {
	public static final ResourceLocation TEXTURE_ARMOR_STAND = new ResourceLocation(
			"textures/entity/armorstand/wood.png");

	public ArmorStandRenderer(RenderManager parRenderManager) {
		super(parRenderManager, new ModelArmorStand(), 0.0F);
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
			protected void initArmor() {
				this.field_177189_c = new ModelArmorStandArmor(0.5F);
				this.field_177186_d = new ModelArmorStandArmor(1.0F);
			}
		};
		this.addLayer(layerbipedarmor);
		this.addLayer(new LayerHeldItem(this));
		this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
	}

	protected ResourceLocation getEntityTexture(EntityArmorStand var1) {
		return TEXTURE_ARMOR_STAND;
	}

	public ModelArmorStand getMainModel() {
		return (ModelArmorStand) super.getMainModel();
	}

	protected void rotateCorpse(EntityArmorStand var1, float var2, float f, float var4) {
		GlStateManager.rotate(180.0F - f, 0.0F, 1.0F, 0.0F);
	}

	protected boolean canRenderName(EntityArmorStand entityarmorstand) {
		return entityarmorstand.getAlwaysRenderNameTag();
	}
}
