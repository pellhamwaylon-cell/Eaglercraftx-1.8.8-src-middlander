package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S13PacketDestroyEntities implements Packet<INetHandlerPlayClient> {
	private int[] entityIDs;

	public S13PacketDestroyEntities() {
	}

	public S13PacketDestroyEntities(int... entityIDsIn) {
		this.entityIDs = entityIDsIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityIDs = new int[parPacketBuffer.readVarIntFromBuffer()];

		for (int i = 0; i < this.entityIDs.length; ++i) {
			this.entityIDs[i] = parPacketBuffer.readVarIntFromBuffer();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityIDs.length);

		for (int i = 0; i < this.entityIDs.length; ++i) {
			parPacketBuffer.writeVarIntToBuffer(this.entityIDs[i]);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleDestroyEntities(this);
	}

	public int[] getEntityIDs() {
		return this.entityIDs;
	}
}
