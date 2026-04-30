package net.minecraft.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import com.google.common.collect.Lists;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityDispenser;

public class WeightedRandomChestContent extends WeightedRandom.Item {
	private ItemStack theItemId;
	private int minStackSize;
	private int maxStackSize;

	public WeightedRandomChestContent(Item parItem, int parInt1, int minimumChance, int maximumChance,
			int itemWeightIn) {
		super(itemWeightIn);
		this.theItemId = new ItemStack(parItem, 1, parInt1);
		this.minStackSize = minimumChance;
		this.maxStackSize = maximumChance;
	}

	public WeightedRandomChestContent(ItemStack stack, int minimumChance, int maximumChance, int itemWeightIn) {
		super(itemWeightIn);
		this.theItemId = stack;
		this.minStackSize = minimumChance;
		this.maxStackSize = maximumChance;
	}

	public static void generateChestContents(EaglercraftRandom random, List<WeightedRandomChestContent> listIn,
			IInventory inv, int max) {
		for (int i = 0; i < max; ++i) {
			WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent) WeightedRandom
					.getRandomItem(random, listIn);
			int j = weightedrandomchestcontent.minStackSize + random
					.nextInt(weightedrandomchestcontent.maxStackSize - weightedrandomchestcontent.minStackSize + 1);
			if (weightedrandomchestcontent.theItemId.getMaxStackSize() >= j) {
				ItemStack itemstack1 = weightedrandomchestcontent.theItemId.copy();
				itemstack1.stackSize = j;
				inv.setInventorySlotContents(random.nextInt(inv.getSizeInventory()), itemstack1);
			} else {
				for (int k = 0; k < j; ++k) {
					ItemStack itemstack = weightedrandomchestcontent.theItemId.copy();
					itemstack.stackSize = 1;
					inv.setInventorySlotContents(random.nextInt(inv.getSizeInventory()), itemstack);
				}
			}
		}

	}

	public static void generateDispenserContents(EaglercraftRandom random, List<WeightedRandomChestContent> listIn,
			TileEntityDispenser dispenser, int max) {
		for (int i = 0; i < max; ++i) {
			WeightedRandomChestContent weightedrandomchestcontent = (WeightedRandomChestContent) WeightedRandom
					.getRandomItem(random, listIn);
			int j = weightedrandomchestcontent.minStackSize + random
					.nextInt(weightedrandomchestcontent.maxStackSize - weightedrandomchestcontent.minStackSize + 1);
			if (weightedrandomchestcontent.theItemId.getMaxStackSize() >= j) {
				ItemStack itemstack1 = weightedrandomchestcontent.theItemId.copy();
				itemstack1.stackSize = j;
				dispenser.setInventorySlotContents(random.nextInt(dispenser.getSizeInventory()), itemstack1);
			} else {
				for (int k = 0; k < j; ++k) {
					ItemStack itemstack = weightedrandomchestcontent.theItemId.copy();
					itemstack.stackSize = 1;
					dispenser.setInventorySlotContents(random.nextInt(dispenser.getSizeInventory()), itemstack);
				}
			}
		}

	}

	public static List<WeightedRandomChestContent> func_177629_a(List<WeightedRandomChestContent> parList,
			WeightedRandomChestContent... parArrayOfWeightedRandomChestContent) {
		ArrayList arraylist = Lists.newArrayList(parList);
		Collections.addAll(arraylist, parArrayOfWeightedRandomChestContent);
		return arraylist;
	}
}
