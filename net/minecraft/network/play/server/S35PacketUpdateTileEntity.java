package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S35PacketUpdateTileEntity implements Packet<INetHandlerPlayClient> {
	private BlockPos blockPos;
	private int metadata;
	private NBTTagCompound nbt;

	public S35PacketUpdateTileEntity() {
	}

	public S35PacketUpdateTileEntity(BlockPos blockPosIn, int metadataIn, NBTTagCompound nbtIn) {
		this.blockPos = blockPosIn;
		this.metadata = metadataIn;
		this.nbt = nbtIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.blockPos = parPacketBuffer.readBlockPos();
		this.metadata = parPacketBuffer.readUnsignedByte();
		this.nbt = parPacketBuffer.readNBTTagCompoundFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeBlockPos(this.blockPos);
		parPacketBuffer.writeByte((byte) this.metadata);
		parPacketBuffer.writeNBTTagCompoundToBuffer(this.nbt);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleUpdateTileEntity(this);
	}

	public BlockPos getPos() {
		return this.blockPos;
	}

	public int getTileEntityType() {
		return this.metadata;
	}

	public NBTTagCompound getNbtCompound() {
		return this.nbt;
	}
}
