package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C10PacketCreativeInventoryAction implements Packet<INetHandlerPlayServer> {
	private int slotId;
	private ItemStack stack;

	public C10PacketCreativeInventoryAction() {
	}

	public C10PacketCreativeInventoryAction(int slotIdIn, ItemStack stackIn) {
		this.slotId = slotIdIn;
		this.stack = stackIn != null ? stackIn.copy() : null;
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processCreativeInventoryAction(this);
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.slotId = parPacketBuffer.readShort();
		this.stack = parPacketBuffer.readItemStackFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeShort(this.slotId);
		parPacketBuffer.writeItemStackToBuffer(this.stack);
	}

	public int getSlotId() {
		return this.slotId;
	}

	public ItemStack getStack() {
		return this.stack;
	}
}
