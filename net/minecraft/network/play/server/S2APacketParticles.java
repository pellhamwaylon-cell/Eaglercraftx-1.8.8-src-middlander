package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.EnumParticleTypes;

public class S2APacketParticles implements Packet<INetHandlerPlayClient> {
	private EnumParticleTypes particleType;
	private float xCoord;
	private float yCoord;
	private float zCoord;
	private float xOffset;
	private float yOffset;
	private float zOffset;
	private float particleSpeed;
	private int particleCount;
	private boolean longDistance;
	private int[] particleArguments;

	public S2APacketParticles() {
	}

	public S2APacketParticles(EnumParticleTypes particleTypeIn, boolean longDistanceIn, float x, float y, float z,
			float xOffsetIn, float yOffset, float zOffset, float particleSpeedIn, int particleCountIn,
			int... particleArgumentsIn) {
		this.particleType = particleTypeIn;
		this.longDistance = longDistanceIn;
		this.xCoord = x;
		this.yCoord = y;
		this.zCoord = z;
		this.xOffset = xOffsetIn;
		this.yOffset = yOffset;
		this.zOffset = zOffset;
		this.particleSpeed = particleSpeedIn;
		this.particleCount = particleCountIn;
		this.particleArguments = particleArgumentsIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.particleType = EnumParticleTypes.getParticleFromId(parPacketBuffer.readInt());
		if (this.particleType == null) {
			this.particleType = EnumParticleTypes.BARRIER;
		}

		this.longDistance = parPacketBuffer.readBoolean();
		this.xCoord = parPacketBuffer.readFloat();
		this.yCoord = parPacketBuffer.readFloat();
		this.zCoord = parPacketBuffer.readFloat();
		this.xOffset = parPacketBuffer.readFloat();
		this.yOffset = parPacketBuffer.readFloat();
		this.zOffset = parPacketBuffer.readFloat();
		this.particleSpeed = parPacketBuffer.readFloat();
		this.particleCount = parPacketBuffer.readInt();
		int i = this.particleType.getArgumentCount();
		this.particleArguments = new int[i];

		for (int j = 0; j < i; ++j) {
			this.particleArguments[j] = parPacketBuffer.readVarIntFromBuffer();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeInt(this.particleType.getParticleID());
		parPacketBuffer.writeBoolean(this.longDistance);
		parPacketBuffer.writeFloat(this.xCoord);
		parPacketBuffer.writeFloat(this.yCoord);
		parPacketBuffer.writeFloat(this.zCoord);
		parPacketBuffer.writeFloat(this.xOffset);
		parPacketBuffer.writeFloat(this.yOffset);
		parPacketBuffer.writeFloat(this.zOffset);
		parPacketBuffer.writeFloat(this.particleSpeed);
		parPacketBuffer.writeInt(this.particleCount);
		int i = this.particleType.getArgumentCount();

		for (int j = 0; j < i; ++j) {
			parPacketBuffer.writeVarIntToBuffer(this.particleArguments[j]);
		}

	}

	public EnumParticleTypes getParticleType() {
		return this.particleType;
	}

	public boolean isLongDistance() {
		return this.longDistance;
	}

	public double getXCoordinate() {
		return (double) this.xCoord;
	}

	public double getYCoordinate() {
		return (double) this.yCoord;
	}

	public double getZCoordinate() {
		return (double) this.zCoord;
	}

	public float getXOffset() {
		return this.xOffset;
	}

	public float getYOffset() {
		return this.yOffset;
	}

	public float getZOffset() {
		return this.zOffset;
	}

	public float getParticleSpeed() {
		return this.particleSpeed;
	}

	public int getParticleCount() {
		return this.particleCount;
	}

	public int[] getParticleArgs() {
		return this.particleArguments;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleParticles(this);
	}
}
