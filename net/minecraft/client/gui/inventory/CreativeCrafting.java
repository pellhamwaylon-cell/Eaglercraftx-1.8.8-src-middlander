package net.minecraft.client.gui.inventory;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class CreativeCrafting implements ICrafting {
	private final Minecraft mc;

	public CreativeCrafting(Minecraft mc) {
		this.mc = mc;
	}

	public void updateCraftingInventory(Container var1, List<ItemStack> var2) {
	}

	public void sendSlotContents(Container var1, int i, ItemStack itemstack) {
		this.mc.playerController.sendSlotPacket(itemstack, i);
	}

	public void sendProgressBarUpdate(Container var1, int var2, int var3) {
	}

	public void func_175173_a(Container var1, IInventory var2) {
	}
}
