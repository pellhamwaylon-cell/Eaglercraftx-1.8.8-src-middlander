package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S31PacketWindowProperty implements Packet<INetHandlerPlayClient> {
	private int windowId;
	private int varIndex;
	private int varValue;

	public S31PacketWindowProperty() {
	}

	public S31PacketWindowProperty(int windowIdIn, int varIndexIn, int varValueIn) {
		this.windowId = windowIdIn;
		this.varIndex = varIndexIn;
		this.varValue = varValueIn;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleWindowProperty(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readUnsignedByte();
		this.varIndex = parPacketBuffer.readShort();
		this.varValue = parPacketBuffer.readShort();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.varIndex);
		parPacketBuffer.writeShort(this.varValue);
	}

	public int getWindowId() {
		return this.windowId;
	}

	public int getVarIndex() {
		return this.varIndex;
	}

	public int getVarValue() {
		return this.varValue;
	}
}
