package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class ContainerBeacon extends Container {
	private IInventory tileBeacon;
	private final ContainerBeacon.BeaconSlot beaconSlot;

	public ContainerBeacon(IInventory playerInventory, IInventory tileBeaconIn) {
		this.tileBeacon = tileBeaconIn;
		this.addSlotToContainer(this.beaconSlot = new ContainerBeacon.BeaconSlot(tileBeaconIn, 0, 136, 110));
		byte b0 = 36;
		short short1 = 137;

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, b0 + j * 18, short1 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, b0 + k * 18, 58 + short1));
		}

	}

	public void onCraftGuiOpened(ICrafting icrafting) {
		super.onCraftGuiOpened(icrafting);
		icrafting.func_175173_a(this, this.tileBeacon);
	}

	public void updateProgressBar(int i, int j) {
		this.tileBeacon.setField(i, j);
	}

	public IInventory func_180611_e() {
		return this.tileBeacon;
	}

	public void onContainerClosed(EntityPlayer entityplayer) {
		super.onContainerClosed(entityplayer);
		if (entityplayer != null && !entityplayer.worldObj.isRemote) {
			ItemStack itemstack = this.beaconSlot.decrStackSize(this.beaconSlot.getSlotStackLimit());
			if (itemstack != null) {
				entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}

		}
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.tileBeacon.isUseableByPlayer(entityplayer);
	}

	public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 0) {
				if (!this.mergeItemStack(itemstack1, 1, 37, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (!this.beaconSlot.getHasStack() && this.beaconSlot.isItemValid(itemstack1)
					&& itemstack1.stackSize == 1) {
				if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
					return null;
				}
			} else if (i >= 1 && i < 28) {
				if (!this.mergeItemStack(itemstack1, 28, 37, false)) {
					return null;
				}
			} else if (i >= 28 && i < 37) {
				if (!this.mergeItemStack(itemstack1, 1, 28, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 1, 37, false)) {
				return null;
			}

			if (itemstack1.stackSize == 0) {
				slot.putStack((ItemStack) null);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.stackSize == itemstack.stackSize) {
				return null;
			}

			slot.onPickupFromSlot(entityplayer, itemstack1);
		}

		return itemstack;
	}

	class BeaconSlot extends Slot {
		public BeaconSlot(IInventory parIInventory, int parInt1, int parInt2, int parInt3) {
			super(parIInventory, parInt1, parInt2, parInt3);
		}

		public boolean isItemValid(ItemStack itemstack) {
			return itemstack == null ? false
					: itemstack.getItem() == Items.emerald || itemstack.getItem() == Items.diamond
							|| itemstack.getItem() == Items.gold_ingot || itemstack.getItem() == Items.iron_ingot;
		}

		public int getSlotStackLimit() {
			return 1;
		}
	}
}
