package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class Slot {
	private final int slotIndex;
	public final IInventory inventory;
	public int slotNumber;
	public int xDisplayPosition;
	public int yDisplayPosition;

	public Slot(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		this.inventory = inventoryIn;
		this.slotIndex = index;
		this.xDisplayPosition = xPosition;
		this.yDisplayPosition = yPosition;
	}

	public void onSlotChange(ItemStack parItemStack, ItemStack parItemStack2) {
		if (parItemStack != null && parItemStack2 != null) {
			if (parItemStack.getItem() == parItemStack2.getItem()) {
				int i = parItemStack2.stackSize - parItemStack.stackSize;
				if (i > 0) {
					this.onCrafting(parItemStack, i);
				}

			}
		}
	}

	protected void onCrafting(ItemStack var1, int var2) {
	}

	protected void onCrafting(ItemStack var1) {
	}

	public void onPickupFromSlot(EntityPlayer var1, ItemStack var2) {
		this.onSlotChanged();
	}

	public boolean isItemValid(ItemStack var1) {
		return true;
	}

	public ItemStack getStack() {
		return this.inventory.getStackInSlot(this.slotIndex);
	}

	public boolean getHasStack() {
		return this.getStack() != null;
	}

	public void putStack(ItemStack itemstack) {
		this.inventory.setInventorySlotContents(this.slotIndex, itemstack);
		this.onSlotChanged();
	}

	public void onSlotChanged() {
		this.inventory.markDirty();
	}

	public int getSlotStackLimit() {
		return this.inventory.getInventoryStackLimit();
	}

	public int getItemStackLimit(ItemStack var1) {
		return this.getSlotStackLimit();
	}

	public String getSlotTexture() {
		return null;
	}

	public ItemStack decrStackSize(int i) {
		return this.inventory.decrStackSize(this.slotIndex, i);
	}

	public boolean isHere(IInventory iinventory, int i) {
		return iinventory == this.inventory && i == this.slotIndex;
	}

	public boolean canTakeStack(EntityPlayer var1) {
		return true;
	}

	public boolean canBeHovered() {
		return true;
	}
}
