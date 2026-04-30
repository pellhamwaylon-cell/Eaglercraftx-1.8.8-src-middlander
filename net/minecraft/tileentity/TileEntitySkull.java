package net.minecraft.tileentity;

import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;

import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.StringUtils;

public class TileEntitySkull extends TileEntity {
	private int skullType;
	private int skullRotation;
	private GameProfile playerProfile = null;

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("SkullType", (byte) (this.skullType & 255));
		nbttagcompound.setByte("Rot", (byte) (this.skullRotation & 255));
		if (this.playerProfile != null) {
			NBTTagCompound nbttagcompound1 = new NBTTagCompound();
			NBTUtil.writeGameProfile(nbttagcompound1, this.playerProfile);
			nbttagcompound.setTag("Owner", nbttagcompound1);
		}

	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.skullType = nbttagcompound.getByte("SkullType");
		this.skullRotation = nbttagcompound.getByte("Rot");
		if (this.skullType == 3) {
			if (nbttagcompound.hasKey("Owner", 10)) {
				this.playerProfile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("Owner"));
			} else if (nbttagcompound.hasKey("ExtraType", 8)) {
				String s = nbttagcompound.getString("ExtraType");
				if (!StringUtils.isNullOrEmpty(s)) {
					this.playerProfile = new GameProfile((EaglercraftUUID) null, s);
					this.updatePlayerProfile();
				}
			}
		}

	}

	public GameProfile getPlayerProfile() {
		return this.playerProfile;
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		return new S35PacketUpdateTileEntity(this.pos, 4, nbttagcompound);
	}

	public void setType(int type) {
		this.skullType = type;
		this.playerProfile = null;
	}

	public void setPlayerProfile(GameProfile playerProfile) {
		this.skullType = 3;
		this.playerProfile = playerProfile;
		this.updatePlayerProfile();
	}

	private void updatePlayerProfile() {
		this.playerProfile = updateGameprofile(this.playerProfile);
		this.markDirty();
	}

	public static GameProfile updateGameprofile(GameProfile input) {
		return input;
	}

	public int getSkullType() {
		return this.skullType;
	}

	public int getSkullRotation() {
		return this.skullRotation;
	}

	public void setSkullRotation(int rotation) {
		this.skullRotation = rotation;
	}
}
