package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class BlockGravel extends BlockFalling {
	public Item getItemDropped(IBlockState var1, EaglercraftRandom random, int i) {
		if (i > 3) {
			i = 3;
		}

		return random.nextInt(10 - i * 3) == 0 ? Items.flint : Item.getItemFromBlock(this);
	}

	public MapColor getMapColor(IBlockState var1) {
		return MapColor.stoneColor;
	}
}
