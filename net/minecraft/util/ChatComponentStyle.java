package net.minecraft.util;

import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public abstract class ChatComponentStyle implements IChatComponent {
	protected List<IChatComponent> siblings = Lists.newArrayList();
	private ChatStyle style;

	public IChatComponent appendSibling(IChatComponent component) {
		component.getChatStyle().setParentStyle(this.getChatStyle());
		this.siblings.add(component);
		return this;
	}

	public List<IChatComponent> getSiblings() {
		return this.siblings;
	}

	public IChatComponent appendText(String text) {
		return this.appendSibling(new ChatComponentText(text));
	}

	public IChatComponent setChatStyle(ChatStyle style) {
		this.style = style;

		for (int i = 0, l = this.siblings.size(); i < l; ++i) {
			this.siblings.get(i).getChatStyle().setParentStyle(this.getChatStyle());
		}

		return this;
	}

	public ChatStyle getChatStyle() {
		if (this.style == null) {
			this.style = new ChatStyle();

			for (int i = 0, l = this.siblings.size(); i < l; ++i) {
				this.siblings.get(i).getChatStyle().setParentStyle(this.style);
			}
		}

		return this.style;
	}

	public ChatStyle getChatStyleIfPresent() {
		return this.style;
	}

	public Iterator<IChatComponent> iterator() {
		return Iterators.concat(Iterators.forArray(new ChatComponentStyle[] { this }),
				createDeepCopyIterator(this.siblings));
	}

	public final String getUnformattedText() {
		StringBuilder stringbuilder = new StringBuilder();

		for (IChatComponent ichatcomponent : this) {
			stringbuilder.append(ichatcomponent.getUnformattedTextForChat());
		}

		return stringbuilder.toString();
	}

	public final String getFormattedText() {
		StringBuilder stringbuilder = new StringBuilder();

		for (IChatComponent ichatcomponent : this) {
			stringbuilder.append(ichatcomponent.getChatStyle().getFormattingCode());
			stringbuilder.append(ichatcomponent.getUnformattedTextForChat());
			stringbuilder.append(EnumChatFormatting.RESET);
		}

		return stringbuilder.toString();
	}

	public static Iterator<IChatComponent> createDeepCopyIterator(Iterable<IChatComponent> components) {
		Iterator iterator = Iterators.concat(
				Iterators.transform(components.iterator(), new Function<IChatComponent, Iterator<IChatComponent>>() {
					public Iterator<IChatComponent> apply(IChatComponent ichatcomponent) {
						return ichatcomponent.iterator();
					}
				}));
		iterator = Iterators.transform(iterator, new Function<IChatComponent, IChatComponent>() {
			public IChatComponent apply(IChatComponent ichatcomponent) {
				IChatComponent ichatcomponent1 = ichatcomponent.createCopy();
				ichatcomponent1.setChatStyle(ichatcomponent1.getChatStyle().createDeepCopy());
				return ichatcomponent1;
			}
		});
		return iterator;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof ChatComponentStyle)) {
			return false;
		} else {
			ChatComponentStyle chatcomponentstyle = (ChatComponentStyle) object;
			return this.siblings.equals(chatcomponentstyle.siblings)
					&& this.getChatStyle().equals(chatcomponentstyle.getChatStyle());
		}
	}

	public int hashCode() {
		return 31 * this.style.hashCode() + this.siblings.hashCode();
	}

	public String toString() {
		return "BaseComponent{style=" + this.style + ", siblings=" + this.siblings + '}';
	}
}
