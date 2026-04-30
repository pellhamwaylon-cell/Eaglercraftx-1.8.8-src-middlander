package net.minecraft.network.handshake.client;

import java.io.IOException;

import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.handshake.INetHandlerHandshakeServer;

public class C00Handshake implements Packet<INetHandlerHandshakeServer> {
	private int protocolVersion;
	private String ip;
	private int port;
	private EnumConnectionState requestedState;

	public C00Handshake() {
	}

	public C00Handshake(int version, String ip, int port, EnumConnectionState requestedState) {
		this.protocolVersion = version;
		this.ip = ip;
		this.port = port;
		this.requestedState = requestedState;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.protocolVersion = parPacketBuffer.readVarIntFromBuffer();
		this.ip = parPacketBuffer.readStringFromBuffer(255);
		this.port = parPacketBuffer.readUnsignedShort();
		this.requestedState = EnumConnectionState.getById(parPacketBuffer.readVarIntFromBuffer());
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.protocolVersion);
		parPacketBuffer.writeString(this.ip);
		parPacketBuffer.writeShort(this.port);
		parPacketBuffer.writeVarIntToBuffer(this.requestedState.getId());
	}

	public void processPacket(INetHandlerHandshakeServer inethandlerhandshakeserver) {
		inethandlerhandshakeserver.processHandshake(this);
	}

	public EnumConnectionState getRequestedState() {
		return this.requestedState;
	}

	public int getProtocolVersion() {
		return this.protocolVersion;
	}
}
