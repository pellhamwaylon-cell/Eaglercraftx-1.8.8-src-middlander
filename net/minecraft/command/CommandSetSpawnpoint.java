package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetSpawnpoint extends CommandBase {

	public String getCommandName() {
		return "spawnpoint";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.spawnpoint.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length > 1 && parArrayOfString.length < 4) {
			throw new WrongUsageException("commands.spawnpoint.usage", new Object[0]);
		} else {
			EntityPlayerMP entityplayermp = parArrayOfString.length > 0
					? getPlayer(parICommandSender, parArrayOfString[0])
					: getCommandSenderAsPlayer(parICommandSender);
			BlockPos blockpos = parArrayOfString.length > 3
					? parseBlockPos(parICommandSender, parArrayOfString, 1, true)
					: entityplayermp.getPosition();
			if (entityplayermp.worldObj != null) {
				entityplayermp.setSpawnPoint(blockpos, true);
				notifyOperators(parICommandSender, this, "commands.spawnpoint.success",
						new Object[] { entityplayermp.getName(), Integer.valueOf(blockpos.getX()),
								Integer.valueOf(blockpos.getY()), Integer.valueOf(blockpos.getZ()) });
			}

		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: (astring.length > 1 && astring.length <= 4 ? func_175771_a(astring, 1, blockpos) : null);
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}
}
