package net.minecraft.command.server;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandResultStats;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class CommandSetBlock extends CommandBase {

	public String getCommandName() {
		return "setblock";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.setblock.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 4) {
			throw new WrongUsageException("commands.setblock.usage", new Object[0]);
		} else {
			parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 0);
			BlockPos blockpos = parseBlockPos(parICommandSender, parArrayOfString, 0, false);
			Block block = CommandBase.getBlockByText(parICommandSender, parArrayOfString[3]);
			int i = 0;
			if (parArrayOfString.length >= 5) {
				i = parseInt(parArrayOfString[4], 0, 15);
			}

			World world = parICommandSender.getEntityWorld();
			if (!world.isBlockLoaded(blockpos)) {
				throw new CommandException("commands.setblock.outOfWorld", new Object[0]);
			} else {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				boolean flag = false;
				if (parArrayOfString.length >= 7 && block.hasTileEntity()) {
					String s = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 6).getUnformattedText();

					try {
						nbttagcompound = JsonToNBT.getTagFromJson(s);
						flag = true;
					} catch (NBTException nbtexception) {
						throw new CommandException("commands.setblock.tagError",
								new Object[] { nbtexception.getMessage() });
					}
				}

				if (parArrayOfString.length >= 6) {
					if (parArrayOfString[5].equals("destroy")) {
						world.destroyBlock(blockpos, true);
						if (block == Blocks.air) {
							notifyOperators(parICommandSender, this, "commands.setblock.success", new Object[0]);
							return;
						}
					} else if (parArrayOfString[5].equals("keep") && !world.isAirBlock(blockpos)) {
						throw new CommandException("commands.setblock.noChange", new Object[0]);
					}
				}

				TileEntity tileentity1 = world.getTileEntity(blockpos);
				if (tileentity1 != null) {
					if (tileentity1 instanceof IInventory) {
						((IInventory) tileentity1).clear();
					}

					world.setBlockState(blockpos, Blocks.air.getDefaultState(), block == Blocks.air ? 2 : 4);
				}

				IBlockState iblockstate = block.getStateFromMeta(i);
				if (!world.setBlockState(blockpos, iblockstate, 2)) {
					throw new CommandException("commands.setblock.noChange", new Object[0]);
				} else {
					if (flag) {
						TileEntity tileentity = world.getTileEntity(blockpos);
						if (tileentity != null) {
							nbttagcompound.setInteger("x", blockpos.getX());
							nbttagcompound.setInteger("y", blockpos.getY());
							nbttagcompound.setInteger("z", blockpos.getZ());
							tileentity.readFromNBT(nbttagcompound);
						}
					}

					world.notifyNeighborsRespectDebug(blockpos, iblockstate.getBlock());
					parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_BLOCKS, 1);
					notifyOperators(parICommandSender, this, "commands.setblock.success", new Object[0]);
				}
			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length > 0 && astring.length <= 3 ? func_175771_a(astring, 0, blockpos)
				: (astring.length == 4 ? getListOfStringsMatchingLastWord(astring, Block.blockRegistry.getKeys())
						: (astring.length == 6
								? getListOfStringsMatchingLastWord(astring,
										new String[] { "replace", "destroy", "keep" })
								: null));
	}
}
