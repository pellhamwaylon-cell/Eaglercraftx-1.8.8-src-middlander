package net.minecraft.block;

import net.minecraft.util.BlockPos;

public class BlockEventData {
	private BlockPos position;
	private Block blockType;
	private int eventID;
	private int eventParameter;

	public BlockEventData(BlockPos pos, Block blockType, int eventId, int parInt1) {
		this.position = pos;
		this.eventID = eventId;
		this.eventParameter = parInt1;
		this.blockType = blockType;
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public int getEventID() {
		return this.eventID;
	}

	public int getEventParameter() {
		return this.eventParameter;
	}

	public Block getBlock() {
		return this.blockType;
	}

	public boolean equals(Object parObject) {
		if (!(parObject instanceof BlockEventData)) {
			return false;
		} else {
			BlockEventData blockeventdata = (BlockEventData) parObject;
			return this.position.equals(blockeventdata.position) && this.eventID == blockeventdata.eventID
					&& this.eventParameter == blockeventdata.eventParameter
					&& this.blockType == blockeventdata.blockType;
		}
	}

	public String toString() {
		return "TE(" + this.position + ")," + this.eventID + "," + this.eventParameter + "," + this.blockType;
	}
}
