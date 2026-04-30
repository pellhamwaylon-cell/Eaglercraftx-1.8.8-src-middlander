package net.minecraft.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.world.World;

public class ItemSoup extends ItemFood {
	public ItemSoup(int healAmount) {
		super(healAmount, false);
		this.setMaxStackSize(1);
	}

	public ItemStack onItemUseFinish(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		super.onItemUseFinish(itemstack, world, entityplayer);
		return new ItemStack(Items.bowl);
	}
}
