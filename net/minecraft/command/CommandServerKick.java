package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.StringUtils;

public class CommandServerKick extends CommandBase {

	public String getCommandName() {
		return "kick";
	}

	public int getRequiredPermissionLevel() {
		return 3;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.kick.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length > 0 && parArrayOfString[0].length() > 1) {
			EntityPlayerMP entityplayermp = MinecraftServer.getServer().getConfigurationManager()
					.getPlayerByUsername(parArrayOfString[0]);
			String s = "Kicked by an operator.";
			boolean flag = false;
			if (entityplayermp == null) {
				throw new PlayerNotFoundException();
			} else {
				if (parArrayOfString.length >= 2) {
					s = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 1).getUnformattedText();
					if (MinecraftServer.getServer().worldServers[0].getWorldInfo().getGameRulesInstance()
							.getBoolean("colorCodes")) {
						s = StringUtils.translateControlCodesAlternate(s);
					}
					flag = true;
				}

				entityplayermp.playerNetServerHandler.kickPlayerFromServer(s);
				if (flag) {
					notifyOperators(parICommandSender, this, "commands.kick.success.reason",
							new Object[] { entityplayermp.getName(), s });
				} else {
					notifyOperators(parICommandSender, this, "commands.kick.success",
							new Object[] { entityplayermp.getName() });
				}

			}
		} else {
			throw new WrongUsageException("commands.kick.usage", new Object[0]);
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length >= 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: null;
	}
}
