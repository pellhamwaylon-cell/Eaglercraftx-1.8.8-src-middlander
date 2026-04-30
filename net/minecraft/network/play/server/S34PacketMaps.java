package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.Collection;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.Vec4b;
import net.minecraft.world.storage.MapData;

public class S34PacketMaps implements Packet<INetHandlerPlayClient> {
	private int mapId;
	private byte mapScale;
	private Vec4b[] mapVisiblePlayersVec4b;
	private int mapMinX;
	private int mapMinY;
	private int mapMaxX;
	private int mapMaxY;
	private byte[] mapDataBytes;

	public S34PacketMaps() {
	}

	public S34PacketMaps(int mapIdIn, byte scale, Collection<Vec4b> visiblePlayers, byte[] colors, int minX, int minY,
			int maxX, int maxY) {
		this.mapId = mapIdIn;
		this.mapScale = scale;
		this.mapVisiblePlayersVec4b = (Vec4b[]) visiblePlayers.toArray(new Vec4b[visiblePlayers.size()]);
		this.mapMinX = minX;
		this.mapMinY = minY;
		this.mapMaxX = maxX;
		this.mapMaxY = maxY;
		this.mapDataBytes = new byte[maxX * maxY];

		for (int i = 0; i < maxX; ++i) {
			for (int j = 0; j < maxY; ++j) {
				this.mapDataBytes[i + j * maxX] = colors[minX + i + (minY + j) * 128];
			}
		}

	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.mapId = parPacketBuffer.readVarIntFromBuffer();
		this.mapScale = parPacketBuffer.readByte();
		this.mapVisiblePlayersVec4b = new Vec4b[parPacketBuffer.readVarIntFromBuffer()];

		for (int i = 0; i < this.mapVisiblePlayersVec4b.length; ++i) {
			short short1 = (short) parPacketBuffer.readByte();
			this.mapVisiblePlayersVec4b[i] = new Vec4b((byte) (short1 >> 4 & 15), parPacketBuffer.readByte(),
					parPacketBuffer.readByte(), (byte) (short1 & 15));
		}

		this.mapMaxX = parPacketBuffer.readUnsignedByte();
		if (this.mapMaxX > 0) {
			this.mapMaxY = parPacketBuffer.readUnsignedByte();
			this.mapMinX = parPacketBuffer.readUnsignedByte();
			this.mapMinY = parPacketBuffer.readUnsignedByte();
			this.mapDataBytes = parPacketBuffer.readByteArray(0x400000);
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.mapId);
		parPacketBuffer.writeByte(this.mapScale);
		parPacketBuffer.writeVarIntToBuffer(this.mapVisiblePlayersVec4b.length);

		for (int i = 0; i < this.mapVisiblePlayersVec4b.length; ++i) {
			Vec4b vec4b = this.mapVisiblePlayersVec4b[i];
			parPacketBuffer.writeByte((vec4b.func_176110_a() & 15) << 4 | vec4b.func_176111_d() & 15);
			parPacketBuffer.writeByte(vec4b.func_176112_b());
			parPacketBuffer.writeByte(vec4b.func_176113_c());
		}

		parPacketBuffer.writeByte(this.mapMaxX);
		if (this.mapMaxX > 0) {
			parPacketBuffer.writeByte(this.mapMaxY);
			parPacketBuffer.writeByte(this.mapMinX);
			parPacketBuffer.writeByte(this.mapMinY);
			parPacketBuffer.writeByteArray(this.mapDataBytes);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleMaps(this);
	}

	public int getMapId() {
		return this.mapId;
	}

	public void setMapdataTo(MapData mapdataIn) {
		mapdataIn.scale = this.mapScale;
		mapdataIn.mapDecorations.clear();

		for (int i = 0; i < this.mapVisiblePlayersVec4b.length; ++i) {
			Vec4b vec4b = this.mapVisiblePlayersVec4b[i];
			mapdataIn.mapDecorations.put("icon-" + i, vec4b);
		}

		for (int j = 0; j < this.mapMaxX; ++j) {
			for (int k = 0; k < this.mapMaxY; ++k) {
				mapdataIn.colors[this.mapMinX + j + (this.mapMinY + k) * 128] = this.mapDataBytes[j + k * this.mapMaxX];
			}
		}

	}
}
