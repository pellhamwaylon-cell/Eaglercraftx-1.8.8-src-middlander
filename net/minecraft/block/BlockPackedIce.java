package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockPackedIce extends Block {
	public BlockPackedIce() {
		super(Material.packedIce);
		this.slipperiness = 0.98F;
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 0;
	}
}
