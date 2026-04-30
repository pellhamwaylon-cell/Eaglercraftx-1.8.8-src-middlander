package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockClay extends Block {
	public BlockClay() {
		super(Material.clay);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.clay_ball;
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 4;
	}
}
