package net.minecraft.client.player.inventory;

import com.carrotsearch.hppc.IntIntHashMap;
import com.carrotsearch.hppc.IntIntMap;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.ILockableContainer;
import net.minecraft.world.LockCode;

public class ContainerLocalMenu extends InventoryBasic implements ILockableContainer {
	private String guiID;
	private IntIntMap field_174895_b = new IntIntHashMap();

	public ContainerLocalMenu(String id, IChatComponent title, int slotCount) {
		super(title, slotCount);
		this.guiID = id;
	}

	public int getField(int i) {
		return this.field_174895_b.getOrDefault(i, 0);
	}

	public void setField(int i, int j) {
		this.field_174895_b.put(i, j);
	}

	public int getFieldCount() {
		return this.field_174895_b.size();
	}

	public boolean isLocked() {
		return false;
	}

	public void setLockCode(LockCode var1) {
	}

	public LockCode getLockCode() {
		return LockCode.EMPTY_CODE;
	}

	public String getGuiID() {
		return this.guiID;
	}

	public Container createContainer(InventoryPlayer var1, EntityPlayer var2) {
		throw new UnsupportedOperationException();
	}
}
