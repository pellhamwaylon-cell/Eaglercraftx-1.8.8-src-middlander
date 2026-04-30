package net.minecraft.pathfinding;

import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.pathfinder.SwimNodeProcessor;

public class PathNavigateSwimmer extends PathNavigate {
	public PathNavigateSwimmer(EntityLiving entitylivingIn, World worldIn) {
		super(entitylivingIn, worldIn);
	}

	protected PathFinder getPathFinder() {
		return new PathFinder(new SwimNodeProcessor());
	}

	protected boolean canNavigate() {
		return this.isInLiquid();
	}

	protected Vec3 getEntityPosition() {
		return new Vec3(this.theEntity.posX, this.theEntity.posY + (double) this.theEntity.height * 0.5D,
				this.theEntity.posZ);
	}

	protected void pathFollow() {
		Vec3 vec3 = this.getEntityPosition();
		float f = this.theEntity.width * this.theEntity.width;
		byte b0 = 6;
		if (vec3.squareDistanceTo(this.currentPath.getVectorFromIndex(this.theEntity,
				this.currentPath.getCurrentPathIndex())) < (double) f) {
			this.currentPath.incrementPathIndex();
		}

		for (int i = Math.min(this.currentPath.getCurrentPathIndex() + b0,
				this.currentPath.getCurrentPathLength() - 1); i > this.currentPath.getCurrentPathIndex(); --i) {
			Vec3 vec31 = this.currentPath.getVectorFromIndex(this.theEntity, i);
			if (vec31.squareDistanceTo(vec3) <= 36.0D && this.isDirectPathBetweenPoints(vec3, vec31, 0, 0, 0)) {
				this.currentPath.setCurrentPathIndex(i);
				break;
			}
		}

		this.checkForStuck(vec3);
	}

	protected void removeSunnyPath() {
		super.removeSunnyPath();
	}

	protected boolean isDirectPathBetweenPoints(Vec3 vec3, Vec3 vec31, int var3, int var4, int var5) {
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks(vec3,
				new Vec3(vec31.xCoord, vec31.yCoord + (double) this.theEntity.height * 0.5D, vec31.zCoord), false, true,
				false);
		return movingobjectposition == null
				|| movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.MISS;
	}
}
