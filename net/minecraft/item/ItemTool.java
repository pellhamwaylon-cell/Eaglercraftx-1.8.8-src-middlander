package net.minecraft.item;

import java.util.Set;

import com.google.common.collect.Multimap;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ItemTool extends Item {
	private Set<Block> effectiveBlocks;
	protected float efficiencyOnProperMaterial = 4.0F;
	private float damageVsEntity;
	protected Item.ToolMaterial toolMaterial;

	protected ItemTool(float attackDamage, Item.ToolMaterial material, Set<Block> effectiveBlocks) {
		this.toolMaterial = material;
		this.effectiveBlocks = effectiveBlocks;
		this.maxStackSize = 1;
		this.setMaxDamage(material.getMaxUses());
		this.efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
		this.damageVsEntity = attackDamage + material.getDamageVsEntity();
		this.setCreativeTab(CreativeTabs.tabTools);
	}

	public float getStrVsBlock(ItemStack var1, Block block) {
		return this.effectiveBlocks.contains(block) ? this.efficiencyOnProperMaterial : 1.0F;
	}

	public boolean hitEntity(ItemStack itemstack, EntityLivingBase var2, EntityLivingBase entitylivingbase) {
		itemstack.damageItem(2, entitylivingbase);
		return true;
	}

	public boolean onBlockDestroyed(ItemStack itemstack, World world, Block block, BlockPos blockpos,
			EntityLivingBase entitylivingbase) {
		if ((double) block.getBlockHardness(world, blockpos) != 0.0D) {
			itemstack.damageItem(1, entitylivingbase);
		}

		return true;
	}

	public boolean isFull3D() {
		return true;
	}

	public Item.ToolMaterial getToolMaterial() {
		return this.toolMaterial;
	}

	public int getItemEnchantability() {
		return this.toolMaterial.getEnchantability();
	}

	public String getToolMaterialName() {
		return this.toolMaterial.toString();
	}

	public boolean getIsRepairable(ItemStack itemstack, ItemStack itemstack1) {
		return this.toolMaterial.getRepairItem() == itemstack1.getItem() ? true
				: super.getIsRepairable(itemstack, itemstack1);
	}

	public Multimap<String, AttributeModifier> getItemAttributeModifiers() {
		Multimap multimap = super.getItemAttributeModifiers();
		multimap.put(SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName(),
				new AttributeModifier(itemModifierUUID, "Tool modifier", (double) this.damageVsEntity, 0));
		return multimap;
	}
}
