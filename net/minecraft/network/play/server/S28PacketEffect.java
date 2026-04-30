package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class S28PacketEffect implements Packet<INetHandlerPlayClient> {
	private int soundType;
	private BlockPos soundPos;
	private int soundData;
	private boolean serverWide;

	public S28PacketEffect() {
	}

	public S28PacketEffect(int soundTypeIn, BlockPos soundPosIn, int soundDataIn, boolean serverWideIn) {
		this.soundType = soundTypeIn;
		this.soundPos = soundPosIn;
		this.soundData = soundDataIn;
		this.serverWide = serverWideIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.soundType = parPacketBuffer.readInt();
		this.soundPos = parPacketBuffer.readBlockPos();
		this.soundData = parPacketBuffer.readInt();
		this.serverWide = parPacketBuffer.readBoolean();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeInt(this.soundType);
		parPacketBuffer.writeBlockPos(this.soundPos);
		parPacketBuffer.writeInt(this.soundData);
		parPacketBuffer.writeBoolean(this.serverWide);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEffect(this);
	}

	public boolean isSoundServerwide() {
		return this.serverWide;
	}

	public int getSoundType() {
		return this.soundType;
	}

	public int getSoundData() {
		return this.soundData;
	}

	public BlockPos getSoundPos() {
		return this.soundPos;
	}
}
