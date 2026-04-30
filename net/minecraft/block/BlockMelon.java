package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockMelon extends Block {
	protected BlockMelon() {
		super(Material.gourd, MapColor.limeColor);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.melon;
	}

	public int quantityDropped(EaglercraftRandom random) {
		return 3 + random.nextInt(5);
	}

	public int quantityDroppedWithBonus(int i, EaglercraftRandom random) {
		return Math.min(9, this.quantityDropped(random) + random.nextInt(1 + i));
	}
}
