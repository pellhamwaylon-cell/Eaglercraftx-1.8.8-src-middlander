package net.minecraft.network.login.client;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginServer;

public class C01PacketEncryptionResponse implements Packet<INetHandlerLoginServer> {
	private byte[] secretKeyEncrypted = new byte[0];
	private byte[] verifyTokenEncrypted = new byte[0];

	public C01PacketEncryptionResponse() {
	}

//	public C01PacketEncryptionResponse(SecretKey secretKey, PublicKey publicKey, byte[] verifyToken) {
//		this.secretKeyEncrypted = CryptManager.encryptData(publicKey, secretKey.getEncoded());
//		this.verifyTokenEncrypted = CryptManager.encryptData(publicKey, verifyToken);
//	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.secretKeyEncrypted = parPacketBuffer.readByteArray(1024);
		this.verifyTokenEncrypted = parPacketBuffer.readByteArray(1024);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByteArray(this.secretKeyEncrypted);
		parPacketBuffer.writeByteArray(this.verifyTokenEncrypted);
	}

	public void processPacket(INetHandlerLoginServer inethandlerloginserver) {
		inethandlerloginserver.processEncryptionResponse(this);
	}

//	public SecretKey getSecretKey(PrivateKey key) {
//		return CryptManager.decryptSharedKey(key, this.secretKeyEncrypted);
//	}

//	public byte[] getVerifyToken(PrivateKey key) {
//		return key == null ? this.verifyTokenEncrypted : CryptManager.decryptData(key, this.verifyTokenEncrypted);
//	}
}
