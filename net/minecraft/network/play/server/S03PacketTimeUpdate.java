package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S03PacketTimeUpdate implements Packet<INetHandlerPlayClient> {
	private long totalWorldTime;
	private long worldTime;

	public S03PacketTimeUpdate() {
	}

	public S03PacketTimeUpdate(long totalWorldTimeIn, long totalTimeIn, boolean doDayLightCycle) {
		this.totalWorldTime = totalWorldTimeIn;
		this.worldTime = totalTimeIn;
		if (!doDayLightCycle) {
			this.worldTime = -this.worldTime;
			if (this.worldTime == 0L) {
				this.worldTime = -1L;
			}
		}

	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.totalWorldTime = parPacketBuffer.readLong();
		this.worldTime = parPacketBuffer.readLong();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeLong(this.totalWorldTime);
		parPacketBuffer.writeLong(this.worldTime);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleTimeUpdate(this);
	}

	public long getTotalWorldTime() {
		return this.totalWorldTime;
	}

	public long getWorldTime() {
		return this.worldTime;
	}
}
