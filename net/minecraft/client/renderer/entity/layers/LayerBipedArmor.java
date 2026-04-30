package net.minecraft.client.renderer.entity.layers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;

public class LayerBipedArmor extends LayerArmorBase<ModelBiped> {
	public LayerBipedArmor(RendererLivingEntity<?> rendererIn) {
		super(rendererIn);
	}

	protected void initArmor() {
		this.field_177189_c = new ModelBiped(0.5F);
		this.field_177186_d = new ModelBiped(1.0F);
	}

	protected void func_177179_a(ModelBiped modelbiped, int i) {
		this.func_177194_a(modelbiped);
		switch (i) {
		case 1:
			modelbiped.bipedRightLeg.showModel = true;
			modelbiped.bipedLeftLeg.showModel = true;
			break;
		case 2:
			modelbiped.bipedBody.showModel = true;
			modelbiped.bipedRightLeg.showModel = true;
			modelbiped.bipedLeftLeg.showModel = true;
			break;
		case 3:
			modelbiped.bipedBody.showModel = true;
			modelbiped.bipedRightArm.showModel = true;
			modelbiped.bipedLeftArm.showModel = true;
			break;
		case 4:
			modelbiped.bipedHead.showModel = true;
			modelbiped.bipedHeadwear.showModel = true;
		}

	}

	protected void func_177194_a(ModelBiped parModelBiped) {
		parModelBiped.setInvisible(false);
	}
}
