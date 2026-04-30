package net.minecraft.client.entity;

import net.lax1dude.eaglercraft.v1_8.EagRuntime;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;
import net.lax1dude.eaglercraft.v1_8.mojang.authlib.GameProfile;
import net.lax1dude.eaglercraft.v1_8.profanity_filter.ProfanityFilter;
import net.lax1dude.eaglercraft.v1_8.profile.SkinModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.event.ClickEvent;
import net.minecraft.init.Items;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;

public abstract class AbstractClientPlayer extends EntityPlayer {
	private NetworkPlayerInfo playerInfo;

	public long eaglerHighPolyAnimationTick = EagRuntime.steadyTimeMillis();
	public float eaglerHighPolyAnimationFloat1 = 0.0f;
	public float eaglerHighPolyAnimationFloat2 = 0.0f;
	public float eaglerHighPolyAnimationFloat3 = 0.0f;
	public float eaglerHighPolyAnimationFloat4 = 0.0f;
	public float eaglerHighPolyAnimationFloat5 = 0.0f;
	public float eaglerHighPolyAnimationFloat6 = 0.0f;
	public EaglercraftUUID clientBrandUUIDCache = null;
	private String nameProfanityFilter = null;

	public AbstractClientPlayer(World worldIn, GameProfile playerProfile) {
		super(worldIn, playerProfile);
	}

	public boolean isSpectator() {
		NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getNetHandler()
				.getPlayerInfo(this.getGameProfile().getId());
		return networkplayerinfo != null && networkplayerinfo.getGameType() == WorldSettings.GameType.SPECTATOR;
	}

	public boolean hasPlayerInfo() {
		return this.getPlayerInfo() != null;
	}

	protected NetworkPlayerInfo getPlayerInfo() {
		if (this.playerInfo == null) {
			this.playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getUniqueID());
		}

		return this.playerInfo;
	}

	public boolean hasSkin() {
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo != null && networkplayerinfo.hasLocationSkin();
	}

	public ResourceLocation getLocationSkin() {
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? DefaultPlayerSkin.getDefaultSkin(this.getUniqueID())
				: networkplayerinfo.getLocationSkin();
	}

	public ResourceLocation getLocationCape() {
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? null : networkplayerinfo.getLocationCape();
	}

	public String getSkinType() {
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? DefaultPlayerSkin.getSkinType(this.getUniqueID())
				: networkplayerinfo.getSkinType();
	}

	public SkinModel getEaglerSkinModel() {
		NetworkPlayerInfo networkplayerinfo = this.getPlayerInfo();
		return networkplayerinfo == null ? SkinModel.STEVE : networkplayerinfo.getEaglerSkinModel();
	}

	public float getFovModifier() {
		float f = 1.0F;
		if (this.capabilities.isFlying) {
			f *= 1.1F;
		}

		IAttributeInstance iattributeinstance = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
		f = (float) ((double) f
				* ((iattributeinstance.getAttributeValue() / (double) this.capabilities.getWalkSpeed() + 1.0D) / 2.0D));
		if (this.capabilities.getWalkSpeed() == 0.0F || Float.isNaN(f) || Float.isInfinite(f)) {
			f = 1.0F;
		}

		if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
			int i = this.getItemInUseDuration();
			float f1 = (float) i / 20.0F;
			if (f1 > 1.0F) {
				f1 = 1.0F;
			} else {
				f1 = f1 * f1;
			}

			f *= 1.0F - f1 * 0.15F;
		}

		return f;
	}

	public String getNameProfanityFilter() {
		if (Minecraft.getMinecraft().isEnableProfanityFilter()) {
			if (nameProfanityFilter == null) {
				nameProfanityFilter = ProfanityFilter.getInstance()
						.profanityFilterString(this.getGameProfile().getName());
			}
			return nameProfanityFilter;
		} else {
			return this.getGameProfile().getName();
		}
	}

	public IChatComponent getDisplayNameProfanityFilter() {
		ChatComponentText chatcomponenttext = new ChatComponentText(
				ScorePlayerTeam.formatPlayerName(this.getTeam(), this.getNameProfanityFilter()));
		chatcomponenttext.getChatStyle()
				.setChatClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + this.getName() + " "));
		chatcomponenttext.getChatStyle().setChatHoverEvent(this.getHoverEvent());
		chatcomponenttext.getChatStyle().setInsertion(this.getName());
		return chatcomponenttext;
	}
}
