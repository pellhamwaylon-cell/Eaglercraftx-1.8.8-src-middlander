package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0APacketAnimation implements Packet<INetHandlerPlayServer> {
	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.handleAnimation(this);
	}
}
