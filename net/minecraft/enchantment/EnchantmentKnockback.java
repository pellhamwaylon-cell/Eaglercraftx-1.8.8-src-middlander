package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentKnockback extends Enchantment {
	protected EnchantmentKnockback(int parInt1, ResourceLocation parResourceLocation, int parInt2) {
		super(parInt1, parResourceLocation, parInt2, EnumEnchantmentType.WEAPON);
		this.setName("knockback");
	}

	public int getMinEnchantability(int i) {
		return 5 + 20 * (i - 1);
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 2;
	}
}
