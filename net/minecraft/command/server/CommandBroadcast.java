package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StringUtils;

public class CommandBroadcast extends CommandBase {

	public String getCommandName() {
		return "say";
	}

	public int getRequiredPermissionLevel() {
		return 1;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.say.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length > 0 && parArrayOfString[0].length() > 0) {
			IChatComponent ichatcomponent = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 0, true);
			if (MinecraftServer.getServer().worldServers[0].getWorldInfo().getGameRulesInstance()
					.getBoolean("colorCodes")) {
				ichatcomponent = new ChatComponentText(
						StringUtils.translateControlCodesAlternate(ichatcomponent.getFormattedText()));
			}
			MinecraftServer.getServer().getConfigurationManager().sendChatMsg(new ChatComponentTranslation(
					"chat.type.announcement", new Object[] { parICommandSender.getDisplayName(), ichatcomponent }));
		} else {
			throw new WrongUsageException("commands.say.usage", new Object[0]);
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length >= 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: null;
	}
}
