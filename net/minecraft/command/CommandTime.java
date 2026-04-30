package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;

public class CommandTime extends CommandBase {

	public String getCommandName() {
		return "time";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.time.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length > 1) {
			if (parArrayOfString[0].equals("set")) {
				int l;
				if (parArrayOfString[1].equals("day")) {
					l = 1000;
				} else if (parArrayOfString[1].equals("night")) {
					l = 13000;
				} else {
					l = parseInt(parArrayOfString[1], 0);
				}

				this.setTime(parICommandSender, l);
				notifyOperators(parICommandSender, this, "commands.time.set", new Object[] { Integer.valueOf(l) });
				return;
			}

			if (parArrayOfString[0].equals("add")) {
				int k = parseInt(parArrayOfString[1], 0);
				this.addTime(parICommandSender, k);
				notifyOperators(parICommandSender, this, "commands.time.added", new Object[] { Integer.valueOf(k) });
				return;
			}

			if (parArrayOfString[0].equals("query")) {
				if (parArrayOfString[1].equals("daytime")) {
					int j = (int) (parICommandSender.getEntityWorld().getWorldTime() % 2147483647L);
					parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, j);
					notifyOperators(parICommandSender, this, "commands.time.query",
							new Object[] { Integer.valueOf(j) });
					return;
				}

				if (parArrayOfString[1].equals("gametime")) {
					int i = (int) (parICommandSender.getEntityWorld().getTotalWorldTime() % 2147483647L);
					parICommandSender.setCommandStat(CommandResultStats.Type.QUERY_RESULT, i);
					notifyOperators(parICommandSender, this, "commands.time.query",
							new Object[] { Integer.valueOf(i) });
					return;
				}
			}
		}

		throw new WrongUsageException("commands.time.usage", new Object[0]);
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, new String[] { "set", "add", "query" })
				: (astring.length == 2 && astring[0].equals("set")
						? getListOfStringsMatchingLastWord(astring, new String[] { "day", "night" })
						: (astring.length == 2 && astring[0].equals("query")
								? getListOfStringsMatchingLastWord(astring, new String[] { "daytime", "gametime" })
								: null));
	}

	protected void setTime(ICommandSender parICommandSender, int parInt1) {
		for (int i = 0; i < MinecraftServer.getServer().worldServers.length; ++i) {
			MinecraftServer.getServer().worldServers[i].setWorldTime((long) parInt1);
		}

	}

	protected void addTime(ICommandSender parICommandSender, int parInt1) {
		for (int i = 0; i < MinecraftServer.getServer().worldServers.length; ++i) {
			WorldServer worldserver = MinecraftServer.getServer().worldServers[i];
			worldserver.setWorldTime(worldserver.getWorldTime() + (long) parInt1);
		}

	}
}
