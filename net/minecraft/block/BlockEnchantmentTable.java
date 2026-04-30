package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockEnchantmentTable extends BlockContainer {
	protected BlockEnchantmentTable() {
		super(Material.rock, MapColor.redColor);
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
		this.setLightOpacity(0);
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public boolean isFullCube() {
		return false;
	}

	public void randomDisplayTick(World world, BlockPos blockpos, IBlockState iblockstate, EaglercraftRandom random) {
		super.randomDisplayTick(world, blockpos, iblockstate, random);

		for (int i = -2; i <= 2; ++i) {
			for (int j = -2; j <= 2; ++j) {
				if (i > -2 && i < 2 && j == -1) {
					j = 2;
				}

				if (random.nextInt(16) == 0) {
					for (int k = 0; k <= 1; ++k) {
						BlockPos blockpos1 = blockpos.add(i, k, j);
						if (world.getBlockState(blockpos1).getBlock() == Blocks.bookshelf) {
							if (!world.isAirBlock(blockpos.add(i / 2, 0, j / 2))) {
								break;
							}

							world.spawnParticle(EnumParticleTypes.ENCHANTMENT_TABLE, (double) blockpos.getX() + 0.5D,
									(double) blockpos.getY() + 2.0D, (double) blockpos.getZ() + 0.5D,
									(double) ((float) i + random.nextFloat()) - 0.5D,
									(double) ((float) k - random.nextFloat() - 1.0F),
									(double) ((float) j + random.nextFloat()) - 0.5D, new int[0]);
						}
					}
				}
			}
		}

	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderType() {
		return 3;
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnchantmentTable();
	}

	public boolean onBlockActivated(World world, BlockPos blockpos, IBlockState var3, EntityPlayer entityplayer,
			EnumFacing var5, float var6, float var7, float var8) {
		if (!world.isRemote) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityEnchantmentTable) {
				entityplayer.displayGui((TileEntityEnchantmentTable) tileentity);
			}
		}
		return true;
	}

	public void onBlockPlacedBy(World world, BlockPos blockpos, IBlockState iblockstate,
			EntityLivingBase entitylivingbase, ItemStack itemstack) {
		super.onBlockPlacedBy(world, blockpos, iblockstate, entitylivingbase, itemstack);
		if (itemstack.hasDisplayName()) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityEnchantmentTable) {
				((TileEntityEnchantmentTable) tileentity).setCustomName(itemstack.getDisplayName());
			}
		}

	}
}
