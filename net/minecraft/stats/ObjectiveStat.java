package net.minecraft.stats;

import net.minecraft.scoreboard.ScoreDummyCriteria;

public class ObjectiveStat extends ScoreDummyCriteria {
	private final StatBase field_151459_g;

	public ObjectiveStat(StatBase parStatBase) {
		super(parStatBase.statId);
		this.field_151459_g = parStatBase;
	}
}
