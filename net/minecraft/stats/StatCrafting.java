package net.minecraft.stats;

import net.minecraft.item.Item;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.util.IChatComponent;

public class StatCrafting extends StatBase {
	private final Item field_150960_a;

	public StatCrafting(String parString1, String parString2, IChatComponent statNameIn, Item parItem) {
		super(parString1 + parString2, statNameIn);
		this.field_150960_a = parItem;
		int i = Item.getIdFromItem(parItem);
		if (i != 0) {
			IScoreObjectiveCriteria.INSTANCES.put(parString1 + i, this.func_150952_k());
		}

	}

	public Item func_150959_a() {
		return this.field_150960_a;
	}
}
