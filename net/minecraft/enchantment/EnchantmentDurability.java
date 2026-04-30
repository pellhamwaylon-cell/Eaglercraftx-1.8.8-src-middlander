package net.minecraft.enchantment;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class EnchantmentDurability extends Enchantment {
	protected EnchantmentDurability(int enchID, ResourceLocation enchName, int enchWeight) {
		super(enchID, enchName, enchWeight, EnumEnchantmentType.BREAKABLE);
		this.setName("durability");
	}

	public int getMinEnchantability(int i) {
		return 5 + (i - 1) * 8;
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 3;
	}

	public boolean canApply(ItemStack itemstack) {
		return itemstack.isItemStackDamageable() ? true : super.canApply(itemstack);
	}

	public static boolean negateDamage(ItemStack parItemStack, int parInt1, EaglercraftRandom parRandom) {
		return parItemStack.getItem() instanceof ItemArmor && parRandom.nextFloat() < 0.6F ? false
				: parRandom.nextInt(parInt1 + 1) > 0;
	}
}
