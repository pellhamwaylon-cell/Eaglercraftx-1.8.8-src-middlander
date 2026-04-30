package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0FPacketConfirmTransaction implements Packet<INetHandlerPlayServer> {
	private int windowId;
	private short uid;
	private boolean accepted;

	public C0FPacketConfirmTransaction() {
	}

	public C0FPacketConfirmTransaction(int windowId, short uid, boolean accepted) {
		this.windowId = windowId;
		this.uid = uid;
		this.accepted = accepted;
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processConfirmTransaction(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readByte();
		this.uid = parPacketBuffer.readShort();
		this.accepted = parPacketBuffer.readByte() != 0;
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.uid);
		parPacketBuffer.writeByte(this.accepted ? 1 : 0);
	}

	public int getWindowId() {
		return this.windowId;
	}

	public short getUid() {
		return this.uid;
	}
}
