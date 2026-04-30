package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class C02PacketUseEntity implements Packet<INetHandlerPlayServer> {
	private int entityId;
	private C02PacketUseEntity.Action action;
	private Vec3 hitVec;

	public C02PacketUseEntity() {
	}

	public C02PacketUseEntity(Entity entity, C02PacketUseEntity.Action action) {
		this.entityId = entity.getEntityId();
		this.action = action;
	}

	public C02PacketUseEntity(Entity entity, Vec3 hitVec) {
		this(entity, C02PacketUseEntity.Action.INTERACT_AT);
		this.hitVec = hitVec;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
		this.action = (C02PacketUseEntity.Action) parPacketBuffer.readEnumValue(C02PacketUseEntity.Action.class);
		if (this.action == C02PacketUseEntity.Action.INTERACT_AT) {
			this.hitVec = new Vec3((double) parPacketBuffer.readFloat(), (double) parPacketBuffer.readFloat(),
					(double) parPacketBuffer.readFloat());
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
		parPacketBuffer.writeEnumValue(this.action);
		if (this.action == C02PacketUseEntity.Action.INTERACT_AT) {
			parPacketBuffer.writeFloat((float) this.hitVec.xCoord);
			parPacketBuffer.writeFloat((float) this.hitVec.yCoord);
			parPacketBuffer.writeFloat((float) this.hitVec.zCoord);
		}

	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processUseEntity(this);
	}

	public Entity getEntityFromWorld(World worldIn) {
		return worldIn.getEntityByID(this.entityId);
	}

	public C02PacketUseEntity.Action getAction() {
		return this.action;
	}

	public Vec3 getHitVec() {
		return this.hitVec;
	}

	public static enum Action {
		INTERACT, ATTACK, INTERACT_AT;
	}
}
