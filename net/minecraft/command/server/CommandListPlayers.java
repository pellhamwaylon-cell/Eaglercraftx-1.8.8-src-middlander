package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;

public class CommandListPlayers extends CommandBase {

	public String getCommandName() {
		return "list";
	}

	public int getRequiredPermissionLevel() {
		return 0;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.players.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		int i = MinecraftServer.getServer().getCurrentPlayerCount();
		parICommandSender.addChatMessage(new ChatComponentTranslation("commands.players.list",
				new Object[] { Integer.valueOf(i), Integer.valueOf(MinecraftServer.getServer().getMaxPlayers()) }));
		parICommandSender.addChatMessage(new ChatComponentText(MinecraftServer.getServer().getConfigurationManager()
				.func_181058_b(parArrayOfString.length > 0 && "uuids".equalsIgnoreCase(parArrayOfString[0]))));
		parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, i);
	}
}
