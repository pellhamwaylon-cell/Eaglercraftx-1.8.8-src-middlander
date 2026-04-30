package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S25PacketBlockBreakAnim implements Packet<INetHandlerPlayClient> {
	private int breakerId;
	private BlockPos position;
	private int progress;

	public S25PacketBlockBreakAnim() {
	}

	public S25PacketBlockBreakAnim(int breakerId, BlockPos pos, int progress) {
		this.breakerId = breakerId;
		this.position = pos;
		this.progress = progress;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.breakerId = parPacketBuffer.readVarIntFromBuffer();
		this.position = parPacketBuffer.readBlockPos();
		this.progress = parPacketBuffer.readUnsignedByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.breakerId);
		parPacketBuffer.writeBlockPos(this.position);
		parPacketBuffer.writeByte(this.progress);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleBlockBreakAnim(this);
	}

	public int getBreakerId() {
		return this.breakerId;
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public int getProgress() {
		return this.progress;
	}
}
