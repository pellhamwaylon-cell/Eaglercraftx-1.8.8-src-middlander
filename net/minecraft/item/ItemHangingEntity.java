package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemHangingEntity extends Item {
	private final Class<? extends EntityHanging> hangingEntityClass;

	public ItemHangingEntity(Class<? extends EntityHanging> entityClass) {
		this.hangingEntityClass = entityClass;
		this.setCreativeTab(CreativeTabs.tabDecorations);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing enumfacing, float var6, float var7, float var8) {
		if (enumfacing == EnumFacing.DOWN) {
			return false;
		} else if (enumfacing == EnumFacing.UP) {
			return false;
		} else {
			BlockPos blockpos1 = blockpos.offset(enumfacing);
			if (!entityplayer.canPlayerEdit(blockpos1, enumfacing, itemstack)) {
				return false;
			} else {
				EntityHanging entityhanging = this.createEntity(world, blockpos1, enumfacing);
				if (entityhanging != null && entityhanging.onValidSurface()) {
					if (!world.isRemote) {
						world.spawnEntityInWorld(entityhanging);
					}
					--itemstack.stackSize;
				}

				return true;
			}
		}
	}

	private EntityHanging createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide) {
		return (EntityHanging) (this.hangingEntityClass == EntityPainting.class
				? new EntityPainting(worldIn, pos, clickedSide)
				: (this.hangingEntityClass == EntityItemFrame.class ? new EntityItemFrame(worldIn, pos, clickedSide)
						: null));
	}
}
