package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;

public class CommandGive extends CommandBase {

	public String getCommandName() {
		return "give";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.give.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 2) {
			throw new WrongUsageException("commands.give.usage", new Object[0]);
		} else {
			EntityPlayerMP entityplayermp = getPlayer(parICommandSender, parArrayOfString[0]);
			Item item = getItemByText(parICommandSender, parArrayOfString[1]);
			int i = parArrayOfString.length >= 3 ? parseInt(parArrayOfString[2], 1, 64) : 1;
			int j = parArrayOfString.length >= 4 ? parseInt(parArrayOfString[3]) : 0;
			ItemStack itemstack = new ItemStack(item, i, j);
			if (parArrayOfString.length >= 5) {
				String s = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 4).getUnformattedText();

				try {
					itemstack.setTagCompound(JsonToNBT.getTagFromJson(s));
				} catch (NBTException nbtexception) {
					throw new CommandException("commands.give.tagError", new Object[] { nbtexception.getMessage() });
				}
			}

			boolean flag = entityplayermp.inventory.addItemStackToInventory(itemstack);
			if (flag) {
				entityplayermp.worldObj.playSoundAtEntity(entityplayermp, "random.pop", 0.2F,
						((entityplayermp.getRNG().nextFloat() - entityplayermp.getRNG().nextFloat()) * 0.7F + 1.0F)
								* 2.0F);
				entityplayermp.inventoryContainer.detectAndSendChanges();
			}

			if (flag && itemstack.stackSize <= 0) {
				itemstack.stackSize = 1;
				parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, i);
				EntityItem entityitem1 = entityplayermp.dropPlayerItemWithRandomChoice(itemstack, false);
				if (entityitem1 != null) {
					entityitem1.func_174870_v();
				}
			} else {
				parICommandSender.setCommandStat(CommandResultStats.Type.AFFECTED_ITEMS, i - itemstack.stackSize);
				EntityItem entityitem = entityplayermp.dropPlayerItemWithRandomChoice(itemstack, false);
				if (entityitem != null) {
					entityitem.setNoPickupDelay();
					entityitem.setOwner(entityplayermp.getName());
				}
			}

			notifyOperators(parICommandSender, this, "commands.give.success",
					new Object[] { itemstack.getChatComponent(), Integer.valueOf(i), entityplayermp.getName() });
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, this.getPlayers())
				: (astring.length == 2 ? getListOfStringsMatchingLastWord(astring, Item.itemRegistry.getKeys()) : null);
	}

	protected String[] getPlayers() {
		return MinecraftServer.getServer().getAllUsernames();
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}
}
