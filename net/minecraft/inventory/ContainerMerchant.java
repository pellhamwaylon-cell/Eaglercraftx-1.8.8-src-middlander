package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerMerchant extends Container {
	private IMerchant theMerchant;
	private InventoryMerchant merchantInventory;
	private final World theWorld;

	public ContainerMerchant(InventoryPlayer playerInventory, IMerchant merchant, World worldIn) {
		this.theMerchant = merchant;
		this.theWorld = worldIn;
		this.merchantInventory = new InventoryMerchant(playerInventory.player, merchant);
		this.addSlotToContainer(new Slot(this.merchantInventory, 0, 36, 53));
		this.addSlotToContainer(new Slot(this.merchantInventory, 1, 62, 53));
		this.addSlotToContainer(
				new SlotMerchantResult(playerInventory.player, merchant, this.merchantInventory, 2, 120, 53));

		for (int i = 0; i < 3; ++i) {
			for (int j = 0; j < 9; ++j) {
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}

		for (int k = 0; k < 9; ++k) {
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}

	}

	public InventoryMerchant getMerchantInventory() {
		return this.merchantInventory;
	}

	public void onCraftGuiOpened(ICrafting icrafting) {
		super.onCraftGuiOpened(icrafting);
	}

	public void detectAndSendChanges() {
		super.detectAndSendChanges();
	}

	public void onCraftMatrixChanged(IInventory iinventory) {
		this.merchantInventory.resetRecipeAndSlots();
		super.onCraftMatrixChanged(iinventory);
	}

	public void setCurrentRecipeIndex(int currentRecipeIndex) {
		this.merchantInventory.setCurrentRecipeIndex(currentRecipeIndex);
	}

	public void updateProgressBar(int var1, int var2) {
	}

	public boolean canInteractWith(EntityPlayer entityplayer) {
		return this.theMerchant.getCustomer() == entityplayer;
	}

	public ItemStack transferStackInSlot(EntityPlayer entityplayer, int i) {
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(i);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (i == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return null;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (i != 0 && i != 1) {
				if (i >= 3 && i < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return null;
					}
				} else if (i >= 30 && i < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return null;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
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

	public void onContainerClosed(EntityPlayer entityplayer) {
		super.onContainerClosed(entityplayer);
		this.theMerchant.setCustomer((EntityPlayer) null);
		super.onContainerClosed(entityplayer);
		if (!this.theWorld.isRemote) {
			ItemStack itemstack = this.merchantInventory.removeStackFromSlot(0);
			if (itemstack != null) {
				entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}

			itemstack = this.merchantInventory.removeStackFromSlot(1);
			if (itemstack != null) {
				entityplayer.dropPlayerItemWithRandomChoice(itemstack, false);
			}
		}
	}
}
