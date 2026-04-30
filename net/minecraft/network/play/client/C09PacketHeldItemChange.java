package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C09PacketHeldItemChange implements Packet<INetHandlerPlayServer> {
	private int slotId;

	public C09PacketHeldItemChange() {
	}

	public C09PacketHeldItemChange(int slotId) {
		this.slotId = slotId;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.slotId = parPacketBuffer.readShort();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeShort(this.slotId);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processHeldItemChange(this);
	}

	public int getSlotId() {
		return this.slotId;
	}
}
