package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S49PacketUpdateEntityNBT implements Packet<INetHandlerPlayClient> {
	private int entityId;
	private NBTTagCompound tagCompound;

	public S49PacketUpdateEntityNBT() {
	}

	public S49PacketUpdateEntityNBT(int entityIdIn, NBTTagCompound tagCompoundIn) {
		this.entityId = entityIdIn;
		this.tagCompound = tagCompoundIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityId = parPacketBuffer.readVarIntFromBuffer();
		this.tagCompound = parPacketBuffer.readNBTTagCompoundFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityId);
		parPacketBuffer.writeNBTTagCompoundToBuffer(this.tagCompound);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityNBT(this);
	}

	public NBTTagCompound getTagCompound() {
		return this.tagCompound;
	}

	public Entity getEntity(World worldIn) {
		return worldIn.getEntityByID(this.entityId);
	}
}
