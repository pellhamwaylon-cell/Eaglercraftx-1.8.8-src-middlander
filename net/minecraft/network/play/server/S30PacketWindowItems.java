package net.minecraft.network.play.server;

import java.io.IOException;
import java.util.List;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S30PacketWindowItems implements Packet<INetHandlerPlayClient> {
	private int windowId;
	private ItemStack[] itemStacks;

	public S30PacketWindowItems() {
	}

	public S30PacketWindowItems(int windowIdIn, List<ItemStack> parList) {
		this.windowId = windowIdIn;
		this.itemStacks = new ItemStack[parList.size()];

		for (int i = 0; i < this.itemStacks.length; ++i) {
			ItemStack itemstack = (ItemStack) parList.get(i);
			this.itemStacks[i] = itemstack == null ? null : itemstack.copy();
		}

	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.windowId = parPacketBuffer.readUnsignedByte();
		short short1 = parPacketBuffer.readShort();
		this.itemStacks = new ItemStack[short1];

		for (int i = 0; i < short1; ++i) {
			this.itemStacks[i] = parPacketBuffer.readItemStackFromBuffer();
		}

	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeByte(this.windowId);
		parPacketBuffer.writeShort(this.itemStacks.length);

		for (int i = 0; i < this.itemStacks.length; ++i) {
			parPacketBuffer.writeItemStackToBuffer(this.itemStacks[i]);
		}

	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleWindowItems(this);
	}

	public int func_148911_c() {
		return this.windowId;
	}

	public ItemStack[] getItemStacks() {
		return this.itemStacks;
	}
}
