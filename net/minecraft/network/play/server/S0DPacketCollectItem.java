package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S0DPacketCollectItem implements Packet<INetHandlerPlayClient> {
	private int collectedItemEntityId;
	private int entityId;

	public S0DPacketCollectItem() {
	}

	public S0DPacketCollectItem(int collectedItemEntityIdIn, int entityIdIn) {
		this.collectedItemEntityId = collectedItemEntityIdIn;
		this.entityId = entityIdIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.collectedItemEntityId = parPacketBuffer.readVarIntFromBuffer();
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.collectedItemEntityId);
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleCollectItem(this);
	}

	public int getCollectedItemEntityID() {
		return this.collectedItemEntityId;
	}

	public int getEntityID() {
		return this.entityId;
	}
}
