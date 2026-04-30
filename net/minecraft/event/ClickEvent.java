package net.minecraft.event;

import java.util.Map;

import com.google.common.collect.Maps;

public class ClickEvent {
	private final ClickEvent.Action action;
	private final String value;

	public ClickEvent(ClickEvent.Action theAction, String theValue) {
		this.action = theAction;
		this.value = theValue;
	}

	public ClickEvent.Action getAction() {
		return this.action;
	}

	public String getValue() {
		return this.value;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (object != null && this.getClass() == object.getClass()) {
			ClickEvent clickevent = (ClickEvent) object;
			if (this.action != clickevent.action) {
				return false;
			} else {
				if (this.value != null) {
					if (!this.value.equals(clickevent.value)) {
						return false;
					}
				} else if (clickevent.value != null) {
					return false;
				}

				return true;
			}
		} else {
			return false;
		}
	}

	public String toString() {
		return "ClickEvent{action=" + this.action + ", value=\'" + this.value + '\'' + '}';
	}

	public int hashCode() {
		int i = this.action.hashCode();
		i = 31 * i + (this.value != null ? this.value.hashCode() : 0);
		return i;
	}

	public static enum Action {
		OPEN_URL("open_url", true), OPEN_FILE("open_file", false), RUN_COMMAND("run_command", true),
		TWITCH_USER_INFO("twitch_user_info", false), SUGGEST_COMMAND("suggest_command", true),
		CHANGE_PAGE("change_page", true);

		private static final Map<String, ClickEvent.Action> nameMapping = Maps.newHashMap();
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

		public static ClickEvent.Action getValueByCanonicalName(String canonicalNameIn) {
			return (ClickEvent.Action) nameMapping.get(canonicalNameIn);
		}

		static {
			ClickEvent.Action[] types = values();
			for (int i = 0; i < types.length; ++i) {
				nameMapping.put(types[i].getCanonicalName(), types[i]);
			}

		}
	}
}
