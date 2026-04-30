package net.minecraft.command;

import java.util.List;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandBlockData extends CommandBase {

	public String getCommandName() {
		return "blockdata";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.blockdata.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 4) {
			throw new WrongUsageException("commands.blockdata.usage", new Object[0]);
		} else {
			parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
			BlockPos blockpos = parseBlockPos(parICommandSender, parArrayOfString, 0, false);
			World world = parICommandSender.getEntityWorld();
			if (!world.isBlockLoaded(blockpos)) {
				throw new CommandException("commands.blockdata.outOfWorld", new Object[0]);
			} else {
				TileEntity tileentity = world.getTileEntity(blockpos);
				if (tileentity == null) {
					throw new CommandException("commands.blockdata.notValid", new Object[0]);
				} else {
					NBTTagCompound nbttagcompound = new NBTTagCompound();
					tileentity.writeToNBT(nbttagcompound);
					NBTTagCompound nbttagcompound1 = (NBTTagCompound) nbttagcompound.copy();

					NBTTagCompound nbttagcompound2;
					try {
						nbttagcompound2 = JsonToNBT
								.getTagFromJson(getChatComponentFromNthArg(parICommandSender, parArrayOfString, 3)
										.getUnformattedText());
					} catch (NBTException nbtexception) {
						throw new CommandException("commands.blockdata.tagError",
								new Object[] { nbtexception.getMessage() });
					}

					nbttagcompound.merge(nbttagcompound2);
					nbttagcompound.setInteger("x", blockpos.getX());
					nbttagcompound.setInteger("y", blockpos.getY());
					nbttagcompound.setInteger("z", blockpos.getZ());
					if (nbttagcompound.equals(nbttagcompound1)) {
						throw new CommandException("commands.blockdata.failed",
								new Object[] { nbttagcompound.toString() });
					} else {
						tileentity.readFromNBT(nbttagcompound);
						tileentity.markDirty();
						world.markBlockForUpdate(blockpos);
						parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
						notifyOperators(parICommandSender, this, "commands.blockdata.success",
								new Object[] { nbttagcompound.toString() });
					}
				}
			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length > 0 && astring.length <= 3 ? func_175771_a(astring, 0, blockpos) : null;
	}
}
