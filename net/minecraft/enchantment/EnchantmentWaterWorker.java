package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentWaterWorker extends Enchantment {
	public EnchantmentWaterWorker(int parInt1, ResourceLocation parResourceLocation, int parInt2) {
		super(parInt1, parResourceLocation, parInt2, EnumEnchantmentType.ARMOR_HEAD);
		this.setName("waterWorker");
	}

	public int getMinEnchantability(int var1) {
		return 1;
	}

	public int getMaxEnchantability(int i) {
		return this.getMinEnchantability(i) + 40;
	}

	public int getMaxLevel() {
		return 1;
	}
}
