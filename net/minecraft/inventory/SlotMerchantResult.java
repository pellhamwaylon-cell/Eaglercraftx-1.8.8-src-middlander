package net.minecraft.inventory;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.village.MerchantRecipe;

public class SlotMerchantResult extends Slot {
	private final InventoryMerchant theMerchantInventory;
	private EntityPlayer thePlayer;
	private int field_75231_g;
	private final IMerchant theMerchant;

	public SlotMerchantResult(EntityPlayer player, IMerchant merchant, InventoryMerchant merchantInventory,
			int slotIndex, int xPosition, int yPosition) {
		super(merchantInventory, slotIndex, xPosition, yPosition);
		this.thePlayer = player;
		this.theMerchant = merchant;
		this.theMerchantInventory = merchantInventory;
	}

	public boolean isItemValid(ItemStack var1) {
		return false;
	}

	public ItemStack decrStackSize(int i) {
		if (this.getHasStack()) {
			this.field_75231_g += Math.min(i, this.getStack().stackSize);
		}

		return super.decrStackSize(i);
	}

	protected void onCrafting(ItemStack itemstack, int i) {
		this.field_75231_g += i;
		this.onCrafting(itemstack);
	}

	protected void onCrafting(ItemStack itemstack) {
		itemstack.onCrafting(this.thePlayer.worldObj, this.thePlayer, this.field_75231_g);
		this.field_75231_g = 0;
	}

	public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack) {
		this.onCrafting(itemstack);
		MerchantRecipe merchantrecipe = this.theMerchantInventory.getCurrentRecipe();
		if (merchantrecipe != null) {
			ItemStack itemstack1 = this.theMerchantInventory.getStackInSlot(0);
			ItemStack itemstack2 = this.theMerchantInventory.getStackInSlot(1);
			if (this.doTrade(merchantrecipe, itemstack1, itemstack2)
					|| this.doTrade(merchantrecipe, itemstack2, itemstack1)) {
				this.theMerchant.useRecipe(merchantrecipe);
				entityplayer.triggerAchievement(StatList.timesTradedWithVillagerStat);
				if (itemstack1 != null && itemstack1.stackSize <= 0) {
					itemstack1 = null;
				}

				if (itemstack2 != null && itemstack2.stackSize <= 0) {
					itemstack2 = null;
				}

				this.theMerchantInventory.setInventorySlotContents(0, itemstack1);
				this.theMerchantInventory.setInventorySlotContents(1, itemstack2);
			}
		}

	}

	private boolean doTrade(MerchantRecipe trade, ItemStack firstItem, ItemStack secondItem) {
		ItemStack itemstack = trade.getItemToBuy();
		ItemStack itemstack1 = trade.getSecondItemToBuy();
		if (firstItem != null && firstItem.getItem() == itemstack.getItem()) {
			if (itemstack1 != null && secondItem != null && itemstack1.getItem() == secondItem.getItem()) {
				firstItem.stackSize -= itemstack.stackSize;
				secondItem.stackSize -= itemstack1.stackSize;
				return true;
			}

			if (itemstack1 == null && secondItem == null) {
				firstItem.stackSize -= itemstack.stackSize;
				return true;
			}
		}

		return false;
	}
}
