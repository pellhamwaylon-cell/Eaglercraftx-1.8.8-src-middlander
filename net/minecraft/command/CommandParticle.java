package net.minecraft.command;

import java.util.List;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class CommandParticle extends CommandBase {

	public String getCommandName() {
		return "particle";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.particle.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 8) {
			throw new WrongUsageException("commands.particle.usage", new Object[0]);
		} else {
			boolean flag = false;
			EnumParticleTypes enumparticletypes = null;

			EnumParticleTypes[] types = EnumParticleTypes._VALUES;
			for (int i = 0; i < types.length; ++i) {
				EnumParticleTypes enumparticletypes1 = types[i];
				if (enumparticletypes1.hasArguments()) {
					if (parArrayOfString[0].startsWith(enumparticletypes1.getParticleName())) {
						flag = true;
						enumparticletypes = enumparticletypes1;
						break;
					}
				} else if (parArrayOfString[0].equals(enumparticletypes1.getParticleName())) {
					flag = true;
					enumparticletypes = enumparticletypes1;
					break;
				}
			}

			if (!flag) {
				throw new CommandException("commands.particle.notFound", new Object[] { parArrayOfString[0] });
			} else {
				String s = parArrayOfString[0];
				Vec3 vec3 = parICommandSender.getPositionVector();
				double d6 = (double) ((float) parseDouble(vec3.xCoord, parArrayOfString[1], true));
				double d0 = (double) ((float) parseDouble(vec3.yCoord, parArrayOfString[2], true));
				double d1 = (double) ((float) parseDouble(vec3.zCoord, parArrayOfString[3], true));
				double d2 = (double) ((float) parseDouble(parArrayOfString[4]));
				double d3 = (double) ((float) parseDouble(parArrayOfString[5]));
				double d4 = (double) ((float) parseDouble(parArrayOfString[6]));
				double d5 = (double) ((float) parseDouble(parArrayOfString[7]));
				int i = 0;
				if (parArrayOfString.length > 8) {
					i = parseInt(parArrayOfString[8], 0);
				}

				boolean flag1 = false;
				if (parArrayOfString.length > 9 && "force".equals(parArrayOfString[9])) {
					flag1 = true;
				}

				World world = parICommandSender.getEntityWorld();
				if (world instanceof WorldServer) {
					WorldServer worldserver = (WorldServer) world;
					int[] aint = new int[enumparticletypes.getArgumentCount()];
					if (enumparticletypes.hasArguments()) {
						String[] astring = parArrayOfString[0].split("_", 3);

						for (int j = 1; j < astring.length; ++j) {
							try {
								aint[j - 1] = Integer.parseInt(astring[j]);
							} catch (NumberFormatException var29) {
								throw new CommandException("commands.particle.notFound",
										new Object[] { parArrayOfString[0] });
							}
						}
					}

					worldserver.spawnParticle(enumparticletypes, flag1, d6, d0, d1, i, d2, d3, d4, d5, aint);
					notifyOperators(parICommandSender, this, "commands.particle.success",
							new Object[] { s, Integer.valueOf(Math.max(i, 1)) });
				}

			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, EnumParticleTypes.getParticleNames())
				: (astring.length > 1 && astring.length <= 4 ? func_175771_a(astring, 1, blockpos)
						: (astring.length == 10
								? getListOfStringsMatchingLastWord(astring, new String[] { "normal", "force" })
								: null));
	}
}
