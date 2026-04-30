package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;

public class S41PacketServerDifficulty implements Packet<INetHandlerPlayClient> {
	private EnumDifficulty difficulty;
	private boolean difficultyLocked;

	public S41PacketServerDifficulty() {
	}

	public S41PacketServerDifficulty(EnumDifficulty difficultyIn, boolean lockedIn) {
		this.difficulty = difficultyIn;
		this.difficultyLocked = lockedIn;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleServerDifficulty(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		int i = parPacketBuffer.readUnsignedByte();
		this.difficulty = EnumDifficulty.getDifficultyEnum(i & 3);
		this.difficultyLocked = (i & 4) != 0;
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.difficulty.getDifficultyId() | (this.difficultyLocked ? 4 : 0));
	}

	public boolean isDifficultyLocked() {
		return this.difficultyLocked;
	}

	public EnumDifficulty getDifficulty() {
		return this.difficulty;
	}
}
