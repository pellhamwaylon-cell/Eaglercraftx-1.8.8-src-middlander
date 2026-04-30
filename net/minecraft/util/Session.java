package net.minecraft.util;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import net.lax1dude.eaglercraft.v1_8.profile.EaglerProfile;
import net.minecraft.entity.player.EntityPlayer;

public class Session {
	private GameProfile profile;

	private static final EaglercraftUUID outOfGameUUID;

	public Session() {
		reset();
	}

	public GameProfile getProfile() {
		return profile;
	}

	public void update(String serverUsername, EaglercraftUUID uuid) {
		profile = new GameProfile(uuid, serverUsername);
	}

	public void reset() {
		update(EaglerProfile.getName(), outOfGameUUID);
	}

	public void setLAN() {
		update(EaglerProfile.getName(), EntityPlayer.getOfflineUUID(EaglerProfile.getName()));
	}

	static {
		byte[] bytes = new byte[16];
		(new EaglercraftRandom()).nextBytes(bytes);
		outOfGameUUID = new EaglercraftUUID(bytes);
	}

}
