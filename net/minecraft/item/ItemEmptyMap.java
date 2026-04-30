package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class ItemEmptyMap extends ItemMapBase {
	protected ItemEmptyMap() {
		this.setCreativeTab(CreativeTabs.tabMisc);
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		ItemStack itemstack1 = new ItemStack(Items.filled_map, 1, world.getUniqueDataId("map"));
		String s = "map_" + itemstack1.getMetadata();
		MapData mapdata = new MapData(s);
		world.setItemData(s, mapdata);
		mapdata.scale = 0;
		mapdata.calculateMapCenter(entityplayer.posX, entityplayer.posZ, mapdata.scale);
		mapdata.dimension = (byte) world.provider.getDimensionId();
		mapdata.markDirty();
		--itemstack.stackSize;
		if (itemstack.stackSize <= 0) {
			return itemstack1;
		} else {
			if (!entityplayer.inventory.addItemStackToInventory(itemstack1.copy())) {
				entityplayer.dropPlayerItemWithRandomChoice(itemstack1, false);
			}

			entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
			return itemstack;
		}
	}
}
