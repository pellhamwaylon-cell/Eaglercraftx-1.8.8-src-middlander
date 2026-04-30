package net.minecraft.network.play.server;

import java.io.IOException;

import com.carrotsearch.hppc.ObjectIntHashMap;
import com.carrotsearch.hppc.ObjectIntMap;
import com.carrotsearch.hppc.cursors.ObjectIntCursor;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;

public class S37PacketStatistics implements Packet<INetHandlerPlayClient> {
	private ObjectIntMap<StatBase> field_148976_a;

	public S37PacketStatistics() {
	}

	public S37PacketStatistics(ObjectIntMap<StatBase> parMap) {
		this.field_148976_a = parMap;
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleStatistics(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		int i = parPacketBuffer.readVarIntFromBuffer();
		this.field_148976_a = new ObjectIntHashMap<>();

		for (int j = 0; j < i; ++j) {
			StatBase statbase = StatList.getOneShotStat(parPacketBuffer.readStringFromBuffer(32767));
			int k = parPacketBuffer.readVarIntFromBuffer();
			if (statbase != null) {
				this.field_148976_a.put(statbase, k);
			}
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.field_148976_a.size());

		for (ObjectIntCursor<StatBase> entry : this.field_148976_a) {
			parPacketBuffer.writeString(entry.key.statId);
			parPacketBuffer.writeVarIntToBuffer(entry.value);
		}

	}

	public ObjectIntMap<StatBase> func_148974_c() {
		return this.field_148976_a;
	}
}
