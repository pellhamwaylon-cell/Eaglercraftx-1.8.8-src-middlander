package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;

public class S3BPacketScoreboardObjective implements Packet<INetHandlerPlayClient> {
	private String objectiveName;
	private String objectiveValue;
	private IScoreObjectiveCriteria.EnumRenderType type;
	private int field_149342_c;

	public S3BPacketScoreboardObjective() {
	}

	public S3BPacketScoreboardObjective(ScoreObjective parScoreObjective, int parInt1) {
		this.objectiveName = parScoreObjective.getName();
		this.objectiveValue = parScoreObjective.getDisplayName();
		this.type = parScoreObjective.getCriteria().getRenderType();
		this.field_149342_c = parInt1;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.objectiveName = parPacketBuffer.readStringFromBuffer(16);
		this.field_149342_c = parPacketBuffer.readByte();
		if (this.field_149342_c == 0 || this.field_149342_c == 2) {
			this.objectiveValue = parPacketBuffer.readStringFromBuffer(32);
			this.type = IScoreObjectiveCriteria.EnumRenderType.func_178795_a(parPacketBuffer.readStringFromBuffer(16));
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.objectiveName);
		parPacketBuffer.writeByte(this.field_149342_c);
		if (this.field_149342_c == 0 || this.field_149342_c == 2) {
			parPacketBuffer.writeString(this.objectiveValue);
			parPacketBuffer.writeString(this.type.func_178796_a());
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleScoreboardObjective(this);
	}

	public String func_149339_c() {
		return this.objectiveName;
	}

	public String func_149337_d() {
		return this.objectiveValue;
	}

	public int func_149338_e() {
		return this.field_149342_c;
	}

	public IScoreObjectiveCriteria.EnumRenderType func_179817_d() {
		return this.type;
	}
}
