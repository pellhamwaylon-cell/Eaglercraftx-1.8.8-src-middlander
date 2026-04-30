package net.minecraft.enchantment;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentUntouching extends Enchantment {
	protected EnchantmentUntouching(int parInt1, ResourceLocation parResourceLocation, int parInt2) {
		super(parInt1, parResourceLocation, parInt2, EnumEnchantmentType.DIGGER);
		this.setName("untouching");
	}

	public int getMinEnchantability(int var1) {
		return 15;
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 1;
	}

	public boolean canApplyTogether(Enchantment enchantment) {
		return super.canApplyTogether(enchantment) && enchantment.effectId != fortune.effectId;
	}

	public boolean canApply(ItemStack itemstack) {
		return itemstack.getItem() == Items.shears ? true : super.canApply(itemstack);
	}
}
