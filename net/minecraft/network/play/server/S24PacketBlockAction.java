package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S24PacketBlockAction implements Packet<INetHandlerPlayClient> {
	private BlockPos blockPosition;
	private int instrument;
	private int pitch;
	private Block block;

	public S24PacketBlockAction() {
	}

	public S24PacketBlockAction(BlockPos blockPositionIn, Block blockIn, int instrumentIn, int pitchIn) {
		this.blockPosition = blockPositionIn;
		this.instrument = instrumentIn;
		this.pitch = pitchIn;
		this.block = blockIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.blockPosition = parPacketBuffer.readBlockPos();
		this.instrument = parPacketBuffer.readUnsignedByte();
		this.pitch = parPacketBuffer.readUnsignedByte();
		this.block = Block.getBlockById(parPacketBuffer.readVarIntFromBuffer() & 4095);
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeBlockPos(this.blockPosition);
		parPacketBuffer.writeByte(this.instrument);
		parPacketBuffer.writeByte(this.pitch);
		parPacketBuffer.writeVarIntToBuffer(Block.getIdFromBlock(this.block) & 4095);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleBlockAction(this);
	}

	public BlockPos getBlockPosition() {
		return this.blockPosition;
	}

	public int getData1() {
		return this.instrument;
	}

	public int getData2() {
		return this.pitch;
	}

	public Block getBlockType() {
		return this.block;
	}
}
