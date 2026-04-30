package net.minecraft.command;

import java.util.List;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandDefaultGameMode extends CommandGameMode {

	public String getCommandName() {
		return "defaultgamemode";
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.defaultgamemode.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length <= 0) {
			throw new WrongUsageException("commands.defaultgamemode.usage", new Object[0]);
		} else {
			WorldSettings.GameType worldsettings$gametype = this.getGameModeFromCommand(parICommandSender,
					parArrayOfString[0]);
			this.setGameType(worldsettings$gametype);
			notifyOperators(parICommandSender, this, "commands.defaultgamemode.success", new Object[] {
					new ChatComponentTranslation("gameMode." + worldsettings$gametype.getName(), new Object[0]) });
		}
	}

	protected void setGameType(WorldSettings.GameType parGameType) {
		MinecraftServer minecraftserver = MinecraftServer.getServer();
		minecraftserver.setGameType(parGameType);
		if (minecraftserver.getForceGamemode()) {
			List<EntityPlayerMP> lst = MinecraftServer.getServer().getConfigurationManager().func_181057_v();
			for (int i = 0, l = lst.size(); i < l; ++i) {
				EntityPlayerMP entityplayermp = lst.get(i);
				entityplayermp.setGameType(parGameType);
				entityplayermp.fallDistance = 0.0F;
			}
		}

	}
}
