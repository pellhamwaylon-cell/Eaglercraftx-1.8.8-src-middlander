package net.minecraft.client.gui.spectator.categories;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiSpectator;
import net.minecraft.client.gui.spectator.ISpectatorMenuObject;
import net.minecraft.client.gui.spectator.ISpectatorMenuView;
import net.minecraft.client.gui.spectator.PlayerMenuObject;
import net.minecraft.client.gui.spectator.SpectatorMenu;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class TeleportToPlayer implements ISpectatorMenuView, ISpectatorMenuObject {
	private static final Ordering<NetworkPlayerInfo> field_178674_a = Ordering
			.from(new Comparator<NetworkPlayerInfo>() {
				public int compare(NetworkPlayerInfo networkplayerinfo, NetworkPlayerInfo networkplayerinfo1) {
					return ComparisonChain.start().compare(networkplayerinfo.getGameProfile().getId(),
							networkplayerinfo1.getGameProfile().getId()).result();
				}
			});
	private final List<ISpectatorMenuObject> field_178673_b;

	public TeleportToPlayer() {
		this(field_178674_a.sortedCopy(Minecraft.getMinecraft().getNetHandler().getPlayerInfoMap()));
	}

	public TeleportToPlayer(Collection<NetworkPlayerInfo> parCollection) {
		this.field_178673_b = Lists.newArrayList();

		List<NetworkPlayerInfo> lst = field_178674_a.sortedCopy(parCollection);
		for (int i = 0, l = lst.size(); i < l; ++i) {
			NetworkPlayerInfo networkplayerinfo = lst.get(i);
			if (networkplayerinfo.getGameType() != WorldSettings.GameType.SPECTATOR) {
				this.field_178673_b.add(new PlayerMenuObject(networkplayerinfo.getGameProfile()));
			}
		}

	}

	public List<ISpectatorMenuObject> func_178669_a() {
		return this.field_178673_b;
	}

	public IChatComponent func_178670_b() {
		return new ChatComponentText("Select a player to teleport to");
	}

	public void func_178661_a(SpectatorMenu spectatormenu) {
		spectatormenu.func_178647_a(this);
	}

	public IChatComponent getSpectatorName() {
		return new ChatComponentText("Teleport to player");
	}

	public void func_178663_a(float var1, int var2) {
		Minecraft.getMinecraft().getTextureManager().bindTexture(GuiSpectator.field_175269_a);
		Gui.drawModalRectWithCustomSizedTexture(0, 0, 0.0F, 0.0F, 16, 16, 256.0F, 256.0F);
	}

	public boolean func_178662_A_() {
		return !this.field_178673_b.isEmpty();
	}
}
