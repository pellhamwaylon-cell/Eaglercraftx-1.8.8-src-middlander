package net.minecraft.command.server;

import java.util.List;

import org.json.JSONException;

import net.lax1dude.eaglercraft.v1_8.ExceptionUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentProcessor;
import net.minecraft.util.IChatComponent;

public class CommandMessageRaw extends CommandBase {

	public String getCommandName() {
		return "tellraw";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.tellraw.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 2) {
			throw new WrongUsageException("commands.tellraw.usage", new Object[0]);
		} else {
			EntityPlayerMP entityplayermp = getPlayer(parICommandSender, parArrayOfString[0]);
			String s = buildString(parArrayOfString, 1);

			try {
				IChatComponent ichatcomponent = IChatComponent.Serializer.jsonToComponent(s);
				entityplayermp.addChatMessage(
						ChatComponentProcessor.processComponent(parICommandSender, ichatcomponent, entityplayermp));
			} catch (JSONException jsonparseexception) {
				Throwable throwable = ExceptionUtils.getRootCause(jsonparseexception);
				throw new SyntaxErrorException("commands.tellraw.jsonException",
						new Object[] { throwable == null ? "" : throwable.getMessage() });
			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: null;
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}
}
