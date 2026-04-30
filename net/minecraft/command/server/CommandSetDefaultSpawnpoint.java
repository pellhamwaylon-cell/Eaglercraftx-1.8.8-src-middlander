package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandSetDefaultSpawnpoint extends CommandBase {

	public String getCommandName() {
		return "setworldspawn";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.setworldspawn.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		BlockPos blockpos;
		if (parArrayOfString.length == 0) {
			blockpos = getCommandSenderAsPlayer(parICommandSender).getPosition();
		} else {
			if (parArrayOfString.length != 3 || parICommandSender.getEntityWorld() == null) {
				throw new WrongUsageException("commands.setworldspawn.usage", new Object[0]);
			}

			blockpos = parseBlockPos(parICommandSender, parArrayOfString, 0, true);
		}

		parICommandSender.getEntityWorld().setSpawnPoint(blockpos);
		MinecraftServer.getServer().getConfigurationManager()
				.sendPacketToAllPlayers(new S05PacketSpawnPosition(blockpos));
		notifyOperators(parICommandSender, this, "commands.setworldspawn.success", new Object[] {
				Integer.valueOf(blockpos.getX()), Integer.valueOf(blockpos.getY()), Integer.valueOf(blockpos.getZ()) });
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length > 0 && astring.length <= 3 ? func_175771_a(astring, 0, blockpos) : null;
	}
}
