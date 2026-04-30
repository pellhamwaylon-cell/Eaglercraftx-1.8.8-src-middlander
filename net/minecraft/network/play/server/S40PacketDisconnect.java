package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S40PacketDisconnect implements Packet<INetHandlerPlayClient> {
	private IChatComponent reason;

	public S40PacketDisconnect() {
	}

	public S40PacketDisconnect(IChatComponent reasonIn) {
		this.reason = reasonIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.reason = parPacketBuffer.readChatComponent();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeChatComponent(this.reason);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleDisconnect(this);
	}

	public IChatComponent getReason() {
		return this.reason;
	}
}
