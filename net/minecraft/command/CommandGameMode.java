package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.WorldSettings;

public class CommandGameMode extends CommandBase {

	public String getCommandName() {
		return "gamemode";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.gamemode.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length <= 0) {
			throw new WrongUsageException("commands.gamemode.usage", new Object[0]);
		} else {
			WorldSettings.GameType worldsettings$gametype = this.getGameModeFromCommand(parICommandSender,
					parArrayOfString[0]);
			EntityPlayerMP entityplayermp = parArrayOfString.length >= 2
					? getPlayer(parICommandSender, parArrayOfString[1])
					: getCommandSenderAsPlayer(parICommandSender);
			entityplayermp.setGameType(worldsettings$gametype);
			entityplayermp.fallDistance = 0.0F;
			if (parICommandSender.getEntityWorld().getGameRules().getBoolean("sendCommandFeedback")) {
				entityplayermp.addChatMessage(new ChatComponentTranslation("gameMode.changed", new Object[0]));
			}

			ChatComponentTranslation chatcomponenttranslation = new ChatComponentTranslation(
					"gameMode." + worldsettings$gametype.getName(), new Object[0]);
			if (entityplayermp != parICommandSender) {
				notifyOperators(parICommandSender, this, 1, "commands.gamemode.success.other",
						new Object[] { entityplayermp.getName(), chatcomponenttranslation });
			} else {
				notifyOperators(parICommandSender, this, 1, "commands.gamemode.success.self",
						new Object[] { chatcomponenttranslation });
			}

		}
	}

	protected WorldSettings.GameType getGameModeFromCommand(ICommandSender parICommandSender, String parString1)
			throws NumberInvalidException {
		return !parString1.equalsIgnoreCase(WorldSettings.GameType.SURVIVAL.getName())
				&& !parString1.equalsIgnoreCase("s")
						? (!parString1.equalsIgnoreCase(WorldSettings.GameType.CREATIVE.getName())
								&& !parString1.equalsIgnoreCase("c")
										? (!parString1.equalsIgnoreCase(WorldSettings.GameType.ADVENTURE.getName())
												&& !parString1.equalsIgnoreCase("a")
														? (!parString1.equalsIgnoreCase(
																WorldSettings.GameType.SPECTATOR.getName())
																&& !parString1.equalsIgnoreCase("sp")
																		? WorldSettings.getGameTypeById(parseInt(
																				parString1, 0,
																				WorldSettings.GameType._VALUES.length
																						- 2))
																		: WorldSettings.GameType.SPECTATOR)
														: WorldSettings.GameType.ADVENTURE)
										: WorldSettings.GameType.CREATIVE)
						: WorldSettings.GameType.SURVIVAL;
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring,
						new String[] { "survival", "creative", "adventure", "spectator" })
				: (astring.length == 2 ? getListOfStringsMatchingLastWord(astring, this.getListOfPlayerUsernames())
						: null);
	}

	protected String[] getListOfPlayerUsernames() {
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 1;
	}
}
