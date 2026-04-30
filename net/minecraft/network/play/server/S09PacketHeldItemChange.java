package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S09PacketHeldItemChange implements Packet<INetHandlerPlayClient> {
	private int heldItemHotbarIndex;

	public S09PacketHeldItemChange() {
	}

	public S09PacketHeldItemChange(int hotbarIndexIn) {
		this.heldItemHotbarIndex = hotbarIndexIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.heldItemHotbarIndex = parPacketBuffer.readByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.heldItemHotbarIndex);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleHeldItemChange(this);
	}

	public int getHeldItemHotbarIndex() {
		return this.heldItemHotbarIndex;
	}
}
