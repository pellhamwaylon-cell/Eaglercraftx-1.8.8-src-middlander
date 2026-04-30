package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockBookshelf extends Block {
	public BlockBookshelf() {
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 3;
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.book;
	}
}
