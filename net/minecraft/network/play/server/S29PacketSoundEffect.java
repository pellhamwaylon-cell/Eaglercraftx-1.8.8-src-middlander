package net.minecraft.network.play.server;

import java.io.IOException;

import org.apache.commons.lang3.Validate;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S29PacketSoundEffect implements Packet<INetHandlerPlayClient> {
	private String soundName;
	private int posX;
	private int posY = Integer.MAX_VALUE;
	private int posZ;
	private float soundVolume;
	private int soundPitch;

	public S29PacketSoundEffect() {
	}

	public S29PacketSoundEffect(String soundNameIn, double soundX, double soundY, double soundZ, float volume,
			float pitch) {
		Validate.notNull(soundNameIn, "name", new Object[0]);
		this.soundName = soundNameIn;
		this.posX = (int) (soundX * 8.0D);
		this.posY = (int) (soundY * 8.0D);
		this.posZ = (int) (soundZ * 8.0D);
		this.soundVolume = volume;
		this.soundPitch = (int) (pitch * 63.0F);
		pitch = MathHelper.clamp_float(pitch, 0.0F, 255.0F);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.soundName = parPacketBuffer.readStringFromBuffer(256);
		this.posX = parPacketBuffer.readInt();
		this.posY = parPacketBuffer.readInt();
		this.posZ = parPacketBuffer.readInt();
		this.soundVolume = parPacketBuffer.readFloat();
		this.soundPitch = parPacketBuffer.readUnsignedByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.soundName);
		parPacketBuffer.writeInt(this.posX);
		parPacketBuffer.writeInt(this.posY);
		parPacketBuffer.writeInt(this.posZ);
		parPacketBuffer.writeFloat(this.soundVolume);
		parPacketBuffer.writeByte(this.soundPitch);
	}

	public String getSoundName() {
		return this.soundName;
	}

	public double getX() {
		return (double) ((float) this.posX / 8.0F);
	}

	public double getY() {
		return (double) ((float) this.posY / 8.0F);
	}

	public double getZ() {
		return (double) ((float) this.posZ / 8.0F);
	}

	public float getVolume() {
		return this.soundVolume;
	}

	public float getPitch() {
		return (float) this.soundPitch / 63.0F;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleSoundEffect(this);
	}
}
