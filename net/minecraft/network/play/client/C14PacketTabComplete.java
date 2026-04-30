package net.minecraft.network.play.client;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;

public class C14PacketTabComplete implements Packet<INetHandlerPlayServer> {
	private String message;
	private BlockPos targetBlock;

	public C14PacketTabComplete() {
	}

	public C14PacketTabComplete(String msg) {
		this(msg, (BlockPos) null);
	}

	public C14PacketTabComplete(String msg, BlockPos target) {
		this.message = msg;
		this.targetBlock = target;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.message = parPacketBuffer.readStringFromBuffer(32767);
		boolean flag = parPacketBuffer.readBoolean();
		if (flag) {
			this.targetBlock = parPacketBuffer.readBlockPos();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(StringUtils.substring(this.message, 0, 32767));
		boolean flag = this.targetBlock != null;
		parPacketBuffer.writeBoolean(flag);
		if (flag) {
			parPacketBuffer.writeBlockPos(this.targetBlock);
		}

	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processTabComplete(this);
	}

	public String getMessage() {
		return this.message;
	}

	public BlockPos getTargetBlock() {
		return this.targetBlock;
	}
}
