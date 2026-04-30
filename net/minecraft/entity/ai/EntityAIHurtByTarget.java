package net.minecraft.entity.ai;

import java.util.List;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;

public class EntityAIHurtByTarget extends EntityAITarget {
	private boolean entityCallsForHelp;
	private int revengeTimerOld;
	private final Class[] targetClasses;

	public EntityAIHurtByTarget(EntityCreature creatureIn, boolean entityCallsForHelpIn, Class... targetClassesIn) {
		super(creatureIn, false);
		this.entityCallsForHelp = entityCallsForHelpIn;
		this.targetClasses = targetClassesIn;
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		int i = this.taskOwner.getRevengeTimer();
		return i != this.revengeTimerOld && this.isSuitableTarget(this.taskOwner.getAITarget(), false);
	}

	public void startExecuting() {
		this.taskOwner.setAttackTarget(this.taskOwner.getAITarget());
		this.revengeTimerOld = this.taskOwner.getRevengeTimer();
		if (this.entityCallsForHelp) {
			double d0 = this.getTargetDistance();

			List<EntityCreature> lst = this.taskOwner.worldObj.getEntitiesWithinAABB(this.taskOwner.getClass(),
					(new AxisAlignedBB(this.taskOwner.posX, this.taskOwner.posY, this.taskOwner.posZ,
							this.taskOwner.posX + 1.0D, this.taskOwner.posY + 1.0D, this.taskOwner.posZ + 1.0D))
									.expand(d0, 10.0D, d0));
			for (int i = 0, l = lst.size(); i < l; ++i) {
				EntityCreature entitycreature = lst.get(i);
				if (this.taskOwner != entitycreature && entitycreature.getAttackTarget() == null
						&& !entitycreature.isOnSameTeam(this.taskOwner.getAITarget())) {
					boolean flag = false;

					for (int j = 0; j < this.targetClasses.length; ++j) {
						if (entitycreature.getClass() == this.targetClasses[j]) {
							flag = true;
							break;
						}
					}

					if (!flag) {
						this.setEntityAttackTarget(entitycreature, this.taskOwner.getAITarget());
					}
				}
			}
		}

		super.startExecuting();
	}

	protected void setEntityAttackTarget(EntityCreature creatureIn, EntityLivingBase entityLivingBaseIn) {
		creatureIn.setAttackTarget(entityLivingBaseIn);
	}
}
