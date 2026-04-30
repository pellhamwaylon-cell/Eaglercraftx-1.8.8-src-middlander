package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockWeb extends Block {
	public BlockWeb() {
		super(Material.web);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity entity) {
		entity.setInWeb();
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
		return null;
	}

	public boolean isFullCube() {
		return false;
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.string;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}
}
