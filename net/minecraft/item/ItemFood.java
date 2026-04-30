package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFood extends Item {
	public final int itemUseDuration;
	private final int healAmount;
	private final float saturationModifier;
	private final boolean isWolfsFavoriteMeat;
	private boolean alwaysEdible;
	private int potionId;
	private int potionDuration;
	private int potionAmplifier;
	private float potionEffectProbability;

	public ItemFood(int amount, float saturation, boolean isWolfFood) {
		this.itemUseDuration = 32;
		this.healAmount = amount;
		this.isWolfsFavoriteMeat = isWolfFood;
		this.saturationModifier = saturation;
		this.setCreativeTab(CreativeTabs.tabFood);
	}

	public ItemFood(int amount, boolean isWolfFood) {
		this(amount, 0.6F, isWolfFood);
	}

	public ItemStack onItemUseFinish(ItemStack itemstack, World world, EntityPlayer entityplayer) {
		--itemstack.stackSize;
		entityplayer.getFoodStats().addStats(this, itemstack);
		world.playSoundAtEntity(entityplayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		this.onFoodEaten(itemstack, world, entityplayer);
		entityplayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
		return itemstack;
	}

	protected void onFoodEaten(ItemStack var1, World world, EntityPlayer entityplayer) {
		if (!world.isRemote && this.potionId > 0 && world.rand.nextFloat() < this.potionEffectProbability) {
			entityplayer
					.addPotionEffect(new PotionEffect(this.potionId, this.potionDuration * 20, this.potionAmplifier));
		}

	}

	public int getMaxItemUseDuration(ItemStack var1) {
		return 32;
	}

	public EnumAction getItemUseAction(ItemStack var1) {
		return EnumAction.EAT;
	}

	public ItemStack onItemRightClick(ItemStack itemstack, World var2, EntityPlayer entityplayer) {
		if (entityplayer.canEat(this.alwaysEdible)) {
			entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
		}

		return itemstack;
	}

	public int getHealAmount(ItemStack var1) {
		return this.healAmount;
	}

	public float getSaturationModifier(ItemStack var1) {
		return this.saturationModifier;
	}

	public boolean isWolfsFavoriteMeat() {
		return this.isWolfsFavoriteMeat;
	}

	public ItemFood setPotionEffect(int id, int duration, int amplifier, float probability) {
		this.potionId = id;
		this.potionDuration = duration;
		this.potionAmplifier = amplifier;
		this.potionEffectProbability = probability;
		return this;
	}

	public ItemFood setAlwaysEdible() {
		this.alwaysEdible = true;
		return this;
	}
}
