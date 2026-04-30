package net.minecraft.network.play.client;

import java.io.IOException;

import net.lax1dude.eaglercraft.v1_8.netty.ByteBuf;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C17PacketCustomPayload implements Packet<INetHandlerPlayServer> {
	private String channel;
	private PacketBuffer data;

	public C17PacketCustomPayload() {
	}

	public C17PacketCustomPayload(String channelIn, PacketBuffer dataIn) {
		this.channel = channelIn;
		this.data = dataIn;
		if (dataIn.writerIndex() > 32767) {
			throw new IllegalArgumentException("Payload may not be larger than 32767 bytes");
		}
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.channel = parPacketBuffer.readStringFromBuffer(20);
		int i = parPacketBuffer.readableBytes();
		if (i >= 0 && i <= 32767) {
			this.data = new PacketBuffer(parPacketBuffer.readBytes(i));
		} else {
			throw new IOException("Payload may not be larger than 32767 bytes");
		}
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.channel);
		parPacketBuffer.writeBytes((ByteBuf) this.data);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processVanilla250Packet(this);
	}

	public String getChannelName() {
		return this.channel;
	}

	public PacketBuffer getBufferData() {
		return this.data;
	}
}
