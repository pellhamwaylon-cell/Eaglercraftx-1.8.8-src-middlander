package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentArrowFire extends Enchantment {
	public EnchantmentArrowFire(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.BOW);
		this.setName("arrowFire");
	}

	public int getMinEnchantability(int var1) {
		return 20;
	}

	public int getMaxEnchantability(int var1) {
		return 50;
	}

	public int getMaxLevel() {
		return 1;
	}
}
