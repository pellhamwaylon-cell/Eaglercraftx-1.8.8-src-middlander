package net.minecraft.client.gui;

import net.lax1dude.eaglercraft.v1_8.profanity_filter.ProfanityFilter;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;

public class ChatLine {
	private final int updateCounterCreated;
	private final IChatComponent lineString;
	private IChatComponent lineStringProfanityFilter;
	private final int chatLineID;

	public ChatLine(int parInt1, IChatComponent parIChatComponent, int parInt2) {
		this.lineString = parIChatComponent;
		this.updateCounterCreated = parInt1;
		this.chatLineID = parInt2;
	}

	public IChatComponent getChatComponent() {
		if (Minecraft.getMinecraft().isEnableProfanityFilter()) {
			if (lineStringProfanityFilter == null) {
				lineStringProfanityFilter = ProfanityFilter.getInstance().profanityFilterChatComponent(lineString);
			}
			return lineStringProfanityFilter;
		} else {
			return lineString;
		}
	}

	public int getUpdatedCounter() {
		return this.updateCounterCreated;
	}

	public int getChatLineID() {
		return this.chatLineID;
	}
}
