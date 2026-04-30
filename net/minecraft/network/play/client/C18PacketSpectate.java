package net.minecraft.network.play.client;

import java.io.IOException;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.WorldServer;

public class C18PacketSpectate implements Packet<INetHandlerPlayServer> {
	private EaglercraftUUID id;

	public C18PacketSpectate() {
	}

	public C18PacketSpectate(EaglercraftUUID id) {
		this.id = id;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.id = parPacketBuffer.readUuid();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeUuid(this.id);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.handleSpectate(this);
	}

	public Entity getEntity(WorldServer worldIn) {
		return worldIn.getEntityFromUuid(this.id);
	}
}
