package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2BPacketChangeGameState implements Packet<INetHandlerPlayClient> {
	public static final String[] MESSAGE_NAMES = new String[] { "tile.bed.notValid" };
	private int state;
	private float field_149141_c;

	public S2BPacketChangeGameState() {
	}

	public S2BPacketChangeGameState(int stateIn, float parFloat1) {
		this.state = stateIn;
		this.field_149141_c = parFloat1;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.state = parPacketBuffer.readUnsignedByte();
		this.field_149141_c = parPacketBuffer.readFloat();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.state);
		parPacketBuffer.writeFloat(this.field_149141_c);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleChangeGameState(this);
	}

	public int getGameState() {
		return this.state;
	}

	public float func_149137_d() {
		return this.field_149141_c;
	}
}
