package net.minecraft.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class InventoryLargeChest implements ILockableContainer {
	private String name;
	private ILockableContainer upperChest;
	private ILockableContainer lowerChest;

	public InventoryLargeChest(String nameIn, ILockableContainer upperChestIn, ILockableContainer lowerChestIn) {
		this.name = nameIn;
		if (upperChestIn == null) {
			upperChestIn = lowerChestIn;
		}

		if (lowerChestIn == null) {
			lowerChestIn = upperChestIn;
		}

		this.upperChest = upperChestIn;
		this.lowerChest = lowerChestIn;
		if (upperChestIn.isLocked()) {
			lowerChestIn.setLockCode(upperChestIn.getLockCode());
		} else if (lowerChestIn.isLocked()) {
			upperChestIn.setLockCode(lowerChestIn.getLockCode());
		}

	}

	public int getSizeInventory() {
		return this.upperChest.getSizeInventory() + this.lowerChest.getSizeInventory();
	}

	public boolean isPartOfLargeChest(IInventory inventoryIn) {
		return this.upperChest == inventoryIn || this.lowerChest == inventoryIn;
	}

	public String getName() {
		return this.upperChest.hasCustomName() ? this.upperChest.getName()
				: (this.lowerChest.hasCustomName() ? this.lowerChest.getName() : this.name);
	}

	public boolean hasCustomName() {
		return this.upperChest.hasCustomName() || this.lowerChest.hasCustomName();
	}

	public IChatComponent getDisplayName() {
		return (IChatComponent) (this.hasCustomName() ? new ChatComponentText(this.getName())
				: new ChatComponentTranslation(this.getName(), new Object[0]));
	}

	public ItemStack getStackInSlot(int i) {
		return i >= this.upperChest.getSizeInventory()
				? this.lowerChest.getStackInSlot(i - this.upperChest.getSizeInventory())
				: this.upperChest.getStackInSlot(i);
	}

	public ItemStack decrStackSize(int i, int j) {
		return i >= this.upperChest.getSizeInventory()
				? this.lowerChest.decrStackSize(i - this.upperChest.getSizeInventory(), j)
				: this.upperChest.decrStackSize(i, j);
	}

	public ItemStack removeStackFromSlot(int i) {
		return i >= this.upperChest.getSizeInventory()
				? this.lowerChest.removeStackFromSlot(i - this.upperChest.getSizeInventory())
				: this.upperChest.removeStackFromSlot(i);
	}

	public void setInventorySlotContents(int i, ItemStack itemstack) {
		if (i >= this.upperChest.getSizeInventory()) {
			this.lowerChest.setInventorySlotContents(i - this.upperChest.getSizeInventory(), itemstack);
		} else {
			this.upperChest.setInventorySlotContents(i, itemstack);
		}

	}

	public int getInventoryStackLimit() {
		return this.upperChest.getInventoryStackLimit();
	}

	public void markDirty() {
		this.upperChest.markDirty();
		this.lowerChest.markDirty();
	}

	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return this.upperChest.isUseableByPlayer(entityplayer) && this.lowerChest.isUseableByPlayer(entityplayer);
	}

	public void openInventory(EntityPlayer entityplayer) {
		this.upperChest.openInventory(entityplayer);
		this.lowerChest.openInventory(entityplayer);
	}

	public void closeInventory(EntityPlayer entityplayer) {
		this.upperChest.closeInventory(entityplayer);
		this.lowerChest.closeInventory(entityplayer);
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

	public boolean isLocked() {
		return this.upperChest.isLocked() || this.lowerChest.isLocked();
	}

	public void setLockCode(LockCode lockcode) {
		this.upperChest.setLockCode(lockcode);
		this.lowerChest.setLockCode(lockcode);
	}

	public LockCode getLockCode() {
		return this.upperChest.getLockCode();
	}

	public String getGuiID() {
		return this.upperChest.getGuiID();
	}

	public Container createContainer(InventoryPlayer inventoryplayer, EntityPlayer entityplayer) {
		return new ContainerChest(inventoryplayer, this, entityplayer);
	}

	public void clear() {
		this.upperChest.clear();
		this.lowerChest.clear();
	}
}
