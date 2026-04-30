package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockRedstoneLight extends Block {
	private final boolean isOn;

	public BlockRedstoneLight(boolean isOn) {
		super(Material.redstoneLight);
		this.isOn = isOn;
		if (isOn) {
			this.setLightLevel(1.0F);
		}

	}

	public void onBlockAdded(World world, BlockPos blockpos, IBlockState var3) {
		if (!world.isRemote) {
			if (this.isOn && !world.isBlockPowered(blockpos)) {
				world.setBlockState(blockpos, Blocks.redstone_lamp.getDefaultState(), 2);
			} else if (!this.isOn && world.isBlockPowered(blockpos)) {
				world.setBlockState(blockpos, Blocks.lit_redstone_lamp.getDefaultState(), 2);
			}
		}
	}

	public void onNeighborBlockChange(World world, BlockPos blockpos, IBlockState var3, Block var4) {
		if (!world.isRemote) {
			if (this.isOn && !world.isBlockPowered(blockpos)) {
				world.scheduleUpdate(blockpos, this, 4);
			} else if (!this.isOn && world.isBlockPowered(blockpos)) {
				world.setBlockState(blockpos, Blocks.lit_redstone_lamp.getDefaultState(), 2);
			}
		}
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom var4) {
		if (!world.isRemote) {
			if (this.isOn && !world.isBlockPowered(blockpos)) {
				world.setBlockState(blockpos, Blocks.redstone_lamp.getDefaultState(), 2);
			}
		}
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Item.getItemFromBlock(Blocks.redstone_lamp);
	}

	public Item getItem(World var1, BlockPos var2) {
		return Item.getItemFromBlock(Blocks.redstone_lamp);
	}

	protected ItemStack createStackedBlock(IBlockState var1) {
		return new ItemStack(Blocks.redstone_lamp);
	}
}
