package net.minecraft.dispenser;

import net.minecraft.item.ItemStack;

public interface IBehaviorDispenseItem {
	IBehaviorDispenseItem itemDispenseBehaviorProvider = new IBehaviorDispenseItem() {
		public ItemStack dispense(IBlockSource var1, ItemStack itemstack) {
			return itemstack;
		}
	};

	ItemStack dispense(IBlockSource var1, ItemStack var2);
}
