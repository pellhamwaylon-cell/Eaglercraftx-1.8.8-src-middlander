package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C19PacketResourcePackStatus implements Packet<INetHandlerPlayServer> {
	private String hash;
	private C19PacketResourcePackStatus.Action status;

	public C19PacketResourcePackStatus() {
	}

	public C19PacketResourcePackStatus(String hashIn, C19PacketResourcePackStatus.Action statusIn) {
		if (hashIn.length() > 40) {
			hashIn = hashIn.substring(0, 40);
		}

		this.hash = hashIn;
		this.status = statusIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.hash = parPacketBuffer.readStringFromBuffer(40);
		this.status = (C19PacketResourcePackStatus.Action) parPacketBuffer
				.readEnumValue(C19PacketResourcePackStatus.Action.class);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.hash);
		parPacketBuffer.writeEnumValue(this.status);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.handleResourcePackStatus(this);
	}

	public static enum Action {
		SUCCESSFULLY_LOADED, DECLINED, FAILED_DOWNLOAD, ACCEPTED;
	}
}
