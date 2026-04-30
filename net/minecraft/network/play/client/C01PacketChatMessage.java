package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C01PacketChatMessage implements Packet<INetHandlerPlayServer> {
	private String message;

	public C01PacketChatMessage() {
	}

	public C01PacketChatMessage(String messageIn) {
		if (messageIn.length() > 100) {
			messageIn = messageIn.substring(0, 100);
		}

		this.message = messageIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.message = parPacketBuffer.readStringFromBuffer(100);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.message);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processChatMessage(this);
	}

	public String getMessage() {
		return this.message;
	}
}
