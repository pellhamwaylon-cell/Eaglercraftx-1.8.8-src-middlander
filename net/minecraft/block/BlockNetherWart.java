package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockNetherWart extends BlockBush {
	public static final PropertyInteger AGE = PropertyInteger.create("age", 0, 3);

	protected BlockNetherWart() {
		super(Material.plants, MapColor.redColor);
		this.setDefaultState(this.blockState.getBaseState().withProperty(AGE, Integer.valueOf(0)));
		this.setTickRandomly(true);
		float f = 0.5F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.25F, 0.5F + f);
		this.setCreativeTab((CreativeTabs) null);
	}

	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.soul_sand;
	}

	public boolean canBlockStay(World world, BlockPos blockpos, IBlockState var3) {
		return this.canPlaceBlockOn(world.getBlockState(blockpos.down()).getBlock());
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState iblockstate, EaglercraftRandom random) {
		int i = ((Integer) iblockstate.getValue(AGE)).intValue();
		if (i < 3 && random.nextInt(10) == 0) {
			iblockstate = iblockstate.withProperty(AGE, Integer.valueOf(i + 1));
			world.setBlockState(blockpos, iblockstate, 2);
		}

		super.updateTick(world, blockpos, iblockstate, random);
	}

	public void dropBlockAsItemWithChance(World world, BlockPos blockpos, IBlockState iblockstate, float var4, int i) {
		if (!world.isRemote) {
			int j = 1;
			if (((Integer) iblockstate.getValue(AGE)).intValue() >= 3) {
				j = 2 + world.rand.nextInt(3);
				if (i > 0) {
					j += world.rand.nextInt(i + 1);
				}
			}

			for (int k = 0; k < j; ++k) {
				spawnAsEntity(world, blockpos, new ItemStack(Items.nether_wart));
			}
		}
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return null;
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 0;
	}

	public Item getItem(World var1, BlockPos var2) {
		return Items.nether_wart;
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(AGE, Integer.valueOf(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((Integer) iblockstate.getValue(AGE)).intValue();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { AGE });
	}
}
