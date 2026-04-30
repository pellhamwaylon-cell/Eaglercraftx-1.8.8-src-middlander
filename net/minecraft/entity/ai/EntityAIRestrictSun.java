package net.minecraft.entity.ai;

import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathNavigateGround;

public class EntityAIRestrictSun extends EntityAIBase {
	private EntityCreature theEntity;

	public EntityAIRestrictSun(EntityCreature parEntityCreature) {
		this.theEntity = parEntityCreature;
	}

	public boolean shouldExecute() {
		return this.theEntity.worldObj.isDaytime();
	}

	public void startExecuting() {
		((PathNavigateGround) this.theEntity.getNavigator()).setAvoidSun(true);
	}

	public void resetTask() {
		((PathNavigateGround) this.theEntity.getNavigator()).setAvoidSun(false);
	}
}
