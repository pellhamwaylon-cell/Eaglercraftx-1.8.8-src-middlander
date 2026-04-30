package net.minecraft.util;

import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.server.MinecraftServer;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.HString;

public class ChatComponentScore extends ChatComponentStyle {
	private final String name;
	private final String objective;
	private String value = "";

	public ChatComponentScore(String nameIn, String objectiveIn) {
		this.name = nameIn;
		this.objective = objectiveIn;
	}

	public String getName() {
		return this.name;
	}

	public String getObjective() {
		return this.objective;
	}

	public void setValue(String valueIn) {
		this.value = valueIn;
	}

	public String getUnformattedTextForChat() {
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		if (minecraftserver != null && StringUtils.isNullOrEmpty(this.value)) {
			Scoreboard scoreboard = minecraftserver.worldServerForDimension(0).getScoreboard();
			ScoreObjective scoreobjective = scoreboard.getObjective(this.objective);
			if (scoreboard.entityHasObjective(this.name, scoreobjective)) {
				Score score = scoreboard.getValueFromObjective(this.name, scoreobjective);
				this.setValue(HString.format("%d", new Object[] { Integer.valueOf(score.getScorePoints()) }));
			} else {
				this.value = "";
			}
		}

		return this.value;
	}

	public ChatComponentScore createCopy() {
		ChatComponentScore chatcomponentscore = new ChatComponentScore(this.name, this.objective);
		chatcomponentscore.setValue(this.value);
		chatcomponentscore.setChatStyle(this.getChatStyle().createShallowCopy());

		List<IChatComponent> lst = this.getSiblings();
		for (int i = 0, l = lst.size(); i < l; ++i) {
			chatcomponentscore.appendSibling(lst.get(i).createCopy());
		}

		return chatcomponentscore;
	}

	public boolean equals(Object object) {
		if (this == object) {
			return true;
		} else if (!(object instanceof ChatComponentScore)) {
			return false;
		} else {
			ChatComponentScore chatcomponentscore = (ChatComponentScore) object;
			return this.name.equals(chatcomponentscore.name) && this.objective.equals(chatcomponentscore.objective)
					&& super.equals(object);
		}
	}

	public String toString() {
		return "ScoreComponent{name=\'" + this.name + '\'' + "objective=\'" + this.objective + '\'' + ", siblings="
				+ this.siblings + ", style=" + this.getChatStyle() + '}';
	}
}
