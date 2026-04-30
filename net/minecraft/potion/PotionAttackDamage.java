package net.minecraft.potion;

import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.ResourceLocation;

public class PotionAttackDamage extends Potion {
	protected PotionAttackDamage(int potionID, ResourceLocation location, boolean badEffect, int potionColor) {
		super(potionID, location, badEffect, potionColor);
	}

	public double getAttributeModifierAmount(int modifier, AttributeModifier parAttributeModifier) {
		return this.id == Potion.weakness.id ? (double) (-0.5F * (float) (modifier + 1))
				: 1.3D * (double) (modifier + 1);
	}
}
