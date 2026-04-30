package net.minecraft.world;

import net.minecraft.block.Block;
import net.minecraft.util.BlockPos;

public class NextTickListEntry implements Comparable<NextTickListEntry> {
	private static long nextTickEntryID;
	private final Block block;
	public final BlockPos position;
	public long scheduledTime;
	public int priority;
	private long tickEntryID;

	public NextTickListEntry(BlockPos parBlockPos, Block parBlock) {
		this.tickEntryID = (long) (nextTickEntryID++);
		this.position = parBlockPos;
		this.block = parBlock;
	}

	public boolean equals(Object object) {
		if (!(object instanceof NextTickListEntry)) {
			return false;
		} else {
			NextTickListEntry nextticklistentry = (NextTickListEntry) object;
			return this.position.equals(nextticklistentry.position)
					&& Block.isEqualTo(this.block, nextticklistentry.block);
		}
	}

	public int hashCode() {
		return this.position.hashCode();
	}

	public NextTickListEntry setScheduledTime(long parLong1) {
		this.scheduledTime = parLong1;
		return this;
	}

	public void setPriority(int parInt1) {
		this.priority = parInt1;
	}

	public int compareTo(NextTickListEntry parNextTickListEntry) {
		return this.scheduledTime < parNextTickListEntry.scheduledTime ? -1
				: (this.scheduledTime > parNextTickListEntry.scheduledTime ? 1
						: (this.priority != parNextTickListEntry.priority
								? this.priority - parNextTickListEntry.priority
								: (this.tickEntryID < parNextTickListEntry.tickEntryID ? -1
										: (this.tickEntryID > parNextTickListEntry.tickEntryID ? 1 : 0))));
	}

	public String toString() {
		return Block.getIdFromBlock(this.block) + ": " + this.position + ", " + this.scheduledTime + ", "
				+ this.priority + ", " + this.tickEntryID;
	}

	public Block getBlock() {
		return this.block;
	}
}
