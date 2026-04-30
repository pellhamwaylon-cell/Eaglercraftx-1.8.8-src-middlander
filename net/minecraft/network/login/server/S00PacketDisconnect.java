package net.minecraft.network.login.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.util.IChatComponent;

public class S00PacketDisconnect implements Packet<INetHandlerLoginClient> {
	private IChatComponent reason;

	public S00PacketDisconnect() {
	}

	public S00PacketDisconnect(IChatComponent reasonIn) {
		this.reason = reasonIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.reason = parPacketBuffer.readChatComponent();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeChatComponent(this.reason);
	}

	public void processPacket(INetHandlerLoginClient inethandlerloginclient) {
		inethandlerloginclient.handleDisconnect(this);
	}

	public IChatComponent func_149603_c() {
		return this.reason;
	}
}
