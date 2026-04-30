package net.minecraft.enchantment;

import net.minecraft.util.ResourceLocation;

public class EnchantmentLootBonus extends Enchantment {
	protected EnchantmentLootBonus(int parInt1, ResourceLocation parResourceLocation, int parInt2,
			EnumEnchantmentType parEnumEnchantmentType) {
		super(parInt1, parResourceLocation, parInt2, parEnumEnchantmentType);
		if (parEnumEnchantmentType == EnumEnchantmentType.DIGGER) {
			this.setName("lootBonusDigger");
		} else if (parEnumEnchantmentType == EnumEnchantmentType.FISHING_ROD) {
			this.setName("lootBonusFishing");
		} else {
			this.setName("lootBonus");
		}

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

	public boolean canApplyTogether(Enchantment enchantment) {
		return super.canApplyTogether(enchantment) && enchantment.effectId != silkTouch.effectId;
	}
}
