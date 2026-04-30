package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;

public class CommandClearInventory extends CommandBase {

	public String getCommandName() {
		return "clear";
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.clear.usage";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		EntityPlayerMP entityplayermp = parArrayOfString.length == 0 ? getCommandSenderAsPlayer(parICommandSender)
				: getPlayer(parICommandSender, parArrayOfString[0]);
		Item item = parArrayOfString.length >= 2 ? getItemByText(parICommandSender, parArrayOfString[1]) : null;
		int i = parArrayOfString.length >= 3 ? parseInt(parArrayOfString[2], -1) : -1;
		int j = parArrayOfString.length >= 4 ? parseInt(parArrayOfString[3], -1) : -1;
		NBTTagCompound nbttagcompound = null;
		if (parArrayOfString.length >= 5) {
			try {
				nbttagcompound = JsonToNBT.getTagFromJson(buildString(parArrayOfString, 4));
			} catch (NBTException nbtexception) {
				throw new CommandException("commands.clear.tagError", new Object[] { nbtexception.getMessage() });
			}
		}

		if (parArrayOfString.length >= 2 && item == null) {
			throw new CommandException("commands.clear.failure", new Object[] { entityplayermp.getName() });
		} else {
			int k = entityplayermp.inventory.clearMatchingItems(item, i, j, nbttagcompound);
			entityplayermp.inventoryContainer.detectAndSendChanges();
			if (!entityplayermp.capabilities.isCreativeMode) {
				entityplayermp.updateHeldItem();
			}

			parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, k);
			if (k == 0) {
				throw new CommandException("commands.clear.failure", new Object[] { entityplayermp.getName() });
			} else {
				if (j == 0) {
					parICommandSender.addChatMessage(new ChatComponentTranslation("commands.clear.testing",
							new Object[] { entityplayermp.getName(), Integer.valueOf(k) }));
				} else {
					notifyOperators(parICommandSender, this, "commands.clear.success",
							new Object[] { entityplayermp.getName(), Integer.valueOf(k) });
				}

			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, this.func_147209_d())
				: (astring.length == 2 ? getListOfStringsMatchingLastWord(astring, Item.itemRegistry.getKeys()) : null);
	}

	protected String[] func_147209_d() {
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}
}
