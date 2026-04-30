package net.minecraft.scoreboard;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class ScoreHealthCriteria extends ScoreDummyCriteria {
	public ScoreHealthCriteria(String name) {
		super(name);
	}

	public int func_96635_a(List<EntityPlayer> list) {
		float f = 0.0F;

		for (int i = 0, l = list.size(); i < l; ++i) {
			EntityPlayer entityplayer = list.get(i);
			f += entityplayer.getHealth() + entityplayer.getAbsorptionAmount();
		}

		if (list.size() > 0) {
			f /= (float) list.size();
		}

		return MathHelper.ceiling_float_int(f);
	}

	public boolean isReadOnly() {
		return true;
	}

	public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
		return IScoreObjectiveCriteria.EnumRenderType.HEARTS;
	}
}
