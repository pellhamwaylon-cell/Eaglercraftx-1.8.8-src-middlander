package net.minecraft.client.renderer.entity;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderBiped<EntityZombie> {
	private static final ResourceLocation zombieTextures = new ResourceLocation("textures/entity/zombie/zombie.png");
	private static final ResourceLocation zombieVillagerTextures = new ResourceLocation(
			"textures/entity/zombie/zombie_villager.png");
	private final ModelBiped field_82434_o;
	private final ModelZombieVillager zombieVillagerModel;
	private final List<LayerRenderer<EntityZombie>> field_177121_n;
	private final List<LayerRenderer<EntityZombie>> field_177122_o;

	public RenderZombie(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelZombie(), 0.5F, 1.0F);
		LayerRenderer layerrenderer = (LayerRenderer) this.layerRenderers.get(0);
		this.field_82434_o = this.modelBipedMain;
		this.zombieVillagerModel = new ModelZombieVillager();
		this.addLayer(new LayerHeldItem(this));
		LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
			protected void initArmor() {
				this.field_177189_c = new ModelZombie(0.5F, false);
				this.field_177186_d = new ModelZombie(1.0F, false);
			}
		};
		this.addLayer(layerbipedarmor);
		this.field_177122_o = Lists.newArrayList(this.layerRenderers);
		if (layerrenderer instanceof LayerCustomHead) {
			this.removeLayer(layerrenderer);
			this.addLayer(new LayerCustomHead(this.zombieVillagerModel.bipedHead));
		}

		this.removeLayer(layerbipedarmor);
		this.addLayer(new LayerVillagerArmor(this));
		this.field_177121_n = Lists.newArrayList(this.layerRenderers);
	}

	public void doRender(EntityZombie entityzombie, double d0, double d1, double d2, float f, float f1) {
		this.func_82427_a(entityzombie);
		super.doRender(entityzombie, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(EntityZombie entityzombie) {
		return entityzombie.isVillager() ? zombieVillagerTextures : zombieTextures;
	}

	private void func_82427_a(EntityZombie zombie) {
		if (zombie.isVillager()) {
			this.mainModel = this.zombieVillagerModel;
			this.layerRenderers = this.field_177121_n;
		} else {
			this.mainModel = this.field_82434_o;
			this.layerRenderers = this.field_177122_o;
		}

		this.modelBipedMain = (ModelBiped) this.mainModel;
	}

	protected void rotateCorpse(EntityZombie entityzombie, float f, float f1, float f2) {
		if (entityzombie.isConverting()) {
			f1 += (float) (Math.cos((double) entityzombie.ticksExisted * 3.25D) * 3.141592653589793D * 0.25D);
		}

		super.rotateCorpse(entityzombie, f, f1, f2);
	}
}
