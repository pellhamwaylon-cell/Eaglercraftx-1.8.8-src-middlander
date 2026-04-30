package net.minecraft.command;

import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.S29PacketSoundEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.Vec3;

public class CommandPlaySound extends CommandBase {

	public String getCommandName() {
		return "playsound";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.playsound.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 2) {
			throw new WrongUsageException(this.getCommandUsage(parICommandSender), new Object[0]);
		} else {
			int i = 0;
			String s = parArrayOfString[i++];
			EntityPlayerMP entityplayermp = getPlayer(parICommandSender, parArrayOfString[i++]);
			Vec3 vec3 = parICommandSender.getPositionVector();
			double d0 = vec3.xCoord;
			if (parArrayOfString.length > i) {
				d0 = parseDouble(d0, parArrayOfString[i++], true);
			}

			double d1 = vec3.yCoord;
			if (parArrayOfString.length > i) {
				d1 = parseDouble(d1, parArrayOfString[i++], 0, 0, false);
			}

			double d2 = vec3.zCoord;
			if (parArrayOfString.length > i) {
				d2 = parseDouble(d2, parArrayOfString[i++], true);
			}

			double d3 = 1.0D;
			if (parArrayOfString.length > i) {
				d3 = parseDouble(parArrayOfString[i++], 0.0D, 3.4028234663852886E38D);
			}

			double d4 = 1.0D;
			if (parArrayOfString.length > i) {
				d4 = parseDouble(parArrayOfString[i++], 0.0D, 2.0D);
			}

			double d5 = 0.0D;
			if (parArrayOfString.length > i) {
				d5 = parseDouble(parArrayOfString[i], 0.0D, 1.0D);
			}

			double d6 = d3 > 1.0D ? d3 * 16.0D : 16.0D;
			double d7 = entityplayermp.getDistance(d0, d1, d2);
			if (d7 > d6) {
				if (d5 <= 0.0D) {
					throw new CommandException("commands.playsound.playerTooFar",
							new Object[] { entityplayermp.getName() });
				}

				double d8 = d0 - entityplayermp.posX;
				double d9 = d1 - entityplayermp.posY;
				double d10 = d2 - entityplayermp.posZ;
				double d11 = Math.sqrt(d8 * d8 + d9 * d9 + d10 * d10);
				if (d11 > 0.0D) {
					d0 = entityplayermp.posX + d8 / d11 * 2.0D;
					d1 = entityplayermp.posY + d9 / d11 * 2.0D;
					d2 = entityplayermp.posZ + d10 / d11 * 2.0D;
				}

				d3 = d5;
			}

			entityplayermp.playerNetServerHandler
					.sendPacket(new S29PacketSoundEffect(s, d0, d1, d2, (float) d3, (float) d4));
			notifyOperators(parICommandSender, this, "commands.playsound.success",
					new Object[] { s, entityplayermp.getName() });
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length == 2
				? getListOfStringsMatchingLastWord(astring, MinecraftServer.getServer().getAllUsernames())
				: (astring.length > 2 && astring.length <= 5 ? func_175771_a(astring, 2, blockpos) : null);
	}

	public boolean isUsernameIndex(String[] var1, int i) {
		return i == 1;
	}
}
