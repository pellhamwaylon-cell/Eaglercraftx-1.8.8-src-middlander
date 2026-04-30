package net.minecraft.command;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.World;

public class CommandShowSeed extends CommandBase {

	public boolean canCommandSenderUseCommand(ICommandSender icommandsender) {
		return MinecraftServer.getServer().isSinglePlayer() || super.canCommandSenderUseCommand(icommandsender);
	}

	public String getCommandName() {
		return "seed";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.seed.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		Object object = parICommandSender instanceof EntityPlayer ? ((EntityPlayer) parICommandSender).worldObj
				: MinecraftServer.getServer().worldServerForDimension(0);
		parICommandSender.addChatMessage(new ChatComponentTranslation("commands.seed.success",
				new Object[] { Long.valueOf(((World) object).getSeed()) }));
	}
}
