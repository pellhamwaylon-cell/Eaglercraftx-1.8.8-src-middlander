package net.minecraft.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.util.ResourceLocation;

public class PotionHealthBoost extends Potion {
	public PotionHealthBoost(int potionID, ResourceLocation location, boolean badEffect, int potionColor) {
		super(potionID, location, badEffect, potionColor);
	}

	public void removeAttributesModifiersFromEntity(EntityLivingBase entitylivingbase,
			BaseAttributeMap baseattributemap, int i) {
		super.removeAttributesModifiersFromEntity(entitylivingbase, baseattributemap, i);
		if (entitylivingbase.getHealth() > entitylivingbase.getMaxHealth()) {
			entitylivingbase.setHealth(entitylivingbase.getMaxHealth());
		}

	}
}
