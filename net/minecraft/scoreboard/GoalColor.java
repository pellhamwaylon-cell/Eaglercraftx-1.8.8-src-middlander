package net.minecraft.scoreboard;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;

public class GoalColor implements IScoreObjectiveCriteria {
	private final String goalName;

	public GoalColor(String parString1, EnumChatFormatting parEnumChatFormatting) {
		this.goalName = parString1 + parEnumChatFormatting.getFriendlyName();
		IScoreObjectiveCriteria.INSTANCES.put(this.goalName, this);
	}

	public String getName() {
		return this.goalName;
	}

	public int func_96635_a(List<EntityPlayer> parList) {
		return 0;
	}

	public boolean isReadOnly() {
		return false;
	}

	public IScoreObjectiveCriteria.EnumRenderType getRenderType() {
		return IScoreObjectiveCriteria.EnumRenderType.INTEGER;
	}
}
