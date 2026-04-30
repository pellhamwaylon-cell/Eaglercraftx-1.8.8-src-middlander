package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S04PacketEntityEquipment implements Packet<INetHandlerPlayClient> {
	private int entityID;
	private int equipmentSlot;
	private ItemStack itemStack;

	public S04PacketEntityEquipment() {
	}

	public S04PacketEntityEquipment(int entityIDIn, int parInt1, ItemStack itemStackIn) {
		this.entityID = entityIDIn;
		this.equipmentSlot = parInt1;
		this.itemStack = itemStackIn == null ? null : itemStackIn.copy();
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.entityID = parPacketBuffer.readVarIntFromBuffer();
		this.equipmentSlot = parPacketBuffer.readShort();
		this.itemStack = parPacketBuffer.readItemStackFromBuffer();
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeVarIntToBuffer(this.entityID);
		parPacketBuffer.writeShort(this.equipmentSlot);
		parPacketBuffer.writeItemStackToBuffer(this.itemStack);
	}

	public void processPacket(INetHandlerPlayClient inethandlerplayclient) {
		inethandlerplayclient.handleEntityEquipment(this);
	}

	public ItemStack getItemStack() {
		return this.itemStack;
	}

	public int getEntityID() {
		return this.entityID;
	}

	public int getEquipmentSlot() {
		return this.equipmentSlot;
	}
}
