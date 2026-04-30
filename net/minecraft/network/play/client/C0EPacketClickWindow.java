package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C0EPacketClickWindow implements Packet<INetHandlerPlayServer> {
	private int windowId;
	private int slotId;
	private int usedButton;
	private short actionNumber;
	private ItemStack clickedItem;
	private int mode;

	public C0EPacketClickWindow() {
	}

	public C0EPacketClickWindow(int windowId, int slotId, int usedButton, int mode, ItemStack clickedItem,
			short actionNumber) {
		this.windowId = windowId;
		this.slotId = slotId;
		this.usedButton = usedButton;
		this.clickedItem = clickedItem != null ? clickedItem.copy() : null;
		this.actionNumber = actionNumber;
		this.mode = mode;
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processClickWindow(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readByte();
		this.slotId = parPacketBuffer.readShort();
		this.usedButton = parPacketBuffer.readByte();
		this.actionNumber = parPacketBuffer.readShort();
		this.mode = parPacketBuffer.readByte();
		this.clickedItem = parPacketBuffer.readItemStackFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.slotId);
		parPacketBuffer.writeByte(this.usedButton);
		parPacketBuffer.writeShort(this.actionNumber);
		parPacketBuffer.writeByte(this.mode);
		parPacketBuffer.writeItemStackToBuffer(this.clickedItem);
	}

	public int getWindowId() {
		return this.windowId;
	}

	public int getSlotId() {
		return this.slotId;
	}

	public int getUsedButton() {
		return this.usedButton;
	}

	public short getActionNumber() {
		return this.actionNumber;
	}

	public ItemStack getClickedItem() {
		return this.clickedItem;
	}

	public int getMode() {
		return this.mode;
	}
}
