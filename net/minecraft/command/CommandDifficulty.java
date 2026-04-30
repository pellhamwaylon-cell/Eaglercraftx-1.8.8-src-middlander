package net.minecraft.command;

import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.world.EnumDifficulty;

public class CommandDifficulty extends CommandBase {

	public String getCommandName() {
		return "difficulty";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.difficulty.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length <= 0) {
			throw new WrongUsageException("commands.difficulty.usage", new Object[0]);
		} else {
			EnumDifficulty enumdifficulty = this.getDifficultyFromCommand(parArrayOfString[0]);
			MinecraftServer.getServer().setDifficultyForAllWorlds(enumdifficulty);
			notifyOperators(parICommandSender, this, "commands.difficulty.success", new Object[] {
					new ChatComponentTranslation(enumdifficulty.getDifficultyResourceKey(), new Object[0]) });
		}
	}

	protected EnumDifficulty getDifficultyFromCommand(String parString1) throws NumberInvalidException {
		return !parString1.equalsIgnoreCase("peaceful") && !parString1.equalsIgnoreCase("p")
				? (!parString1.equalsIgnoreCase("easy") && !parString1.equalsIgnoreCase("e")
						? (!parString1.equalsIgnoreCase("normal") && !parString1.equalsIgnoreCase("n")
								? (!parString1.equalsIgnoreCase("hard") && !parString1.equalsIgnoreCase("h")
										? EnumDifficulty.getDifficultyEnum(parseInt(parString1, 0, 3))
										: EnumDifficulty.HARD)
								: EnumDifficulty.NORMAL)
						: EnumDifficulty.EASY)
				: EnumDifficulty.PEACEFUL;
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos var3) {
		return astring.length == 1
				? getListOfStringsMatchingLastWord(astring, new String[] { "peaceful", "easy", "normal", "hard" })
				: null;
	}
}
