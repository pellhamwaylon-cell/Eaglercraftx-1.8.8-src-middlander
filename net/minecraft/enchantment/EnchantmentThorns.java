package net.minecraft.enchantment;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

public class EnchantmentThorns extends Enchantment {
	public EnchantmentThorns(int parInt1, ResourceLocation parResourceLocation, int parInt2) {
		super(parInt1, parResourceLocation, parInt2, EnumEnchantmentType.ARMOR_TORSO);
		this.setName("thorns");
	}

	public int getMinEnchantability(int i) {
		return 10 + 20 * (i - 1);
	}

	public int getMaxEnchantability(int i) {
		return super.getMinEnchantability(i) + 50;
	}

	public int getMaxLevel() {
		return 3;
	}

	public boolean canApply(ItemStack itemstack) {
		return itemstack.getItem() instanceof ItemArmor ? true : super.canApply(itemstack);
	}

	public void onUserHurt(EntityLivingBase entitylivingbase, Entity entity, int i) {
		EaglercraftRandom random = entitylivingbase.getRNG();
		ItemStack itemstack = EnchantmentHelper.getEnchantedItem(Enchantment.thorns, entitylivingbase);
		if (func_92094_a(i, random)) {
			if (entity != null) {
				entity.attackEntityFrom(DamageSource.causeThornsDamage(entitylivingbase),
						(float) func_92095_b(i, random));
				entity.playSound("damage.thorns", 0.5F, 1.0F);
			}

			if (itemstack != null) {
				itemstack.damageItem(3, entitylivingbase);
			}
		} else if (itemstack != null) {
			itemstack.damageItem(1, entitylivingbase);
		}

	}

	public static boolean func_92094_a(int parInt1, EaglercraftRandom parRandom) {
		return parInt1 <= 0 ? false : parRandom.nextFloat() < 0.15F * (float) parInt1;
	}

	public static int func_92095_b(int parInt1, EaglercraftRandom parRandom) {
		return parInt1 > 10 ? parInt1 - 10 : 1 + parRandom.nextInt(4);
	}
}
