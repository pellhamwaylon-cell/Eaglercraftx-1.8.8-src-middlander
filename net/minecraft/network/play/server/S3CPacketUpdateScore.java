package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;

public class S3CPacketUpdateScore implements Packet<INetHandlerPlayClient> {
	private String name = "";
	private String objective = "";
	private int value;
	private S3CPacketUpdateScore.Action action;

	public S3CPacketUpdateScore() {
	}

	public S3CPacketUpdateScore(Score scoreIn) {
		this.name = scoreIn.getPlayerName();
		this.objective = scoreIn.getObjective().getName();
		this.value = scoreIn.getScorePoints();
		this.action = S3CPacketUpdateScore.Action.CHANGE;
	}

	public S3CPacketUpdateScore(String nameIn) {
		this.name = nameIn;
		this.objective = "";
		this.value = 0;
		this.action = S3CPacketUpdateScore.Action.REMOVE;
	}

	public S3CPacketUpdateScore(String nameIn, ScoreObjective objectiveIn) {
		this.name = nameIn;
		this.objective = objectiveIn.getName();
		this.value = 0;
		this.action = S3CPacketUpdateScore.Action.REMOVE;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.name = parPacketBuffer.readStringFromBuffer(40);
		this.action = (S3CPacketUpdateScore.Action) parPacketBuffer.readEnumValue(S3CPacketUpdateScore.Action.class);
		this.objective = parPacketBuffer.readStringFromBuffer(16);
		if (this.action != S3CPacketUpdateScore.Action.REMOVE) {
			this.value = parPacketBuffer.readVarIntFromBuffer();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.name);
		parPacketBuffer.writeEnumValue(this.action);
		parPacketBuffer.writeString(this.objective);
		if (this.action != S3CPacketUpdateScore.Action.REMOVE) {
			parPacketBuffer.writeVarIntToBuffer(this.value);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleUpdateScore(this);
	}

	public String getPlayerName() {
		return this.name;
	}

	public String getObjectiveName() {
		return this.objective;
	}

	public int getScoreValue() {
		return this.value;
	}

	public S3CPacketUpdateScore.Action getScoreAction() {
		return this.action;
	}

	public static enum Action {
		CHANGE, REMOVE;
	}
}
