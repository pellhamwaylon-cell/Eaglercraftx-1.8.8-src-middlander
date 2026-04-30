package net.minecraft.network.play.client;

import java.io.IOException;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;

public class C08PacketPlayerBlockPlacement implements Packet<INetHandlerPlayServer> {
	private static final BlockPos field_179726_a = new BlockPos(-1, -1, -1);
	private BlockPos position;
	private int placedBlockDirection;
	private ItemStack stack;
	private float facingX;
	private float facingY;
	private float facingZ;

	public C08PacketPlayerBlockPlacement() {
	}

	public C08PacketPlayerBlockPlacement(ItemStack stackIn) {
		this(field_179726_a, 255, stackIn, 0.0F, 0.0F, 0.0F);
	}

	public C08PacketPlayerBlockPlacement(BlockPos positionIn, int placedBlockDirectionIn, ItemStack stackIn,
			float facingXIn, float facingYIn, float facingZIn) {
		this.position = positionIn;
		this.placedBlockDirection = placedBlockDirectionIn;
		this.stack = stackIn != null ? stackIn.copy() : null;
		this.facingX = facingXIn;
		this.facingY = facingYIn;
		this.facingZ = facingZIn;
	}

	public void readPacketData(PacketBuffer parPacketBuffer) throws IOException {
		this.position = parPacketBuffer.readBlockPos();
		this.placedBlockDirection = parPacketBuffer.readUnsignedByte();
		this.stack = parPacketBuffer.readItemStackFromBuffer();
		this.facingX = (float) parPacketBuffer.readUnsignedByte() / 16.0F;
		this.facingY = (float) parPacketBuffer.readUnsignedByte() / 16.0F;
		this.facingZ = (float) parPacketBuffer.readUnsignedByte() / 16.0F;
	}

	public void writePacketData(PacketBuffer parPacketBuffer) throws IOException {
		parPacketBuffer.writeBlockPos(this.position);
		parPacketBuffer.writeByte(this.placedBlockDirection);
		parPacketBuffer.writeItemStackToBuffer(this.stack);
		parPacketBuffer.writeByte((int) (this.facingX * 16.0F));
		parPacketBuffer.writeByte((int) (this.facingY * 16.0F));
		parPacketBuffer.writeByte((int) (this.facingZ * 16.0F));
	}

	public void processPacket(INetHandlerPlayServer inethandlerplayserver) {
		inethandlerplayserver.processPlayerBlockPlacement(this);
	}

	public BlockPos getPosition() {
		return this.position;
	}

	public int getPlacedBlockDirection() {
		return this.placedBlockDirection;
	}

	public ItemStack getStack() {
		return this.stack;
	}

	public float getPlacedBlockOffsetX() {
		return this.facingX;
	}

	public float getPlacedBlockOffsetY() {
		return this.facingY;
	}

	public float getPlacedBlockOffsetZ() {
		return this.facingZ;
	}
}
