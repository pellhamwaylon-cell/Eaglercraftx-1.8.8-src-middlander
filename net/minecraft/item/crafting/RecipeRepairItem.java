package net.minecraft.item.crafting;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class RecipeRepairItem implements IRecipe {
	public boolean matches(InventoryCrafting inventorycrafting, World var2) {
		ArrayList arraylist = Lists.newArrayList();

		for (int i = 0; i < inventorycrafting.getSizeInventory(); ++i) {
			ItemStack itemstack = inventorycrafting.getStackInSlot(i);
			if (itemstack != null) {
				arraylist.add(itemstack);
				if (arraylist.size() > 1) {
					ItemStack itemstack1 = (ItemStack) arraylist.get(0);
					if (itemstack.getItem() != itemstack1.getItem() || itemstack1.stackSize != 1
							|| itemstack.stackSize != 1 || !itemstack1.getItem().isDamageable()) {
						return false;
					}
				}
			}
		}

		return arraylist.size() == 2;
	}

	public ItemStack getCraftingResult(InventoryCrafting inventorycrafting) {
		ArrayList arraylist = Lists.newArrayList();

		for (int i = 0; i < inventorycrafting.getSizeInventory(); ++i) {
			ItemStack itemstack = inventorycrafting.getStackInSlot(i);
			if (itemstack != null) {
				arraylist.add(itemstack);
				if (arraylist.size() > 1) {
					ItemStack itemstack1 = (ItemStack) arraylist.get(0);
					if (itemstack.getItem() != itemstack1.getItem() || itemstack1.stackSize != 1
							|| itemstack.stackSize != 1 || !itemstack1.getItem().isDamageable()) {
						return null;
					}
				}
			}
		}

		if (arraylist.size() == 2) {
			ItemStack itemstack2 = (ItemStack) arraylist.get(0);
			ItemStack itemstack3 = (ItemStack) arraylist.get(1);
			if (itemstack2.getItem() == itemstack3.getItem() && itemstack2.stackSize == 1 && itemstack3.stackSize == 1
					&& itemstack2.getItem().isDamageable()) {
				Item item = itemstack2.getItem();
				int j = item.getMaxDamage() - itemstack2.getItemDamage();
				int k = item.getMaxDamage() - itemstack3.getItemDamage();
				int l = j + k + item.getMaxDamage() * 5 / 100;
				int i1 = item.getMaxDamage() - l;
				if (i1 < 0) {
					i1 = 0;
				}

				return new ItemStack(itemstack2.getItem(), 1, i1);
			}
		}

		return null;
	}

	public int getRecipeSize() {
		return 4;
	}

	public ItemStack getRecipeOutput() {
		return null;
	}

	public ItemStack[] getRemainingItems(InventoryCrafting inventorycrafting) {
		ItemStack[] aitemstack = new ItemStack[inventorycrafting.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i) {
			ItemStack itemstack = inventorycrafting.getStackInSlot(i);
			if (itemstack != null && itemstack.getItem().hasContainerItem()) {
				aitemstack[i] = new ItemStack(itemstack.getItem().getContainerItem());
			}
		}

		return aitemstack;
	}
}
