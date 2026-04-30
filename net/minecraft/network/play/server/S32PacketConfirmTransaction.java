package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S32PacketConfirmTransaction implements Packet<INetHandlerPlayClient> {
	private int windowId;
	private short actionNumber;
	private boolean field_148893_c;

	public S32PacketConfirmTransaction() {
	}

	public S32PacketConfirmTransaction(int windowIdIn, short actionNumberIn, boolean parFlag) {
		this.windowId = windowIdIn;
		this.actionNumber = actionNumberIn;
		this.field_148893_c = parFlag;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleConfirmTransaction(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readUnsignedByte();
		this.actionNumber = parPacketBuffer.readShort();
		this.field_148893_c = parPacketBuffer.readBoolean();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.actionNumber);
		parPacketBuffer.writeBoolean(this.field_148893_c);
	}

	public int getWindowId() {
		return this.windowId;
	}

	public short getActionNumber() {
		return this.actionNumber;
	}

	public boolean func_148888_e() {
		return this.field_148893_c;
	}
}
