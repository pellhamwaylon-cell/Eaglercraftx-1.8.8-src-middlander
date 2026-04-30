package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S00PacketKeepAlive implements Packet<INetHandlerPlayClient> {
	private int id;

	public S00PacketKeepAlive() {
	}

	public S00PacketKeepAlive(int idIn) {
		this.id = idIn;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleKeepAlive(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.id = parPacketBuffer.readVarIntFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.id);
	}

	public int func_149134_c() {
		return this.id;
	}
}
