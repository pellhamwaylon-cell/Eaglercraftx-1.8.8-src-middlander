package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S11PacketSpawnExperienceOrb implements Packet<INetHandlerPlayClient> {
	private int entityID;
	private int posX;
	private int posY;
	private int posZ;
	private int xpValue;

	public S11PacketSpawnExperienceOrb() {
	}

	public S11PacketSpawnExperienceOrb(EntityXPOrb xpOrb) {
		this.entityID = xpOrb.getEntityId();
		this.posX = MathHelper.floor_double(xpOrb.posX * 32.0D);
		this.posY = MathHelper.floor_double(xpOrb.posY * 32.0D);
		this.posZ = MathHelper.floor_double(xpOrb.posZ * 32.0D);
		this.xpValue = xpOrb.getXpValue();
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityID = parPacketBuffer.readVarIntFromBuffer();
		this.posX = parPacketBuffer.readInt();
		this.posY = parPacketBuffer.readInt();
		this.posZ = parPacketBuffer.readInt();
		this.xpValue = parPacketBuffer.readShort();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityID);
		parPacketBuffer.writeInt(this.posX);
		parPacketBuffer.writeInt(this.posY);
		parPacketBuffer.writeInt(this.posZ);
		parPacketBuffer.writeShort(this.xpValue);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleSpawnExperienceOrb(this);
	}

	public int getEntityID() {
		return this.entityID;
	}

	public int getX() {
		return this.posX;
	}

	public int getY() {
		return this.posY;
	}

	public int getZ() {
		return this.posZ;
	}

	public int getXPValue() {
		return this.xpValue;
	}
}
