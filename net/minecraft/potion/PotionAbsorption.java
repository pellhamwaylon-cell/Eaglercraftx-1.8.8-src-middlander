package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;

public class PotionAbsorption extends Potion {
	protected PotionAbsorption(int potionID, ResourceLocation location, boolean badEffect, int potionColor) {
		super(potionID, location, badEffect, potionColor);
	}

	public void removeAttributesModifiersFromEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap amplifier,
			int parInt1) {
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - (float) (4 * (parInt1 + 1)));
		super.removeAttributesModifiersFromEntity(entityLivingBaseIn, amplifier, parInt1);
	}

	public void applyAttributesModifiersToEntity(EntityLivingBase entityLivingBaseIn, BaseAttributeMap amplifier,
			int parInt1) {
		entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + (float) (4 * (parInt1 + 1)));
		super.applyAttributesModifiersToEntity(entityLivingBaseIn, amplifier, parInt1);
	}
}
