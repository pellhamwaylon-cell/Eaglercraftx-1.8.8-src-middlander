package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S23PacketBlockChange implements Packet<INetHandlerPlayClient> {
	private BlockPos blockPosition;
	private IBlockState blockState;

	public S23PacketBlockChange() {
	}

	public S23PacketBlockChange(World worldIn, BlockPos blockPositionIn) {
		this.blockPosition = blockPositionIn;
		this.blockState = worldIn.getBlockState(blockPositionIn);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.blockPosition = parPacketBuffer.readBlockPos();
		this.blockState = (IBlockState) Block.BLOCK_STATE_IDS.getByValue(parPacketBuffer.readVarIntFromBuffer());
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeBlockPos(this.blockPosition);
		parPacketBuffer.writeVarIntToBuffer(Block.BLOCK_STATE_IDS.get(this.blockState));
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleBlockChange(this);
	}

	public IBlockState getBlockState() {
		return this.blockState;
	}

	public BlockPos getBlockPosition() {
		return this.blockPosition;
	}
}
