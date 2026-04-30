package net.minecraft.client.gui.spectator;

import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.network.play.client.C18PacketSpectate;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class PlayerMenuObject implements ISpectatorMenuObject {
	private final GameProfile profile;

	public PlayerMenuObject(GameProfile profileIn) {
		this.profile = profileIn;
	}

	public void func_178661_a(SpectatorMenu menu) {
		Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C18PacketSpectate(this.profile.getId()));
	}

	public IChatComponent getSpectatorName() {
		return new ChatComponentText(this.profile.getName());
	}

	public void func_178663_a(float alpha, int parInt1) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(
				Minecraft.getMinecraft().getNetHandler().getTextureCache().getPlayerSkin(profile).getLocation());
		GlStateManager.color(1.0F, 1.0F, 1.0F, (float) parInt1 / 255.0F);
		Gui.drawScaledCustomSizeModalRect(2, 2, 8.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
		Gui.drawScaledCustomSizeModalRect(2, 2, 40.0F, 8.0F, 8, 8, 12, 12, 64.0F, 64.0F);
	}

	public boolean func_178662_A_() {
		return true;
	}
}
