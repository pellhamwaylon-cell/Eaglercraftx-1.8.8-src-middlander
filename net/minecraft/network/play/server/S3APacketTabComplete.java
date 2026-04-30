package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S3APacketTabComplete implements Packet<INetHandlerPlayClient> {
	private String[] matches;

	public S3APacketTabComplete() {
	}

	public S3APacketTabComplete(String[] matchesIn) {
		this.matches = matchesIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.matches = new String[parPacketBuffer.readVarIntFromBuffer()];

		for (int i = 0; i < this.matches.length; ++i) {
			this.matches[i] = parPacketBuffer.readStringFromBuffer(32767);
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.matches.length);

		for (int i = 0; i < this.matches.length; ++i) {
			parPacketBuffer.writeString(this.matches[i]);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleTabComplete(this);
	}

	public String[] func_149630_c() {
		return this.matches;
	}
}
