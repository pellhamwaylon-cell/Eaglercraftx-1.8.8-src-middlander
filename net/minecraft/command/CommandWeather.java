package net.minecraft.command;

import java.util.List;

import net.lax1dude.eaglercraft.v1_8.ThreadLocalRandom;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.WorldInfo;

public class CommandWeather extends CommandBase {

	public String getCommandName() {
		return "weather";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.weather.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length >= 1 && parArrayOfString.length <= 2) {
			int i = (300 + ThreadLocalRandom.current().nextInt(600)) * 20 * 2;
			if (parArrayOfString.length >= 2) {
				i = parseInt(parArrayOfString[1], 1, 1000000) * 20;
			}

			WorldServer worldserver = MinecraftServer.getServer().worldServers[0];
			WorldInfo worldinfo = worldserver.getWorldInfo();
			if ("clear".equalsIgnoreCase(parArrayOfString[0])) {
				worldinfo.setCleanWeatherTime(i);
				worldinfo.setRainTime(0);
				worldinfo.setThunderTime(0);
				worldinfo.setRaining(false);
				worldinfo.setThundering(false);
				notifyOperators(parICommandSender, this, "commands.weather.clear", new Object[0]);
			} else if ("rain".equalsIgnoreCase(parArrayOfString[0])) {
				worldinfo.setCleanWeatherTime(0);
				worldinfo.setRainTime(i);
				worldinfo.setThunderTime(i);
				worldinfo.setRaining(true);
				worldinfo.setThundering(false);
				notifyOperators(parICommandSender, this, "commands.weather.rain", new Object[0]);
			} else {
				if (!"thunder".equalsIgnoreCase(parArrayOfString[0])) {
					throw new WrongUsageException("commands.weather.usage", new Object[0]);
				}

				worldinfo.setCleanWeatherTime(0);
				worldinfo.setRainTime(i);
				worldinfo.setThunderTime(i);
				worldinfo.setRaining(true);
				worldinfo.setThundering(true);
				notifyOperators(parICommandSender, this, "commands.weather.thunder", new Object[0]);
			}

		} else {
			throw new WrongUsageException("commands.weather.usage", new Object[0]);
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, new String[] { "clear", "rain", "thunder" })
				: null;
	}
}
