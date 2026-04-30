package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;

public class EntityDamageSourceIndirect extends EntityDamageSource {
	private Entity indirectEntity;

	public EntityDamageSourceIndirect(String parString1, Entity parEntity, Entity indirectEntityIn) {
		super(parString1, parEntity);
		this.indirectEntity = indirectEntityIn;
	}

	public Entity getSourceOfDamage() {
		return this.damageSourceEntity;
	}

	public Entity getEntity() {
		return this.indirectEntity;
	}

	public IChatComponent getDeathMessage(EntityLivingBase entitylivingbase) {
		IChatComponent ichatcomponent = this.indirectEntity == null ? this.damageSourceEntity.getDisplayName()
				: this.indirectEntity.getDisplayName();
		ItemStack itemstack = this.indirectEntity instanceof EntityLivingBase
				? ((EntityLivingBase) this.indirectEntity).getHeldItem()
				: null;
		String s = "death.attack." + this.damageType;
		String s1 = s + ".item";
		return itemstack != null && itemstack.hasDisplayName() && StatCollector.canTranslate(s1)
				? new ChatComponentTranslation(s1,
						new Object[] { entitylivingbase.getDisplayName(), ichatcomponent,
								itemstack.getChatComponent() })
				: new ChatComponentTranslation(s, new Object[] { entitylivingbase.getDisplayName(), ichatcomponent });
	}
}
