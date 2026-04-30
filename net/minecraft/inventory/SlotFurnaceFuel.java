package net.minecraft.inventory;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class SlotFurnaceFuel extends Slot {
	public SlotFurnaceFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
		super(inventoryIn, slotIndex, xPosition, yPosition);
	}

	public boolean isItemValid(ItemStack itemstack) {
		return TileEntityFurnace.isItemFuel(itemstack) || isBucket(itemstack);
	}

	public int getItemStackLimit(ItemStack itemstack) {
		return isBucket(itemstack) ? 1 : super.getItemStackLimit(itemstack);
	}

	public static boolean isBucket(ItemStack parItemStack) {
		return parItemStack != null && parItemStack.getItem() != null && parItemStack.getItem() == Items.bucket;
	}
}
