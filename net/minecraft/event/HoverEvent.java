package net.minecraft.event;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.util.IChatComponent;

public class HoverEvent {
	private final HoverEvent.Action action;
	private final IChatComponent value;

	public HoverEvent(HoverEvent.Action actionIn, IChatComponent valueIn) {
		this.action = actionIn;
		this.value = valueIn;
	}

	public HoverEvent.Action getAction() {
		return this.action;
	}

	public IChatComponent getValue() {
		return this.value;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object != null && this.getClass() == object.getClass()) {
			HoverEvent hoverevent = (HoverEvent) object;
			if (this.action != hoverevent.action) {
				return false;
			} else {
				if (this.value != null) {
					if (!this.value.equals(hoverevent.value)) {
						return false;
					}
				} else if (hoverevent.value != null) {
					return false;
				}

				return true;
			}
		} else {
			return false;
		}
	}

	public String toString() {
		return "HoverEvent{action=" + this.action + ", value=\'" + this.value + '\'' + '}';
	}

	public int hashCode() {
		int i = this.action.hashCode();
		i = 31 * i + (this.value != null ? this.value.hashCode() : 0);
		return i;
	}

	public static enum Action {
		SHOW_TEXT("show_text", true), SHOW_ACHIEVEMENT("show_achievement", true), SHOW_ITEM("show_item", true),
		SHOW_ENTITY("show_entity", true);

		private static final Map<String, HoverEvent.Action> nameMapping = Maps.newHashMap();
		private final boolean allowedInChat;
		private final String canonicalName;

		private Action(String canonicalNameIn, boolean allowedInChatIn) {
			this.canonicalName = canonicalNameIn;
			this.allowedInChat = allowedInChatIn;
		}

		public boolean shouldAllowInChat() {
			return this.allowedInChat;
		}

		public String getCanonicalName() {
			return this.canonicalName;
		}

		public static HoverEvent.Action getValueByCanonicalName(String canonicalNameIn) {
			return (HoverEvent.Action) nameMapping.get(canonicalNameIn);
		}

		static {
			HoverEvent.Action[] types = values();
			for (int i = 0; i < types.length; ++i) {
				nameMapping.put(types[i].getCanonicalName(), types[i]);
			}

		}
	}
}
