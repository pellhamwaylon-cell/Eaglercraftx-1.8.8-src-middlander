package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S47PacketPlayerListHeaderFooter implements Packet<INetHandlerPlayClient> {
	private IChatComponent header;
	private IChatComponent footer;

	public S47PacketPlayerListHeaderFooter() {
	}

	public S47PacketPlayerListHeaderFooter(IChatComponent headerIn) {
		this.header = headerIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.header = parPacketBuffer.readChatComponent();
		this.footer = parPacketBuffer.readChatComponent();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeChatComponent(this.header);
		parPacketBuffer.writeChatComponent(this.footer);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handlePlayerListHeaderFooter(this);
	}

	public IChatComponent getHeader() {
		return this.header;
	}

	public IChatComponent getFooter() {
		return this.footer;
	}
}
