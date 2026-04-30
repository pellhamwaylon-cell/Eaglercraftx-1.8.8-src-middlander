package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentFishingSpeed extends Enchantment {
	protected EnchantmentFishingSpeed(int enchID, ResourceLocation enchName, int enchWeight,
			EnumEnchantmentType enchType) {
		super(enchID, enchName, enchWeight, enchType);
		this.setName("fishingSpeed");
	}

	public int getMinEnchantability(int i) {
		return 15 + (i - 1) * 9;
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 3;
	}
}
