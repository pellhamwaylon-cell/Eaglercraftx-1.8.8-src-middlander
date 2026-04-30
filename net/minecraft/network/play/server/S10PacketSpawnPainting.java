package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.item.EntityPainting;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class S10PacketSpawnPainting implements Packet<INetHandlerPlayClient> {
	private int entityID;
	private BlockPos position;
	private EnumFacing facing;
	private String title;

	public S10PacketSpawnPainting() {
	}

	public S10PacketSpawnPainting(EntityPainting painting) {
		this.entityID = painting.getEntityId();
		this.position = painting.getHangingPosition();
		this.facing = painting.facingDirection;
		this.title = painting.art.title;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityID = parPacketBuffer.readVarIntFromBuffer();
		this.title = parPacketBuffer.readStringFromBuffer(EntityPainting.EnumArt.field_180001_A);
		this.position = parPacketBuffer.readBlockPos();
		this.facing = EnumFacing.getHorizontal(parPacketBuffer.readUnsignedByte());
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityID);
		parPacketBuffer.writeString(this.title);
		parPacketBuffer.writeBlockPos(this.position);
		parPacketBuffer.writeByte(this.facing.getHorizontalIndex());
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleSpawnPainting(this);
	}

	public int getEntityID() {
		return this.entityID;
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public EnumFacing getFacing() {
		return this.facing;
	}

	public String getTitle() {
		return this.title;
	}
}
