package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S06PacketUpdateHealth implements Packet<INetHandlerPlayClient> {
	private float health;
	private int foodLevel;
	private float saturationLevel;

	public S06PacketUpdateHealth() {
	}

	public S06PacketUpdateHealth(float healthIn, int foodLevelIn, float saturationIn) {
		this.health = healthIn;
		this.foodLevel = foodLevelIn;
		this.saturationLevel = saturationIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.health = parPacketBuffer.readFloat();
		this.foodLevel = parPacketBuffer.readVarIntFromBuffer();
		this.saturationLevel = parPacketBuffer.readFloat();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeFloat(this.health);
		parPacketBuffer.writeVarIntToBuffer(this.foodLevel);
		parPacketBuffer.writeFloat(this.saturationLevel);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleUpdateHealth(this);
	}

	public float getHealth() {
		return this.health;
	}

	public int getFoodLevel() {
		return this.foodLevel;
	}

	public float getSaturationLevel() {
		return this.saturationLevel;
	}
}
