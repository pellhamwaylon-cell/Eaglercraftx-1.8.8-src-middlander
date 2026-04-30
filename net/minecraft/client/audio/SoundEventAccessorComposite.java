package net.minecraft.client.audio;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import com.google.common.collect.Lists;

import net.minecraft.util.ResourceLocation;

public class SoundEventAccessorComposite implements ISoundEventAccessor<SoundPoolEntry> {
	private final List<ISoundEventAccessor<SoundPoolEntry>> soundPool = Lists.newArrayList();
	private final EaglercraftRandom rnd = new EaglercraftRandom();
	private final ResourceLocation soundLocation;
	private final SoundCategory category;
	private double eventPitch;
	private double eventVolume;

	public SoundEventAccessorComposite(ResourceLocation soundLocation, double pitch, double volume,
			SoundCategory category) {
		this.soundLocation = soundLocation;
		this.eventVolume = volume;
		this.eventPitch = pitch;
		this.category = category;
	}

	public int getWeight() {
		int i = 0;

		for (int j = 0, l = this.soundPool.size(); j < l; ++j) {
			i += this.soundPool.get(j).getWeight();
		}

		return i;
	}

	public SoundPoolEntry cloneEntry() {
		int i = this.getWeight();
		if (!this.soundPool.isEmpty() && i != 0) {
			int j = this.rnd.nextInt(i);

			for (int k = 0, l = this.soundPool.size(); k < l; ++k) {
				ISoundEventAccessor isoundeventaccessor = this.soundPool.get(k);
				j -= isoundeventaccessor.getWeight();
				if (j < 0) {
					SoundPoolEntry soundpoolentry = (SoundPoolEntry) isoundeventaccessor.cloneEntry();
					soundpoolentry.setPitch(soundpoolentry.getPitch() * this.eventPitch);
					soundpoolentry.setVolume(soundpoolentry.getVolume() * this.eventVolume);
					return soundpoolentry;
				}
			}

			return SoundHandler.missing_sound;
		} else {
			return SoundHandler.missing_sound;
		}
	}

	public void addSoundToEventPool(ISoundEventAccessor<SoundPoolEntry> sound) {
		this.soundPool.add(sound);
	}

	public ResourceLocation getSoundEventLocation() {
		return this.soundLocation;
	}

	public SoundCategory getSoundCategory() {
		return this.category;
	}
}
