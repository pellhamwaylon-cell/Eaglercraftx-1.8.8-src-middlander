package net.minecraft.stats;

import java.util.Map;

import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IJsonSerializable;
import net.minecraft.util.TupleIntJsonSerializable;

public class StatFileWriter {
	protected final Map<StatBase, TupleIntJsonSerializable> statsData = Maps.newHashMap();

	public boolean hasAchievementUnlocked(Achievement achievementIn) {
		return this.readStat(achievementIn) > 0;
	}

	public boolean canUnlockAchievement(Achievement achievementIn) {
		return achievementIn.parentAchievement == null || this.hasAchievementUnlocked(achievementIn.parentAchievement);
	}

	public int func_150874_c(Achievement parAchievement) {
		if (this.hasAchievementUnlocked(parAchievement)) {
			return 0;
		} else {
			int i = 0;

			for (Achievement achievement = parAchievement.parentAchievement; achievement != null
					&& !this.hasAchievementUnlocked(achievement); ++i) {
				achievement = achievement.parentAchievement;
			}

			return i;
		}
	}

	public void increaseStat(EntityPlayer player, StatBase stat, int amount) {
		if (!stat.isAchievement() || this.canUnlockAchievement((Achievement) stat)) {
			this.unlockAchievement(player, stat, this.readStat(stat) + amount);
		}
	}

	public void unlockAchievement(EntityPlayer var1, StatBase statbase, int i) {
		TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable) this.statsData.get(statbase);
		if (tupleintjsonserializable == null) {
			tupleintjsonserializable = new TupleIntJsonSerializable();
			this.statsData.put(statbase, tupleintjsonserializable);
		}

		tupleintjsonserializable.setIntegerValue(i);
	}

	public int readStat(StatBase stat) {
		TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable) this.statsData.get(stat);
		return tupleintjsonserializable == null ? 0 : tupleintjsonserializable.getIntegerValue();
	}

	public <T extends IJsonSerializable> T func_150870_b(StatBase parStatBase) {
		TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable) this.statsData.get(parStatBase);
		return (T) (tupleintjsonserializable != null ? tupleintjsonserializable.getJsonSerializableValue() : null);
	}

	public <T extends IJsonSerializable> T func_150872_a(StatBase parStatBase, T parIJsonSerializable) {
		TupleIntJsonSerializable tupleintjsonserializable = (TupleIntJsonSerializable) this.statsData.get(parStatBase);
		if (tupleintjsonserializable == null) {
			tupleintjsonserializable = new TupleIntJsonSerializable();
			this.statsData.put(parStatBase, tupleintjsonserializable);
		}

		tupleintjsonserializable.setJsonSerializableValue(parIJsonSerializable);
		return (T) parIJsonSerializable;
	}
}
