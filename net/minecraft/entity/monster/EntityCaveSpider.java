package net.minecraft.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityCaveSpider extends EntitySpider {
	public EntityCaveSpider(World worldIn) {
		super(worldIn);
		this.setSize(0.7F, 0.5F);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
	}

	public boolean attackEntityAsMob(Entity entity) {
		if (super.attackEntityAsMob(entity)) {
			if (entity instanceof EntityLivingBase) {
				byte b0 = 0;
				if (this.worldObj.getDifficulty() == EnumDifficulty.NORMAL) {
					b0 = 7;
				} else if (this.worldObj.getDifficulty() == EnumDifficulty.HARD) {
					b0 = 15;
				}

				if (b0 > 0) {
					((EntityLivingBase) entity).addPotionEffect(new PotionEffect(Potion.poison.id, b0 * 20, 0));
				}
			}

			return true;
		} else {
			return false;
		}
	}

	public IEntityLivingData onInitialSpawn(DifficultyInstance var1, IEntityLivingData ientitylivingdata) {
		return ientitylivingdata;
	}

	public float getEyeHeight() {
		return 0.45F;
	}
}
