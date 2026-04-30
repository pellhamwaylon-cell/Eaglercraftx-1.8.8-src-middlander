package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandTestFor extends CommandBase {

	public String getCommandName() {
		return "testfor";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.testfor.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 1) {
			throw new WrongUsageException("commands.testfor.usage", new Object[0]);
		} else {
			Entity entity = func_175768_b(parICommandSender, parArrayOfString[0]);
			NBTTagCompound nbttagcompound = null;
			if (parArrayOfString.length >= 2) {
				try {
					nbttagcompound = JsonToNBT.getTagFromJson(buildString(parArrayOfString, 1));
				} catch (NBTException nbtexception) {
					throw new CommandException("commands.testfor.tagError", new Object[] { nbtexception.getMessage() });
				}
			}

			if (nbttagcompound != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				entity.writeToNBT(nbttagcompound1);
				if (!NBTUtil.func_181123_a(nbttagcompound, nbttagcompound1, true)) {
					throw new CommandException("commands.testfor.failure", new Object[] { entity.getName() });
				}
			}

			notifyOperators(parICommandSender, this, "commands.testfor.success", new Object[] { entity.getName() });
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
