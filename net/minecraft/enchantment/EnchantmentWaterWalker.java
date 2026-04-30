package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentWaterWalker extends Enchantment {
	public EnchantmentWaterWalker(int parInt1, ResourceLocation parResourceLocation, int parInt2) {
		super(parInt1, parResourceLocation, parInt2, EnumEnchantmentType.ARMOR_FEET);
		this.setName("waterWalker");
	}

	public int getMinEnchantability(int i) {
		return i * 10;
	}

	public int getMaxEnchantability(int i) {
		return this.getMinEnchantability(i) + 15;
	}

	public int getMaxLevel() {
		return 3;
	}
}
