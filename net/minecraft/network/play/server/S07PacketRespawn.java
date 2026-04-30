package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;

public class S07PacketRespawn implements Packet<INetHandlerPlayClient> {
	private int dimensionID;
	private EnumDifficulty difficulty;
	private WorldSettings.GameType gameType;
	private WorldType worldType;

	public S07PacketRespawn() {
	}

	public S07PacketRespawn(int dimensionIDIn, EnumDifficulty difficultyIn, WorldType worldTypeIn,
			WorldSettings.GameType gameTypeIn) {
		this.dimensionID = dimensionIDIn;
		this.difficulty = difficultyIn;
		this.gameType = gameTypeIn;
		this.worldType = worldTypeIn;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleRespawn(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.dimensionID = parPacketBuffer.readInt();
		this.difficulty = EnumDifficulty.getDifficultyEnum(parPacketBuffer.readUnsignedByte());
		this.gameType = WorldSettings.GameType.getByID(parPacketBuffer.readUnsignedByte());
		this.worldType = WorldType.parseWorldType(parPacketBuffer.readStringFromBuffer(16));
		if (this.worldType == null) {
			this.worldType = WorldType.DEFAULT;
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeInt(this.dimensionID);
		parPacketBuffer.writeByte(this.difficulty.getDifficultyId());
		parPacketBuffer.writeByte(this.gameType.getID());
		parPacketBuffer.writeString(this.worldType.getWorldTypeName());
	}

	public int getDimensionID() {
		return this.dimensionID;
	}

	public EnumDifficulty getDifficulty() {
		return this.difficulty;
	}

	public WorldSettings.GameType getGameType() {
		return this.gameType;
	}

	public WorldType getWorldType() {
		return this.worldType;
	}
}
