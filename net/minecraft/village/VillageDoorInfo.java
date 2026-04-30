package net.minecraft.village;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class VillageDoorInfo {
	private final BlockPos doorBlockPos;
	private final BlockPos insideBlock;
	private final EnumFacing insideDirection;
	private int lastActivityTimestamp;
	private boolean isDetachedFromVillageFlag;
	private int doorOpeningRestrictionCounter;

	public VillageDoorInfo(BlockPos parBlockPos, int parInt1, int parInt2, int parInt3) {
		this(parBlockPos, getFaceDirection(parInt1, parInt2), parInt3);
	}

	private static EnumFacing getFaceDirection(int deltaX, int deltaZ) {
		return deltaX < 0 ? EnumFacing.WEST
				: (deltaX > 0 ? EnumFacing.EAST : (deltaZ < 0 ? EnumFacing.NORTH : EnumFacing.SOUTH));
	}

	public VillageDoorInfo(BlockPos parBlockPos, EnumFacing parEnumFacing, int parInt1) {
		this.doorBlockPos = parBlockPos;
		this.insideDirection = parEnumFacing;
		this.insideBlock = parBlockPos.offset(parEnumFacing, 2);
		this.lastActivityTimestamp = parInt1;
	}

	public int getDistanceSquared(int parInt1, int parInt2, int parInt3) {
		return (int) this.doorBlockPos.distanceSq((double) parInt1, (double) parInt2, (double) parInt3);
	}

	public int getDistanceToDoorBlockSq(BlockPos parBlockPos) {
		return (int) parBlockPos.distanceSq(this.getDoorBlockPos());
	}

	public int getDistanceToInsideBlockSq(BlockPos parBlockPos) {
		return (int) this.insideBlock.distanceSq(parBlockPos);
	}

	public boolean func_179850_c(BlockPos parBlockPos) {
		int i = parBlockPos.getX() - this.doorBlockPos.getX();
		int j = parBlockPos.getZ() - this.doorBlockPos.getY();
		return i * this.insideDirection.getFrontOffsetX() + j * this.insideDirection.getFrontOffsetZ() >= 0;
	}

	public void resetDoorOpeningRestrictionCounter() {
		this.doorOpeningRestrictionCounter = 0;
	}

	public void incrementDoorOpeningRestrictionCounter() {
		++this.doorOpeningRestrictionCounter;
	}

	public int getDoorOpeningRestrictionCounter() {
		return this.doorOpeningRestrictionCounter;
	}

	public BlockPos getDoorBlockPos() {
		return this.doorBlockPos;
	}

	public BlockPos getInsideBlockPos() {
		return this.insideBlock;
	}

	public int getInsideOffsetX() {
		return this.insideDirection.getFrontOffsetX() * 2;
	}

	public int getInsideOffsetZ() {
		return this.insideDirection.getFrontOffsetZ() * 2;
	}

	public int getInsidePosY() {
		return this.lastActivityTimestamp;
	}

	public void func_179849_a(int parInt1) {
		this.lastActivityTimestamp = parInt1;
	}

	public boolean getIsDetachedFromVillageFlag() {
		return this.isDetachedFromVillageFlag;
	}

	public void setIsDetachedFromVillageFlag(boolean parFlag) {
		this.isDetachedFromVillageFlag = parFlag;
	}
}
