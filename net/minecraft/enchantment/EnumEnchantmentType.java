package net.minecraft.enchantment;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;

public enum EnumEnchantmentType {
	ALL, ARMOR, ARMOR_FEET, ARMOR_LEGS, ARMOR_TORSO, ARMOR_HEAD, WEAPON, DIGGER, FISHING_ROD, BREAKABLE, BOW;

	public boolean canEnchantItem(Item parItem) {
		if (this == ALL) {
			return true;
		} else if (this == BREAKABLE && parItem.isDamageable()) {
			return true;
		} else if (parItem instanceof ItemArmor) {
			if (this == ARMOR) {
				return true;
			} else {
				ItemArmor itemarmor = (ItemArmor) parItem;
				return itemarmor.armorType == 0 ? this == ARMOR_HEAD
						: (itemarmor.armorType == 2 ? this == ARMOR_LEGS
								: (itemarmor.armorType == 1 ? this == ARMOR_TORSO
										: (itemarmor.armorType == 3 ? this == ARMOR_FEET : false)));
			}
		} else {
			return parItem instanceof ItemSword ? this == WEAPON
					: (parItem instanceof ItemTool ? this == DIGGER
							: (parItem instanceof ItemBow ? this == BOW
									: (parItem instanceof ItemFishingRod ? this == FISHING_ROD : false)));
		}
	}
}
