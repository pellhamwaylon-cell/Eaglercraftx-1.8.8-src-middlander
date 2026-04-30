package net.minecraft.network.login.client;

import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import java.io.IOException;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C00PacketLoginStart implements Packet<INetHandlerLoginServer> {
	private GameProfile profile;
	private byte[] skin;
	private byte[] cape;
	private byte[] protocols;
	private EaglercraftUUID brandUUID;

	public C00PacketLoginStart() {
	}

	public C00PacketLoginStart(GameProfile profileIn, byte[] skin, byte[] cape, byte[] protocols,
			EaglercraftUUID brandUUID) {
		this.profile = profileIn;
		this.skin = skin;
		this.cape = cape;
		this.protocols = protocols;
		this.brandUUID = brandUUID;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.profile = new GameProfile((EaglercraftUUID) null, parPacketBuffer.readStringFromBuffer(16));
		this.skin = parPacketBuffer.readByteArray(32768);
		this.cape = parPacketBuffer.readableBytes() > 0 ? parPacketBuffer.readByteArray(32768) : null;
		this.protocols = parPacketBuffer.readableBytes() > 0 ? parPacketBuffer.readByteArray(256) : null;
		this.brandUUID = parPacketBuffer.readableBytes() > 0 ? parPacketBuffer.readUuid() : null;
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.profile.getName());
		parPacketBuffer.writeByteArray(this.skin);
		parPacketBuffer.writeByteArray(this.cape);
		parPacketBuffer.writeByteArray(this.protocols);
		parPacketBuffer.writeUuid(brandUUID);
	}

	public void processPacket(INetHandlerLoginServer inethandlerloginserver) {
		inethandlerloginserver.processLoginStart(this);
	}

	public GameProfile getProfile() {
		return this.profile;
	}

	public byte[] getSkin() {
		return this.skin;
	}

	public byte[] getCape() {
		return this.cape;
	}

	public byte[] getProtocols() {
		return this.protocols;
	}

	public EaglercraftUUID getBrandUUID() {
		return this.brandUUID;
	}
}
