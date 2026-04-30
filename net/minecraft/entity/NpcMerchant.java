package net.minecraft.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;

public class NpcMerchant implements IMerchant {
	private InventoryMerchant theMerchantInventory;
	private EntityPlayer customer;
	private MerchantRecipeList recipeList;
	private IChatComponent field_175548_d;

	public NpcMerchant(EntityPlayer parEntityPlayer, IChatComponent parIChatComponent) {
		this.customer = parEntityPlayer;
		this.field_175548_d = parIChatComponent;
		this.theMerchantInventory = new InventoryMerchant(parEntityPlayer, this);
	}

	public EntityPlayer getCustomer() {
		return this.customer;
	}

	public void setCustomer(EntityPlayer var1) {
	}

	public MerchantRecipeList getRecipes(EntityPlayer var1) {
		return this.recipeList;
	}

	public void setRecipes(MerchantRecipeList merchantrecipelist) {
		this.recipeList = merchantrecipelist;
	}

	public void useRecipe(MerchantRecipe merchantrecipe) {
		merchantrecipe.incrementToolUses();
	}

	public void verifySellingItem(ItemStack var1) {
	}

	public IChatComponent getDisplayName() {
		return (IChatComponent) (this.field_175548_d != null ? this.field_175548_d
				: new ChatComponentTranslation("entity.Villager.name", new Object[0]));
	}
}
