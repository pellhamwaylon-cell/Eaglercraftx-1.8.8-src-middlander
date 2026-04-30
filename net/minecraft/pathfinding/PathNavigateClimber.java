package net.minecraft.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PathNavigateClimber extends PathNavigateGround {
	private BlockPos targetPosition;

	public PathNavigateClimber(EntityLiving entityLivingIn, World worldIn) {
		super(entityLivingIn, worldIn);
	}

	public PathEntity getPathToPos(BlockPos blockpos) {
		this.targetPosition = blockpos;
		return super.getPathToPos(blockpos);
	}

	public PathEntity getPathToEntityLiving(Entity entity) {
		this.targetPosition = new BlockPos(entity);
		return super.getPathToEntityLiving(entity);
	}

	public boolean tryMoveToEntityLiving(Entity entity, double d0) {
		PathEntity pathentity = this.getPathToEntityLiving(entity);
		if (pathentity != null) {
			return this.setPath(pathentity, d0);
		} else {
			this.targetPosition = new BlockPos(entity);
			this.speed = d0;
			return true;
		}
	}

	public void onUpdateNavigation() {
		if (!this.noPath()) {
			super.onUpdateNavigation();
		} else {
			if (this.targetPosition != null) {
				double d0 = (double) (this.theEntity.width * this.theEntity.width);
				if (this.theEntity.getDistanceSqToCenter(this.targetPosition) >= d0
						&& (this.theEntity.posY <= (double) this.targetPosition.getY()
								|| this.theEntity.getDistanceSqToCenter(new BlockPos(this.targetPosition.getX(),
										MathHelper.floor_double(this.theEntity.posY),
										this.targetPosition.getZ())) >= d0)) {
					this.theEntity.getMoveHelper().setMoveTo((double) this.targetPosition.getX(),
							(double) this.targetPosition.getY(), (double) this.targetPosition.getZ(), this.speed);
				} else {
					this.targetPosition = null;
				}
			}

		}
	}
}
