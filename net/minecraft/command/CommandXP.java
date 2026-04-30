package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandXP extends CommandBase {

	public String getCommandName() {
		return "xp";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.xp.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length <= 0) {
			throw new WrongUsageException("commands.xp.usage", new Object[0]);
		} else {
			String s = parArrayOfString[0];
			boolean flag = s.endsWith("l") || s.endsWith("L");
			if (flag && s.length() > 1) {
				s = s.substring(0, s.length() - 1);
			}

			int i = parseInt(s);
			boolean flag1 = i < 0;
			if (flag1) {
				i *= -1;
			}

			EntityPlayerMP entityplayermp = parArrayOfString.length > 1
					? getPlayer(parICommandSender, parArrayOfString[1])
					: getCommandSenderAsPlayer(parICommandSender);
			if (flag) {
				parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, entityplayermp.experienceLevel);
				if (flag1) {
					entityplayermp.addExperienceLevel(-i);
					notifyOperators(parICommandSender, this, "commands.xp.success.negative.levels",
							new Object[] { Integer.valueOf(i), entityplayermp.getName() });
				} else {
					entityplayermp.addExperienceLevel(i);
					notifyOperators(parICommandSender, this, "commands.xp.success.levels",
							new Object[] { Integer.valueOf(i), entityplayermp.getName() });
				}
			} else {
				parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, entityplayermp.experienceTotal);
				if (flag1) {
					throw new CommandException("commands.xp.failure.widthdrawXp", new Object[0]);
				}

				entityplayermp.addExperience(i);
				notifyOperators(parICommandSender, this, "commands.xp.success",
						new Object[] { Integer.valueOf(i), entityplayermp.getName() });
			}

		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 2 ? getListOfStringsMatchingLastWord(astring, this.getAllUsernames()) : null;
	}

	protected String[] getAllUsernames() {
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 1;
	}
}
