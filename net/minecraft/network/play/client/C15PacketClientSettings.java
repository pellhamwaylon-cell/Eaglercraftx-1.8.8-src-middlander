package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C15PacketClientSettings implements Packet<INetHandlerPlayServer> {
	private String lang;
	private int view;
	private EntityPlayer.EnumChatVisibility chatVisibility;
	private boolean enableColors;
	private int modelPartFlags;

	public C15PacketClientSettings() {
	}

	public C15PacketClientSettings(String langIn, int viewIn, EntityPlayer.EnumChatVisibility chatVisibilityIn,
			boolean enableColorsIn, int modelPartFlagsIn) {
		this.lang = langIn;
		this.view = viewIn;
		this.chatVisibility = chatVisibilityIn;
		this.enableColors = enableColorsIn;
		this.modelPartFlags = modelPartFlagsIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.lang = parPacketBuffer.readStringFromBuffer(7);
		this.view = parPacketBuffer.readByte();
		this.chatVisibility = EntityPlayer.EnumChatVisibility.getEnumChatVisibility(parPacketBuffer.readByte());
		this.enableColors = parPacketBuffer.readBoolean();
		this.modelPartFlags = parPacketBuffer.readUnsignedByte();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeString(this.lang);
		parPacketBuffer.writeByte(this.view);
		parPacketBuffer.writeByte(this.chatVisibility.getChatVisibility());
		parPacketBuffer.writeBoolean(this.enableColors);
		parPacketBuffer.writeByte(this.modelPartFlags);
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processClientSettings(this);
	}

	public String getLang() {
		return this.lang;
	}

	public EntityPlayer.EnumChatVisibility getChatVisibility() {
		return this.chatVisibility;
	}

	public boolean isColorsEnabled() {
		return this.enableColors;
	}

	public int getModelPartFlags() {
		return this.modelPartFlags;
	}

	public int getViewDistance() {
		return this.view;
	}
}
