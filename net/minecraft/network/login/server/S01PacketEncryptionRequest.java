package net.minecraft.network.login.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S01PacketEncryptionRequest implements Packet<INetHandlerLoginClient> {
	private String hashedServerId;
	// private PublicKey publicKey;
	private byte[] verifyToken;

	public S01PacketEncryptionRequest() {
	}

//	public S01PacketEncryptionRequest(String serverId, PublicKey key, byte[] verifyToken) {
//		this.hashedServerId = serverId;
//		this.publicKey = key;
//		this.verifyToken = verifyToken;
//	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.hashedServerId = parPacketBuffer.readStringFromBuffer(20);
		// this.publicKey =
		// CryptManager.decodePublicKey(parPacketBuffer.readByteArray());
		parPacketBuffer.readByteArray(1024); // skip
		this.verifyToken = parPacketBuffer.readByteArray(1024);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
//		parPacketBuffer.writeString(this.hashedServerId);
//		parPacketBuffer.writeByteArray(this.publicKey.getEncoded());
//		parPacketBuffer.writeByteArray(this.verifyToken);
	}

	public void processPacket(INetHandlerLoginClient inethandlerloginclient) {
		inethandlerloginclient.handleEncryptionRequest(this);
	}

	public String getServerId() {
		return this.hashedServerId;
	}

//	public PublicKey getPublicKey() {
//		return this.publicKey;
//	}

	public byte[] getVerifyToken() {
		return this.verifyToken;
	}
}
