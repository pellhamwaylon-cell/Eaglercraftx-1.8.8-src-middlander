package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class S45PacketTitle implements Packet<INetHandlerPlayClient> {
	private S45PacketTitle.Type type;
	private IChatComponent message;
	private int fadeInTime;
	private int displayTime;
	private int fadeOutTime;

	public S45PacketTitle() {
	}

	public S45PacketTitle(S45PacketTitle.Type type, IChatComponent message) {
		this(type, message, -1, -1, -1);
	}

	public S45PacketTitle(int fadeInTime, int displayTime, int fadeOutTime) {
		this(S45PacketTitle.Type.TIMES, (IChatComponent) null, fadeInTime, displayTime, fadeOutTime);
	}

	public S45PacketTitle(S45PacketTitle.Type type, IChatComponent message, int fadeInTime, int displayTime,
			int fadeOutTime) {
		this.type = type;
		this.message = message;
		this.fadeInTime = fadeInTime;
		this.displayTime = displayTime;
		this.fadeOutTime = fadeOutTime;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.type = (S45PacketTitle.Type) parPacketBuffer.readEnumValue(S45PacketTitle.Type.class);
		if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
			this.message = parPacketBuffer.readChatComponent();
		}

		if (this.type == S45PacketTitle.Type.TIMES) {
			this.fadeInTime = parPacketBuffer.readInt();
			this.displayTime = parPacketBuffer.readInt();
			this.fadeOutTime = parPacketBuffer.readInt();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeEnumValue(this.type);
		if (this.type == S45PacketTitle.Type.TITLE || this.type == S45PacketTitle.Type.SUBTITLE) {
			parPacketBuffer.writeChatComponent(this.message);
		}

		if (this.type == S45PacketTitle.Type.TIMES) {
			parPacketBuffer.writeInt(this.fadeInTime);
			parPacketBuffer.writeInt(this.displayTime);
			parPacketBuffer.writeInt(this.fadeOutTime);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleTitle(this);
	}

	public S45PacketTitle.Type getType() {
		return this.type;
	}

	public IChatComponent getMessage() {
		return this.message;
	}

	public int getFadeInTime() {
		return this.fadeInTime;
	}

	public int getDisplayTime() {
		return this.displayTime;
	}

	public int getFadeOutTime() {
		return this.fadeOutTime;
	}

	public static enum Type {
		TITLE, SUBTITLE, TIMES, CLEAR, RESET;

		public static S45PacketTitle.Type byName(String name) {
			S45PacketTitle.Type[] types = values();
			for (int i = 0; i < types.length; ++i) {
				S45PacketTitle.Type s45packettitle$type = types[i];
				if (s45packettitle$type.name().equalsIgnoreCase(name)) {
					return s45packettitle$type;
				}
			}

			return TITLE;
		}

		public static String[] getNames() {
			S45PacketTitle.Type[] types = values();
			String[] astring = new String[types.length];

			for (int i = 0; i < types.length; ++i) {
				astring[i] = types[i].name().toLowerCase();
			}

			return astring;
		}
	}
}
