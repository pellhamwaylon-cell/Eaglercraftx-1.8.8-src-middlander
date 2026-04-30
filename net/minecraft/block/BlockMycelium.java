package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockMycelium extends Block {
	public static final PropertyBool SNOWY = PropertyBool.create("snowy");

	protected BlockMycelium() {
		super(Material.grass, MapColor.purpleColor);
		this.setDefaultState(this.blockState.getBaseState().withProperty(SNOWY, Boolean.valueOf(false)));
		this.setTickRandomly(true);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public IBlockState getActualState(IBlockState iblockstate, IBlockAccess iblockaccess, BlockPos blockpos) {
		Block block = iblockaccess.getBlockState(blockpos.up()).getBlock();
		return iblockstate.withProperty(SNOWY, Boolean.valueOf(block == Blocks.snow || block == Blocks.snow_layer));
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom random) {
		if (!world.isRemote) {
			if (world.getLightFromNeighbors(blockpos.up()) < 4
					&& world.getBlockState(blockpos.up()).getBlock().getLightOpacity() > 2) {
				world.setBlockState(blockpos,
						Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT));
			} else {
				if (world.getLightFromNeighbors(blockpos.up()) >= 9) {
					for (int i = 0; i < 4; ++i) {
						BlockPos blockpos1 = blockpos.add(random.nextInt(3) - 1, random.nextInt(5) - 3,
								random.nextInt(3) - 1);
						IBlockState iblockstate = world.getBlockState(blockpos1);
						Block block = world.getBlockState(blockpos1.up()).getBlock();
						if (iblockstate.getBlock() == Blocks.dirt
								&& iblockstate.getValue(BlockDirt.VARIANT) == BlockDirt.DirtType.DIRT
								&& world.getLightFromNeighbors(blockpos1.up()) >= 4 && block.getLightOpacity() <= 2) {
							world.setBlockState(blockpos1, this.getDefaultState());
						}
					}
				}
			}
		}
	}

	public void randomDisplayTick(World world, BlockPos blockpos, IBlockState iblockstate, EaglercraftRandom random) {
		super.randomDisplayTick(world, blockpos, iblockstate, random);
		if (random.nextInt(10) == 0) {
			world.spawnParticle(EnumParticleTypes.TOWN_AURA, (double) ((float) blockpos.getX() + random.nextFloat()),
					(double) ((float) blockpos.getY() + 1.1F), (double) ((float) blockpos.getZ() + random.nextFloat()),
					0.0D, 0.0D, 0.0D, new int[0]);
		}

	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom random, int i) {
		return Blocks.dirt.getItemDropped(
				Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT), random, i);
	}

	public int getMetaFromState(IBlockState var1) {
		return 0;
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { SNOWY });
	}
}
