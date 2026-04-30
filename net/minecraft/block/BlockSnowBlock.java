package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class BlockSnowBlock extends Block {
	protected BlockSnowBlock() {
		super(Material.craftedSnow);
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.snowball;
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 4;
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom var4) {
		if (world.getLightFor(EnumSkyBlock.BLOCK, blockpos) > 11) {
			this.dropBlockAsItem(world, blockpos, world.getBlockState(blockpos), 0);
			world.setBlockToAir(blockpos);
		}

	}
}
