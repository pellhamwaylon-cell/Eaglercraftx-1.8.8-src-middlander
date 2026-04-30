package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.MathHelper;

public class BlockGlowstone extends Block {
	public BlockGlowstone(Material materialIn) {
		super(materialIn);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int quantityDroppedWithBonus(int i, EaglercraftRandom random) {
		return MathHelper.clamp_int(this.quantityDropped(random) + random.nextInt(i + 1), 1, 4);
	}

	public int quantityDropped(EaglercraftRandom random) {
		return 2 + random.nextInt(3);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.glowstone_dust;
	}

	public MapColor getMapColor(IBlockState var1) {
		return MapColor.sandColor;
	}
}
