package net.minecraft.command;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandExecuteAt extends CommandBase {

	public String getCommandName() {
		return "execute";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.execute.usage";
	}

	public void processCommand(final ICommandSender parICommandSender, String[] parArrayOfString)
			throws CommandException {
		if (parArrayOfString.length < 5) {
			throw new WrongUsageException("commands.execute.usage", new Object[0]);
		} else {
			final Entity entity = getEntity(parICommandSender, parArrayOfString[0], Entity.class);
			final double d0 = parseDouble(entity.posX, parArrayOfString[1], false);
			final double d1 = parseDouble(entity.posY, parArrayOfString[2], false);
			final double d2 = parseDouble(entity.posZ, parArrayOfString[3], false);
			final BlockPos blockpos = new BlockPos(d0, d1, d2);
			byte b0 = 4;
			if ("detect".equals(parArrayOfString[4]) && parArrayOfString.length > 10) {
				World world = entity.getEntityWorld();
				double d3 = parseDouble(d0, parArrayOfString[5], false);
				double d4 = parseDouble(d1, parArrayOfString[6], false);
				double d5 = parseDouble(d2, parArrayOfString[7], false);
				Block block = getBlockByText(parICommandSender, parArrayOfString[8]);
				int j = parseInt(parArrayOfString[9], -1, 15);
				BlockPos blockpos1 = new BlockPos(d3, d4, d5);
				IBlockState iblockstate = world.getBlockState(blockpos1);
				if (iblockstate.getBlock() != block
						|| j >= 0 && iblockstate.getBlock().getMetaFromState(iblockstate) != j) {
					throw new CommandException("commands.execute.failed", new Object[] { "detect", entity.getName() });
				}

				b0 = 10;
			}

			String s = buildString(parArrayOfString, b0);
			ICommandSender icommandsender = new ICommandSender() {
				public String getName() {
					return entity.getName();
				}

				public IChatComponent getDisplayName() {
					return entity.getDisplayName();
				}

				public void addChatMessage(IChatComponent component) {
					parICommandSender.addChatMessage(component);
				}

				public boolean canCommandSenderUseCommand(int permLevel, String commandName) {
					return parICommandSender.canCommandSenderUseCommand(permLevel, commandName);
				}

				public BlockPos getPosition() {
					return blockpos;
				}

				public Vec3 getPositionVector() {
					return new Vec3(d0, d1, d2);
				}

				public World getEntityWorld() {
					return entity.worldObj;
				}

				public Entity getCommandSenderEntity() {
					return entity;
				}

				public boolean sendCommandFeedback() {
					MinecraftServer minecraftserver = MinecraftServer.getServer();
					return minecraftserver == null
							|| minecraftserver.worldServers[0].getGameRules().getBoolean("commandBlockOutput");
				}

				public void setCommandStat(CommandResultStats.Type type, int amount) {
					entity.setCommandStat(type, amount);
				}
			};
			ICommandManager icommandmanager = MinecraftServer.getServer().getCommandManager();

			try {
				int i = icommandmanager.executeCommand(icommandsender, s);
				if (i < 1) {
					throw new CommandException("commands.execute.allInvocationsFailed", new Object[] { s });
				}
			} catch (Throwable var23) {
				throw new CommandException("commands.execute.failed", new Object[] { s, entity.getName() });
			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: (astring.length > 1 && astring.length <= 4 ? func_175771_a(astring, 1, blockpos)
						: (astring.length > 5 && astring.length <= 8 && "detect".equals(astring[4])
								? func_175771_a(astring, 5, blockpos)
								: (astring.length == 9 && "detect".equals(astring[4])
										? getListOfStringsMatchingLastWord(astring, Block.blockRegistry.getKeys())
										: null)));
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 0;
	}
}
