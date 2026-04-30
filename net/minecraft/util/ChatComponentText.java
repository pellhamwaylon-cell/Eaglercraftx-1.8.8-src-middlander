package net.minecraft.util;

import java.util.List;

public class ChatComponentText extends ChatComponentStyle {
	private final String text;

	public ChatComponentText(String msg) {
		this.text = msg;
	}

	public String getChatComponentText_TextValue() {
		return this.text;
	}

	public String getUnformattedTextForChat() {
		return this.text;
	}

	public ChatComponentText createCopy() {
		ChatComponentText chatcomponenttext = new ChatComponentText(this.text);
		chatcomponenttext.setChatStyle(this.getChatStyle().createShallowCopy());

		List<IChatComponent> lst = this.getSiblings();
		for (int i = 0, l = lst.size(); i < l; ++i) {
			chatcomponenttext.appendSibling(lst.get(i).createCopy());
		}

		return chatcomponenttext;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof ChatComponentText)) {
			return false;
		} else {
			ChatComponentText chatcomponenttext = (ChatComponentText) object;
			return this.text.equals(chatcomponenttext.getChatComponentText_TextValue()) && super.equals(object);
		}
	}

	public String toString() {
		return "TextComponent{text=\'" + this.text + '\'' + ", siblings=" + this.siblings + ", style="
				+ this.getChatStyle() + '}';
	}
}
