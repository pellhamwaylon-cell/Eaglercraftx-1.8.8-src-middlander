package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S0APacketUseBed implements Packet<INetHandlerPlayClient> {
	private int playerID;
	private BlockPos bedPos;

	public S0APacketUseBed() {
	}

	public S0APacketUseBed(EntityPlayer player, BlockPos bedPosIn) {
		this.playerID = player.getEntityId();
		this.bedPos = bedPosIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.playerID = parPacketBuffer.readVarIntFromBuffer();
		this.bedPos = parPacketBuffer.readBlockPos();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.playerID);
		parPacketBuffer.writeBlockPos(this.bedPos);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleUseBed(this);
	}

	public EntityPlayer getPlayer(World worldIn) {
		return (EntityPlayer) worldIn.getEntityByID(this.playerID);
	}

	public BlockPos getBedPosition() {
		return this.bedPos;
	}
}
