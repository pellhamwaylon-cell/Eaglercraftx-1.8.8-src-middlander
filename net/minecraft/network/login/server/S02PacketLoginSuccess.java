package net.minecraft.network.login.server;

import java.io.IOException;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;

import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S02PacketLoginSuccess implements Packet<INetHandlerLoginClient> {
	private GameProfile profile;
	private int selectedProtocol = 3;

	public S02PacketLoginSuccess() {
	}

	public S02PacketLoginSuccess(GameProfile profileIn, int selectedProtocol) {
		this.profile = profileIn;
		this.selectedProtocol = selectedProtocol;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		String s = parPacketBuffer.readStringFromBuffer(36);
		String s1 = parPacketBuffer.readStringFromBuffer(16);
		selectedProtocol = parPacketBuffer.readableBytes() > 0 ? parPacketBuffer.readShort() : 3;
		EaglercraftUUID uuid = EaglercraftUUID.fromString(s);
		this.profile = new GameProfile(uuid, s1);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		EaglercraftUUID uuid = this.profile.getId();
		parPacketBuffer.writeString(uuid == null ? "" : uuid.toString());
		parPacketBuffer.writeString(this.profile.getName());
		if (selectedProtocol != 3) {
			parPacketBuffer.writeShort(selectedProtocol);
		}
	}

	public void processPacket(INetHandlerLoginClient inethandlerloginclient) {
		inethandlerloginclient.handleLoginSuccess(this);
	}

	public GameProfile getProfile() {
		return this.profile;
	}

	public int getSelectedProtocol() {
		return selectedProtocol;
	}
}
