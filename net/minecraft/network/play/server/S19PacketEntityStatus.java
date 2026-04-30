package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S19PacketEntityStatus implements Packet<INetHandlerPlayClient> {
	private int entityId;
	private byte logicOpcode;

	public S19PacketEntityStatus() {
	}

	public S19PacketEntityStatus(Entity entityIn, byte opCodeIn) {
		this.entityId = entityIn.getEntityId();
		this.logicOpcode = opCodeIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readInt();
		this.logicOpcode = parPacketBuffer.readByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeInt(this.entityId);
		parPacketBuffer.writeByte(this.logicOpcode);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityStatus(this);
	}

	public Entity getEntity(World worldIn) {
		return worldIn.getEntityByID(this.entityId);
	}

	public byte getOpCode() {
		return this.logicOpcode;
	}
}
