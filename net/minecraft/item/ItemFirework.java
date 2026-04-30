package net.minecraft.item;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemFirework extends Item {

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing var5, float f, float f1, float f2) {
		if (!world.isRemote) {
			EntityFireworkRocket entityfireworkrocket = new EntityFireworkRocket(world,
					(double) ((float) blockpos.getX() + f), (double) ((float) blockpos.getY() + f1),
					(double) ((float) blockpos.getZ() + f2), itemstack);
			world.spawnEntityInWorld(entityfireworkrocket);
			if (!entityplayer.capabilities.isCreativeMode) {
				--itemstack.stackSize;
			}

			return true;
		} else {
			return false;
		}
	}

	public void addInformation(ItemStack itemstack, EntityPlayer var2, List<String> list, boolean var4) {
		if (itemstack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = itemstack.getTagCompound().getCompoundTag("Fireworks");
			if (nbttagcompound != null) {
				if (nbttagcompound.hasKey("Flight", 99)) {
					list.add(StatCollector.translateToLocal("item.fireworks.flight") + " "
							+ nbttagcompound.getByte("Flight"));
				}

				NBTTagList nbttaglist = nbttagcompound.getTagList("Explosions", 10);
				if (nbttaglist != null && nbttaglist.tagCount() > 0) {
					for (int i = 0; i < nbttaglist.tagCount(); ++i) {
						NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
						ArrayList arraylist = Lists.newArrayList();
						ItemFireworkCharge.addExplosionInfo(nbttagcompound1, arraylist);
						if (arraylist.size() > 0) {
							for (int j = 1; j < arraylist.size(); ++j) {
								arraylist.set(j, "  " + (String) arraylist.get(j));
							}

							list.addAll(arraylist);
						}
					}
				}

			}
		}
	}

	public boolean shouldUseOnTouchEagler(ItemStack itemStack) {
		return true;
	}
}
