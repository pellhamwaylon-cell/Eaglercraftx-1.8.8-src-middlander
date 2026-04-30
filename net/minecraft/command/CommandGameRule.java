package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S19PacketEntityStatus;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules;

public class CommandGameRule extends CommandBase {

	public String getCommandName() {
		return "gamerule";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.gamerule.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		GameRules gamerules = this.getGameRules();
		String s = parArrayOfString.length > 0 ? parArrayOfString[0] : "";
		String s1 = parArrayOfString.length > 1 ? buildString(parArrayOfString, 1) : "";
		switch (parArrayOfString.length) {
		case 0:
			parICommandSender.addChatMessage(new ChatComponentText(joinNiceString(gamerules.getRules())));
			break;
		case 1:
			if (!gamerules.hasRule(s)) {
				throw new CommandException("commands.gamerule.norule", new Object[] { s });
			}

			String s2 = gamerules.getString(s);
			parICommandSender.addChatMessage((new ChatComponentText(s)).appendText(" = ").appendText(s2));
			parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, gamerules.getInt(s));
			break;
		default:
			if (gamerules.areSameType(s, GameRules.ValueType.BOOLEAN_VALUE) && !"true".equals(s1)
					&& !"false".equals(s1)) {
				throw new CommandException("commands.generic.boolean.invalid", new Object[] { s1 });
			}

			gamerules.setOrCreateGameRule(s, s1);
			func_175773_a(gamerules, s);
			notifyOperators(parICommandSender, this, "commands.gamerule.success", new Object[0]);
		}

	}

	public static void func_175773_a(GameRules parGameRules, String parString1) {
		if ("reducedDebugInfo".equals(parString1)) {
			int i = parGameRules.getBoolean(parString1) ? 22 : 23;

			List<EntityPlayerMP> lst = MinecraftServer.getServer().getConfigurationManager().func_181057_v();
			for (int j = 0, l = lst.size(); j < l; ++j) {
				EntityPlayerMP entityplayermp = lst.get(j);
				entityplayermp.playerNetServerHandler.sendPacket(new S19PacketEntityStatus(entityplayermp, (byte) i));
			}
		}

	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		if (astring.length == 1) {
			return getListOfStringsMatchingLastWord(astring, this.getGameRules().getRules());
		} else {
			if (astring.length == 2) {
				GameRules gamerules = this.getGameRules();
				if (gamerules.areSameType(astring[0], GameRules.ValueType.BOOLEAN_VALUE)) {
					return getListOfStringsMatchingLastWord(astring, new String[] { "true", "false" });
				}
			}

			return null;
		}
	}

	private GameRules getGameRules() {
		return MinecraftServer.getServer().worldServerForDimension(0).getGameRules();
	}
}
