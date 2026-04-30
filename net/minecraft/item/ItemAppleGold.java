package net.minecraft.item;

import java.util.List;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAppleGold extends ItemFood {
	public ItemAppleGold(int amount, float saturation, boolean isWolfFood) {
		super(amount, saturation, isWolfFood);
		this.setHasSubtypes(true);
	}

	public boolean hasEffect(ItemStack itemstack) {
		return itemstack.getMetadata() > 0;
	}

	public EnumRarity getRarity(ItemStack itemstack) {
		return itemstack.getMetadata() == 0 ? EnumRarity.RARE : EnumRarity.EPIC;
	}

	protected void onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			entityplayer.addPotionEffect(new PotionEffect(Potion.absorption.id, 2400, 0));
		}

		if (itemstack.getMetadata() > 0) {
			if (!world.isRemote) {
				entityplayer.addPotionEffect(new PotionEffect(Potion.regeneration.id, 600, 4));
				entityplayer.addPotionEffect(new PotionEffect(Potion.resistance.id, 6000, 0));
				entityplayer.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 6000, 0));
			}
		} else {
			super.onFoodEaten(itemstack, world, entityplayer);
		}

	}

	public void getSubItems(Item item, CreativeTabs var2, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}
