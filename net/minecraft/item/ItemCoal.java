package net.minecraft.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;

public class ItemCoal extends Item {
	public ItemCoal() {
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setCreativeTab(CreativeTabs.tabMaterials);
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		return itemstack.getMetadata() == 1 ? "item.charcoal" : "item.coal";
	}

	public void getSubItems(Item item, CreativeTabs var2, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}
}
