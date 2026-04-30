package net.minecraft.util;

import java.util.List;

public class ChatComponentSelector extends ChatComponentStyle {
	private final String selector;

	public ChatComponentSelector(String selectorIn) {
		this.selector = selectorIn;
	}

	public String getSelector() {
		return this.selector;
	}

	public String getUnformattedTextForChat() {
		return this.selector;
	}

	public ChatComponentSelector createCopy() {
		ChatComponentSelector chatcomponentselector = new ChatComponentSelector(this.selector);
		chatcomponentselector.setChatStyle(this.getChatStyle().createShallowCopy());

		List<IChatComponent> lst = this.getSiblings();
		for (int i = 0, l = lst.size(); i < l; ++i) {
			chatcomponentselector.appendSibling(lst.get(i).createCopy());
		}

		return chatcomponentselector;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof ChatComponentSelector)) {
			return false;
		} else {
			ChatComponentSelector chatcomponentselector = (ChatComponentSelector) object;
			return this.selector.equals(chatcomponentselector.selector) && super.equals(object);
		}
	}

	public String toString() {
		return "SelectorComponent{pattern=\'" + this.selector + '\'' + ", siblings=" + this.siblings + ", style="
				+ this.getChatStyle() + '}';
	}
}
