package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1EPacketRemoveEntityEffect implements Packet<INetHandlerPlayClient> {
	private int entityId;
	private int effectId;

	public S1EPacketRemoveEntityEffect() {
	}

	public S1EPacketRemoveEntityEffect(int entityIdIn, PotionEffect effect) {
		this.entityId = entityIdIn;
		this.effectId = effect.getPotionID();
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
		this.effectId = parPacketBuffer.readUnsignedByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
		parPacketBuffer.writeByte(this.effectId);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleRemoveEntityEffect(this);
	}

	public int getEntityId() {
		return this.entityId;
	}

	public int getEffectId() {
		return this.effectId;
	}
}
