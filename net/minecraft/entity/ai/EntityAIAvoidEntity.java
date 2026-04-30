package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.Vec3;

public class EntityAIAvoidEntity<T extends Entity> extends EntityAIBase {
	private final Predicate<Entity> canBeSeenSelector;
	protected EntityCreature theEntity;
	private double farSpeed;
	private double nearSpeed;
	protected T closestLivingEntity;
	private float avoidDistance;
	private PathEntity entityPathEntity;
	private PathNavigate entityPathNavigate;
	private Class<T> field_181064_i;
	private Predicate<? super T> avoidTargetSelector;

	public EntityAIAvoidEntity(EntityCreature parEntityCreature, Class<T> parClass1, float parFloat1, double parDouble1,
			double parDouble2) {
		this(parEntityCreature, parClass1, Predicates.alwaysTrue(), parFloat1, parDouble1, parDouble2);
	}

	public EntityAIAvoidEntity(EntityCreature parEntityCreature, Class<T> parClass1, Predicate<? super T> parPredicate,
			float parFloat1, double parDouble1, double parDouble2) {
		this.canBeSeenSelector = new Predicate<Entity>() {
			public boolean apply(Entity entity) {
				return entity.isEntityAlive() && EntityAIAvoidEntity.this.theEntity.getEntitySenses().canSee(entity);
			}
		};
		this.theEntity = parEntityCreature;
		this.field_181064_i = parClass1;
		this.avoidTargetSelector = parPredicate;
		this.avoidDistance = parFloat1;
		this.farSpeed = parDouble1;
		this.nearSpeed = parDouble2;
		this.entityPathNavigate = parEntityCreature.getNavigator();
		this.setMutexBits(1);
	}

	public boolean shouldExecute() {
		List list = this.theEntity.worldObj.getEntitiesWithinAABB(this.field_181064_i,
				this.theEntity.getEntityBoundingBox().expand((double) this.avoidDistance, 3.0D,
						(double) this.avoidDistance),
				Predicates.and(new Predicate[] { EntitySelectors.NOT_SPECTATING, this.canBeSeenSelector,
						this.avoidTargetSelector }));
		if (list.isEmpty()) {
			return false;
		} else {
			this.closestLivingEntity = (T) list.get(0);
			Vec3 vec3 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(this.theEntity, 16, 7, new Vec3(
					this.closestLivingEntity.posX, this.closestLivingEntity.posY, this.closestLivingEntity.posZ));
			if (vec3 == null) {
				return false;
			} else if (this.closestLivingEntity.getDistanceSq(vec3.xCoord, vec3.yCoord,
					vec3.zCoord) < this.closestLivingEntity.getDistanceSqToEntity(this.theEntity)) {
				return false;
			} else {
				this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord);
				return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(vec3);
			}
		}
	}

	public boolean continueExecuting() {
		return !this.entityPathNavigate.noPath();
	}

	public void startExecuting() {
		this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
	}

	public void resetTask() {
		this.closestLivingEntity = null;
	}

	public void updateTask() {
		if (this.theEntity.getDistanceSqToEntity(this.closestLivingEntity) < 49.0D) {
			this.theEntity.getNavigator().setSpeed(this.nearSpeed);
		} else {
			this.theEntity.getNavigator().setSpeed(this.farSpeed);
		}

	}
}
