package net.minecraft.enchantment;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDigging extends Enchantment {
	protected EnchantmentDigging(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.DIGGER);
		this.setName("digging");
	}

	public int getMinEnchantability(int i) {
		return 1 + 10 * (i - 1);
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 5;
	}

	public boolean canApply(ItemStack itemstack) {
		return itemstack.getItem() == Items.shears ? true : super.canApply(itemstack);
	}
}
