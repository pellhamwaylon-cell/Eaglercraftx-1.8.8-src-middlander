package net.minecraft.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFence;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLeashKnot;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemLead extends Item {
	public ItemLead() {
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	public boolean onItemUse(ItemStack var1, EntityPlayer entityplayer, World world, BlockPos blockpos, EnumFacing var5,
			float var6, float var7, float var8) {
		Block block = world.getBlockState(blockpos).getBlock();
		if (block instanceof BlockFence) {
			if (world.isRemote) {
				return true;
			} else {
				attachToFence(entityplayer, world, blockpos);
				return true;
			}
		} else {
			return false;
		}
	}

	public static boolean attachToFence(EntityPlayer player, World worldIn, BlockPos fence) {
		EntityLeashKnot entityleashknot = EntityLeashKnot.getKnotForPosition(worldIn, fence);
		boolean flag = false;
		double d0 = 7.0D;
		int i = fence.getX();
		int j = fence.getY();
		int k = fence.getZ();

		List<EntityLiving> lst = worldIn.getEntitiesWithinAABB(EntityLiving.class, new AxisAlignedBB((double) i - d0,
				(double) j - d0, (double) k - d0, (double) i + d0, (double) j + d0, (double) k + d0));
		for (int m = 0, l = lst.size(); m < l; ++m) {
			EntityLiving entityliving = lst.get(m);
			if (entityliving.getLeashed() && entityliving.getLeashedToEntity() == player) {
				if (entityleashknot == null) {
					entityleashknot = EntityLeashKnot.createKnot(worldIn, fence);
				}

				entityliving.setLeashedToEntity(entityleashknot, true);
				flag = true;
			}
		}

		return flag;
	}
}
