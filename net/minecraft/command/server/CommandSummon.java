package net.minecraft.command.server;

import java.util.List;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class CommandSummon extends CommandBase {

	public String getCommandName() {
		return "summon";
	}

	public int getRequiredPermissionLevel() {
		return 2;
	}

	public String getCommandUsage(ICommandSender var1) {
		return "commands.summon.usage";
	}

	public void processCommand(ICommandSender parICommandSender, String[] parArrayOfString) throws CommandException {
		if (parArrayOfString.length < 1) {
			throw new WrongUsageException("commands.summon.usage", new Object[0]);
		} else {
			String s = parArrayOfString[0];
			BlockPos blockpos = parICommandSender.getPosition();
			Vec3 vec3 = parICommandSender.getPositionVector();
			double d0 = vec3.xCoord;
			double d1 = vec3.yCoord;
			double d2 = vec3.zCoord;
			if (parArrayOfString.length >= 4) {
				d0 = parseDouble(d0, parArrayOfString[1], true);
				d1 = parseDouble(d1, parArrayOfString[2], false);
				d2 = parseDouble(d2, parArrayOfString[3], true);
				blockpos = new BlockPos(d0, d1, d2);
			}

			World world = parICommandSender.getEntityWorld();
			if (!world.isBlockLoaded(blockpos)) {
				throw new CommandException("commands.summon.outOfWorld", new Object[0]);
			} else if ("LightningBolt".equals(s)) {
				world.addWeatherEffect(new EntityLightningBolt(world, d0, d1, d2));
				notifyOperators(parICommandSender, this, "commands.summon.success", new Object[0]);
			} else {
				NBTTagCompound nbttagcompound = new NBTTagCompound();
				boolean flag = false;
				if (parArrayOfString.length >= 5) {
					IChatComponent ichatcomponent = getChatComponentFromNthArg(parICommandSender, parArrayOfString, 4);

					try {
						nbttagcompound = JsonToNBT.getTagFromJson(ichatcomponent.getUnformattedText());
						flag = true;
					} catch (NBTException nbtexception) {
						throw new CommandException("commands.summon.tagError",
								new Object[] { nbtexception.getMessage() });
					}
				}

				nbttagcompound.setString("id", s);

				Entity entity2;
				try {
					entity2 = EntityList.createEntityFromNBT(nbttagcompound, world);
				} catch (RuntimeException var19) {
					throw new CommandException("commands.summon.failed", new Object[0]);
				}

				if (entity2 == null) {
					throw new CommandException("commands.summon.failed", new Object[0]);
				} else {
					entity2.setLocationAndAngles(d0, d1, d2, entity2.rotationYaw, entity2.rotationPitch);
					if (!flag && entity2 instanceof EntityLiving) {
						((EntityLiving) entity2).onInitialSpawn(world.getDifficultyForLocation(new BlockPos(entity2)),
								(IEntityLivingData) null);
					}

					world.spawnEntityInWorld(entity2);
					Entity entity = entity2;

					for (NBTTagCompound nbttagcompound1 = nbttagcompound; entity != null && nbttagcompound1
							.hasKey("Riding", 10); nbttagcompound1 = nbttagcompound1.getCompoundTag("Riding")) {
						Entity entity1 = EntityList.createEntityFromNBT(nbttagcompound1.getCompoundTag("Riding"),
								world);
						if (entity1 != null) {
							entity1.setLocationAndAngles(d0, d1, d2, entity1.rotationYaw, entity1.rotationPitch);
							world.spawnEntityInWorld(entity1);
							entity.mountEntity(entity1);
						}

						entity = entity1;
					}

					notifyOperators(parICommandSender, this, "commands.summon.success", new Object[0]);
				}
			}
		}
	}

	public List<String> addTabCompletionOptions(ICommandSender var1, String[] astring, BlockPos blockpos) {
		return astring.length == 1 ? getListOfStringsMatchingLastWord(astring, EntityList.getEntityNameList())
				: (astring.length > 1 && astring.length <= 4 ? func_175771_a(astring, 1, blockpos) : null);
	}
}
