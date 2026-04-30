package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;

public class InventoryCrafting implements IInventory {
	private final ItemStack[] stackList;
	private final int inventoryWidth;
	private final int inventoryHeight;
	private final Container eventHandler;

	public InventoryCrafting(Container eventHandlerIn, int width, int height) {
		int i = width * height;
		this.stackList = new ItemStack[i];
		this.eventHandler = eventHandlerIn;
		this.inventoryWidth = width;
		this.inventoryHeight = height;
	}

	public int getSizeInventory() {
		return this.stackList.length;
	}

	public ItemStack getStackInSlot(int i) {
		return i >= this.getSizeInventory() ? null : this.stackList[i];
	}

	public ItemStack getStackInRowAndColumn(int row, int column) {
		return row >= 0 && row < this.inventoryWidth && column >= 0 && column <= this.inventoryHeight
				? this.getStackInSlot(row + column * this.inventoryWidth)
				: null;
	}

	public String getName() {
		return "container.crafting";
	}

	public boolean hasCustomName() {
		return false;
	}

	public IChatComponent getDisplayName() {
		return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName())
				: new ChatComponentTranslation(this.getName(), new Object[0]));
	}

	public ItemStack removeStackFromSlot(int i) {
		if (this.stackList[i] != null) {
			ItemStack itemstack = this.stackList[i];
			this.stackList[i] = null;
			return itemstack;
		} else {
			return null;
		}
	}

	public ItemStack decrStackSize(int i, int j) {
		if (this.stackList[i] != null) {
			if (this.stackList[i].stackSize <= j) {
				ItemStack itemstack1 = this.stackList[i];
				this.stackList[i] = null;
				this.eventHandler.onCraftMatrixChanged(this);
				return itemstack1;
			} else {
				ItemStack itemstack = this.stackList[i].splitStack(j);
				if (this.stackList[i].stackSize == 0) {
					this.stackList[i] = null;
				}

				this.eventHandler.onCraftMatrixChanged(this);
				return itemstack;
			}
		} else {
			return null;
		}
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		this.stackList[i] = itemstack;
		this.eventHandler.onCraftMatrixChanged(this);
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	public void markDirty() {
	}

	public boolean isUseableByPlayer(EntityPlayer var1) {
		return true;
	}

	public void openInventory(EntityPlayer var1) {
	}

	public void closeInventory(EntityPlayer var1) {
	}

	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return true;
	}

	public int getField(int var1) {
		return 0;
	}

	public void setField(int var1, int var2) {
	}

	public int getFieldCount() {
		return 0;
	}

	public void clear() {
		for (int i = 0; i < this.stackList.length; ++i) {
			this.stackList[i] = null;
		}

	}

	public int getHeight() {
		return this.inventoryHeight;
	}

	public int getWidth() {
		return this.inventoryWidth;
	}
}
