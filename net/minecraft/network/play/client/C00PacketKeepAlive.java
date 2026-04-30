package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C00PacketKeepAlive implements Packet<INetHandlerPlayServer> {
	private int key;

	public C00PacketKeepAlive() {
	}

	public C00PacketKeepAlive(int key) {
		this.key = key;
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processKeepAlive(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.key = parPacketBuffer.readVarIntFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.key);
	}

	public int getKey() {
		return this.key;
	}
}
