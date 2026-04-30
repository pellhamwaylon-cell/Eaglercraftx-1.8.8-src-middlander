package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandKill extends CommandBase {

	public String getCommandName() {
		return "kill";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.kill.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length == 0) {
			EntityPlayerMP entityplayermp = getCommandSenderAsPlayer(parICommandSender);
			entityplayermp.onKillCommand();
			notifyOperators(parICommandSender, this, "commands.kill.successful",
					new Object[] { entityplayermp.getDisplayName() });
		} else {
			Entity entity = func_175768_b(parICommandSender, parArrayOfString[0]);
			entity.onKillCommand();
			notifyOperators(parICommandSender, this, "commands.kill.successful",
					new Object[] { entity.getDisplayName() });
		}
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: null;
	}
}
