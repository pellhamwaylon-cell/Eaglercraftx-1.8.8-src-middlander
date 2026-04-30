package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerHopper extends Container {
	private final IInventory hopperInventory;

	public ContainerHopper(InventoryPlayer playerInventory, IInventory hopperInventoryIn, EntityPlayer player) {
		this.hopperInventory = hopperInventoryIn;
		hopperInventoryIn.openInventory(player);
		byte b0 = 51;

		for (int i = 0; i < hopperInventoryIn.getSizeInventory(); ++i) {
			this.addSlotToContainer(new Slot(hopperInventoryIn, i, 44 + i * 18, 20));
		}

		for (int k = 0; k < 3; ++k) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + k * 9 + 9, 8 + j * 18, k * 18 + b0));
			}
		}

		for (int l = 0; l < 9; ++l) {
			this.addSlotToContainer(new Slot(playerInventory, l, 8 + l * 18, 58 + b0));
		}

	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.hopperInventory.isUseableByPlayer(entityplayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer var1, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i < this.hopperInventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.hopperInventory.getSizeInventory(),
						this.inventorySlots.size(), true)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.hopperInventory.getSizeInventory(), false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	public void onContainerClosed(EntityPlayer entityplayer) {
		super.onContainerClosed(entityplayer);
		this.hopperInventory.closeInventory(entityplayer);
	}
}
