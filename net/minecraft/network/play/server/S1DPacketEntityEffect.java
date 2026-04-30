package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class S1DPacketEntityEffect implements Packet<INetHandlerPlayClient> {
	private int entityId;
	private byte effectId;
	private byte amplifier;
	private int duration;
	private byte hideParticles;

	public S1DPacketEntityEffect() {
	}

	public S1DPacketEntityEffect(int entityIdIn, PotionEffect effect) {
		this.entityId = entityIdIn;
		this.effectId = (byte) (effect.getPotionID() & 255);
		this.amplifier = (byte) (effect.getAmplifier() & 255);
		if (effect.getDuration() > 32767) {
			this.duration = 32767;
		} else {
			this.duration = effect.getDuration();
		}

		this.hideParticles = (byte) (effect.getIsShowParticles() ? 1 : 0);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
		this.effectId = parPacketBuffer.readByte();
		this.amplifier = parPacketBuffer.readByte();
		this.duration = parPacketBuffer.readVarIntFromBuffer();
		this.hideParticles = parPacketBuffer.readByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
		parPacketBuffer.writeByte(this.effectId);
		parPacketBuffer.writeByte(this.amplifier);
		parPacketBuffer.writeVarIntToBuffer(this.duration);
		parPacketBuffer.writeByte(this.hideParticles);
	}

	public boolean func_149429_c() {
		return this.duration == 32767;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityEffect(this);
	}

	public int getEntityId() {
		return this.entityId;
	}

	public byte getEffectId() {
		return this.effectId;
	}

	public byte getAmplifier() {
		return this.amplifier;
	}

	public int getDuration() {
		return this.duration;
	}

	public boolean func_179707_f() {
		return this.hideParticles != 0;
	}
}
