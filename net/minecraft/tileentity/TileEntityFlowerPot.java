package net.minecraft.tileentity;

import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.ResourceLocation;

public class TileEntityFlowerPot extends TileEntity {
	private Item flowerPotItem;
	private int flowerPotData;

	public TileEntityFlowerPot() {
	}

	public TileEntityFlowerPot(Item potItem, int potData) {
		this.flowerPotItem = potItem;
		this.flowerPotData = potData;
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		ResourceLocation resourcelocation = (ResourceLocation) Item.itemRegistry.getNameForObject(this.flowerPotItem);
		nbttagcompound.setString("Item", resourcelocation == null ? "" : resourcelocation.toString());
		nbttagcompound.setInteger("Data", this.flowerPotData);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		if (nbttagcompound.hasKey("Item", 8)) {
			this.flowerPotItem = Item.getByNameOrId(nbttagcompound.getString("Item"));
		} else {
			this.flowerPotItem = Item.getItemById(nbttagcompound.getInteger("Item"));
		}

		this.flowerPotData = nbttagcompound.getInteger("Data");
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		nbttagcompound.removeTag("Item");
		nbttagcompound.setInteger("Item", Item.getIdFromItem(this.flowerPotItem));
		return new S35PacketUpdateTileEntity(this.pos, 5, nbttagcompound);
	}

	public void setFlowerPotData(Item potItem, int potData) {
		this.flowerPotItem = potItem;
		this.flowerPotData = potData;
	}

	public Item getFlowerPotItem() {
		return this.flowerPotItem;
	}

	public int getFlowerPotData() {
		return this.flowerPotData;
	}
}
