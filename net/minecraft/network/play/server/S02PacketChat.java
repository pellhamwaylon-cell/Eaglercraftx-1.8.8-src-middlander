package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S02PacketChat implements Packet<INetHandlerPlayClient> {
	private IChatComponent chatComponent;
	private byte type;

	public S02PacketChat() {
	}

	public S02PacketChat(IChatComponent component) {
		this(component, (byte) 1);
	}

	public S02PacketChat(IChatComponent message, byte typeIn) {
		this.chatComponent = message;
		this.type = typeIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.chatComponent = parPacketBuffer.readChatComponent();
		this.type = parPacketBuffer.readByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeChatComponent(this.chatComponent);
		parPacketBuffer.writeByte(this.type);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleChat(this);
	}

	public IChatComponent getChatComponent() {
		return this.chatComponent;
	}

	public boolean isChat() {
		return this.type == 1 || this.type == 2;
	}

	public byte getType() {
		return this.type;
	}
}
