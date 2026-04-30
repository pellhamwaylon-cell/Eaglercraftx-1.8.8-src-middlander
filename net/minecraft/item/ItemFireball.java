package net.minecraft.item;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemFireball extends Item {
	public ItemFireball() {
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing enumfacing, float var6, float var7, float var8) {
		if (world.isRemote) {
			return true;
		} else {
			blockpos = blockpos.offset(enumfacing);
			if (!entityplayer.canPlayerEdit(blockpos, enumfacing, itemstack)) {
				return false;
			} else {
				if (world.getBlockState(blockpos).getBlock().getMaterial() == Material.air) {
					world.playSoundEffect((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D,
							(double) blockpos.getZ() + 0.5D, "item.fireCharge.use", 1.0F,
							(itemRand.nextFloat() - itemRand.nextFloat()) * 0.2F + 1.0F);
					world.setBlockState(blockpos, Blocks.fire.getDefaultState());
				}

				if (!entityplayer.capabilities.isCreativeMode) {
					--itemstack.stackSize;
				}

				return true;
			}
		}
	}
}
