package net.minecraft.client.settings;

import java.util.List;
import java.util.Set;

import com.carrotsearch.hppc.IntObjectHashMap;
import com.carrotsearch.hppc.IntObjectMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.client.resources.I18n;

public class KeyBinding implements Comparable<KeyBinding> {
	private static final List<KeyBinding> keybindArray = Lists.newArrayList();
	private static final IntObjectMap<KeyBinding> hash = new IntObjectHashMap<>();
	private static final Set<String> keybindSet = Sets.newHashSet();
	private final String keyDescription;
	private final int keyCodeDefault;
	private final String keyCategory;
	private int keyCode;
	private boolean pressed;
	private int pressTime;

	public static void onTick(int keyCode) {
		if (keyCode != 0) {
			KeyBinding keybinding = hash.get(keyCode);
			if (keybinding != null) {
				++keybinding.pressTime;
			}

		}
	}

	public static void setKeyBindState(int keyCode, boolean pressed) {
		if (keyCode != 0) {
			KeyBinding keybinding = hash.get(keyCode);
			if (keybinding != null) {
				keybinding.pressed = pressed;
			}

		}
	}

	public static void unPressAllKeys() {
		for (int i = 0, l = keybindArray.size(); i < l; ++i) {
			keybindArray.get(i).unpressKey();
		}

	}

	public static void resetKeyBindingArrayAndHash() {
		hash.clear();

		for (int i = 0, l = keybindArray.size(); i < l; ++i) {
			KeyBinding keybinding = keybindArray.get(i);
			hash.put(keybinding.keyCode, keybinding);
		}

	}

	public static Set<String> getKeybinds() {
		return keybindSet;
	}

	public KeyBinding(String description, int keyCode, String category) {
		this.keyDescription = description;
		this.keyCode = keyCode;
		this.keyCodeDefault = keyCode;
		this.keyCategory = category;
		keybindArray.add(this);
		hash.put(keyCode, this);
		keybindSet.add(category);
	}

	public boolean isKeyDown() {
		return this.pressed;
	}

	public String getKeyCategory() {
		return this.keyCategory;
	}

	public boolean isPressed() {
		if (this.pressTime == 0) {
			return false;
		} else {
			--this.pressTime;
			return true;
		}
	}

	private void unpressKey() {
		this.pressTime = 0;
		this.pressed = false;
	}

	public String getKeyDescription() {
		return this.keyDescription;
	}

	public int getKeyCodeDefault() {
		return this.keyCodeDefault;
	}

	public int getKeyCode() {
		return this.keyCode;
	}

	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}

	public int compareTo(KeyBinding keybinding) {
		int i = I18n.format(this.keyCategory, new Object[0])
				.compareTo(I18n.format(keybinding.keyCategory, new Object[0]));
		if (i == 0) {
			i = I18n.format(this.keyDescription, new Object[0])
					.compareTo(I18n.format(keybinding.keyDescription, new Object[0]));
		}

		return i;
	}
}
