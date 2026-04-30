package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S1BPacketEntityAttach implements Packet<INetHandlerPlayClient> {
	private int leash;
	private int entityId;
	private int vehicleEntityId;

	public S1BPacketEntityAttach() {
	}

	public S1BPacketEntityAttach(int leashIn, Entity entityIn, Entity vehicle) {
		this.leash = leashIn;
		this.entityId = entityIn.getEntityId();
		this.vehicleEntityId = vehicle != null ? vehicle.getEntityId() : -1;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readInt();
		this.vehicleEntityId = parPacketBuffer.readInt();
		this.leash = parPacketBuffer.readUnsignedByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeInt(this.entityId);
		parPacketBuffer.writeInt(this.vehicleEntityId);
		parPacketBuffer.writeByte(this.leash);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityAttach(this);
	}

	public int getLeash() {
		return this.leash;
	}

	public int getEntityId() {
		return this.entityId;
	}

	public int getVehicleEntityId() {
		return this.vehicleEntityId;
	}
}
