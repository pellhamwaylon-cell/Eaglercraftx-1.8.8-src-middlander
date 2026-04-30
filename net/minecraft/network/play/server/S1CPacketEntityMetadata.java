package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;

import net.minecraft.entity.DataWatcher;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1CPacketEntityMetadata implements Packet<INetHandlerPlayClient> {
	private int entityId;
	private List<DataWatcher.WatchableObject> field_149378_b;

	public S1CPacketEntityMetadata() {
	}

	public S1CPacketEntityMetadata(int entityIdIn, DataWatcher parDataWatcher, boolean parFlag) {
		this.entityId = entityIdIn;
		if (parFlag) {
			this.field_149378_b = parDataWatcher.getAllWatched();
		} else {
			this.field_149378_b = parDataWatcher.getChanged();
		}

	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
		this.field_149378_b = DataWatcher.readWatchedListFromPacketBuffer(parPacketBuffer);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
		DataWatcher.writeWatchedListToPacketBuffer(this.field_149378_b, parPacketBuffer);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityMetadata(this);
	}

	public List<DataWatcher.WatchableObject> func_149376_c() {
		return this.field_149378_b;
	}

	public int getEntityId() {
		return this.entityId;
	}
}
