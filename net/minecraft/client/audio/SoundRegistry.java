package net.minecraft.client.audio;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.util.RegistrySimple;
import net.minecraft.util.ResourceLocation;

public class SoundRegistry extends RegistrySimple<ResourceLocation, SoundEventAccessorComposite> {
	private Map<ResourceLocation, SoundEventAccessorComposite> soundRegistry;

	protected Map<ResourceLocation, SoundEventAccessorComposite> createUnderlyingMap() {
		this.soundRegistry = Maps.newHashMap();
		return this.soundRegistry;
	}

	public void registerSound(SoundEventAccessorComposite parSoundEventAccessorComposite) {
		this.putObject(parSoundEventAccessorComposite.getSoundEventLocation(), parSoundEventAccessorComposite);
	}

	public void clearMap() {
		this.soundRegistry.clear();
	}
}
