package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1FPacketSetExperience implements Packet<INetHandlerPlayClient> {
	private float field_149401_a;
	private int totalExperience;
	private int level;

	public S1FPacketSetExperience() {
	}

	public S1FPacketSetExperience(float parFloat1, int totalExperienceIn, int levelIn) {
		this.field_149401_a = parFloat1;
		this.totalExperience = totalExperienceIn;
		this.level = levelIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.field_149401_a = parPacketBuffer.readFloat();
		this.level = parPacketBuffer.readVarIntFromBuffer();
		this.totalExperience = parPacketBuffer.readVarIntFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeFloat(this.field_149401_a);
		parPacketBuffer.writeVarIntToBuffer(this.level);
		parPacketBuffer.writeVarIntToBuffer(this.totalExperience);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleSetExperience(this);
	}

	public float func_149397_c() {
		return this.field_149401_a;
	}

	public int getTotalExperience() {
		return this.totalExperience;
	}

	public int getLevel() {
		return this.level;
	}
}
