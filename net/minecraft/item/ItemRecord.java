package net.minecraft.item;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.block.BlockJukebox;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemRecord extends Item {
	private static final Map<String, ItemRecord> RECORDS = Maps.newHashMap();
	public final String recordName;

	protected ItemRecord(String name) {
		this.recordName = name;
		this.maxStackSize = 1;
		this.setCreativeTab(CreativeTabs.tabMisc);
		RECORDS.put("records." + name, this);
	}

	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, BlockPos blockpos,
			EnumFacing var5, float var6, float var7, float var8) {
		IBlockState iblockstate = world.getBlockState(blockpos);
		if (iblockstate.getBlock() == Blocks.jukebox
				&& !((Boolean) iblockstate.getValue(BlockJukebox.HAS_RECORD)).booleanValue()) {
			if (world.isRemote) {
				return true;
			} else {
				((BlockJukebox) Blocks.jukebox).insertRecord(world, blockpos, iblockstate, itemstack);
				world.playAuxSFXAtEntity((EntityPlayer) null, 1005, blockpos, Item.getIdFromItem(this));
				--itemstack.stackSize;
				entityplayer.triggerAchievement(StatList.field_181740_X);
				return true;
			}
		} else {
			return false;
		}
	}

	public void addInformation(ItemStack var1, EntityPlayer var2, List<String> list, boolean var4) {
		list.add(this.getRecordNameLocal());
	}

	public String getRecordNameLocal() {
		return StatCollector.translateToLocal("item.record." + this.recordName + ".desc");
	}

	public EnumRarity getRarity(ItemStack stack) {
		return EnumRarity.RARE;
	}

	public static ItemRecord getRecord(String name) {
		return (ItemRecord) RECORDS.get(name);
	}
}
