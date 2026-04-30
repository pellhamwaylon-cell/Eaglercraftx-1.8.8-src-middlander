package net.minecraft.block;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockStainedGlass extends BlockBreakable {
	public static final PropertyEnum<EnumDyeColor> COLOR = PropertyEnum.<EnumDyeColor>create("color",
			EnumDyeColor.class);

	public BlockStainedGlass(Material materialIn) {
		super(materialIn, false);
		this.setDefaultState(this.blockState.getBaseState().withProperty(COLOR, EnumDyeColor.WHITE));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((EnumDyeColor) iblockstate.getValue(COLOR)).getMetadata();
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		EnumDyeColor[] colors = EnumDyeColor.META_LOOKUP;
		for (int i = 0; i < colors.length; ++i) {
			list.add(new ItemStack(item, 1, colors[i].getMetadata()));
		}

	}

	public MapColor getMapColor(IBlockState iblockstate) {
		return ((EnumDyeColor) iblockstate.getValue(COLOR)).getMapColor();
	}

	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 0;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	public boolean isFullCube() {
		return false;
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(COLOR, EnumDyeColor.byMetadata(i));
	}

	public void onBlockAdded(World world, BlockPos blockpos, IBlockState var3) {
		if (!world.isRemote) {
			BlockBeacon.updateColorAsync(world, blockpos);
		}
	}

	public void breakBlock(World world, BlockPos blockpos, IBlockState var3) {
		if (!world.isRemote) {
			BlockBeacon.updateColorAsync(world, blockpos);
		}
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((EnumDyeColor) iblockstate.getValue(COLOR)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { COLOR });
	}
}
