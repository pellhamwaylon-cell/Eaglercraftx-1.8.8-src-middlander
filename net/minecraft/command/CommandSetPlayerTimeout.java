package net.minecraft.command;

import net.minecraft.server.MinecraftServer;

public class CommandSetPlayerTimeout extends CommandBase {

	public String getCommandName() {
		return "setidletimeout";
	}

	public int getRequiredPermissionLevel() {
		return 3;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.setidletimeout.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length != 1) {
			throw new WrongUsageException("commands.setidletimeout.usage", new Object[0]);
		} else {
			int i = parseInt(parArrayOfString[0], 0);
			MinecraftServer.getServer().setPlayerIdleTimeout(i);
			notifyOperators(parICommandSender, this, "commands.setidletimeout.success",
					new Object[] { Integer.valueOf(i) });
		}
	}
}
