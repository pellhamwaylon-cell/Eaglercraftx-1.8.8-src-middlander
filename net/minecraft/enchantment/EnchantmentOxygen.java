package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentOxygen extends Enchantment {
	public EnchantmentOxygen(int enchID, ResourceLocation parResourceLocation, int parInt1) {
		super(enchID, parResourceLocation, parInt1, EnumEnchantmentType.ARMOR_HEAD);
		this.setName("oxygen");
	}

	public int getMinEnchantability(int i) {
		return 10 * i;
	}

	public int getMaxEnchantability(int i) {
		return this.getMinEnchantability(i) + 30;
	}

	public int getMaxLevel() {
		return 3;
	}
}
