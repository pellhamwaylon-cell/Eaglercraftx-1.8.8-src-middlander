package net.minecraft.item.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ShapelessRecipes implements IRecipe {
	private final ItemStack recipeOutput;
	private final List<ItemStack> recipeItems;

	public ShapelessRecipes(ItemStack output, List<ItemStack> inputList) {
		this.recipeOutput = output;
		this.recipeItems = inputList;
	}

	public ItemStack getRecipeOutput() {
		return this.recipeOutput;
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

	public boolean matches(InventoryCrafting inventorycrafting, World var2) {
		ArrayList<ItemStack> arraylist = Lists.newArrayList(this.recipeItems);

		for (int i = 0; i < inventorycrafting.getHeight(); ++i) {
			for (int j = 0; j < inventorycrafting.getWidth(); ++j) {
				ItemStack itemstack = inventorycrafting.getStackInRowAndColumn(j, i);
				if (itemstack != null) {
					boolean flag = false;

					for (int m = 0, l = arraylist.size(); m < l; ++m) {
						ItemStack itemstack1 = arraylist.get(m);
						if (itemstack.getItem() == itemstack1.getItem() && (itemstack1.getMetadata() == 32767
								|| itemstack.getMetadata() == itemstack1.getMetadata())) {
							flag = true;
							arraylist.remove(itemstack1);
							break;
						}
					}

					if (!flag) {
						return false;
					}
				}
			}
		}

		return arraylist.isEmpty();
	}

	public ItemStack getCraftingResult(InventoryCrafting var1) {
		return this.recipeOutput.copy();
	}

	public int getRecipeSize() {
		return this.recipeItems.size();
	}
}
