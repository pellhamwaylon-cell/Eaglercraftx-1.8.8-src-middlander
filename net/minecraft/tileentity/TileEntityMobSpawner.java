package net.minecraft.tileentity;

import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;

public class TileEntityMobSpawner extends TileEntity implements ITickable {
	private final MobSpawnerBaseLogic spawnerLogic = new MobSpawnerBaseLogic() {
		public void func_98267_a(int i) {
			TileEntityMobSpawner.this.worldObj.addBlockEvent(TileEntityMobSpawner.this.pos, Blocks.mob_spawner, i, 0);
		}

		public World getSpawnerWorld() {
			return TileEntityMobSpawner.this.worldObj;
		}

		public BlockPos getSpawnerPosition() {
			return TileEntityMobSpawner.this.pos;
		}

		public void setRandomEntity(
				MobSpawnerBaseLogic.WeightedRandomMinecart mobspawnerbaselogic$weightedrandomminecart) {
			super.setRandomEntity(mobspawnerbaselogic$weightedrandomminecart);
			if (this.getSpawnerWorld() != null) {
				this.getSpawnerWorld().markBlockForUpdate(TileEntityMobSpawner.this.pos);
			}

		}
	};

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.spawnerLogic.readFromNBT(nbttagcompound);
	}

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		this.spawnerLogic.writeToNBT(nbttagcompound);
	}

	public void update() {
		this.spawnerLogic.updateSpawner();
	}

	public Packet getDescriptionPacket() {
		NBTTagCompound nbttagcompound = new NBTTagCompound();
		this.writeToNBT(nbttagcompound);
		nbttagcompound.removeTag("SpawnPotentials");
		return new S35PacketUpdateTileEntity(this.pos, 1, nbttagcompound);
	}

	public boolean receiveClientEvent(int i, int j) {
		return this.spawnerLogic.setDelayToMin(i) ? true : super.receiveClientEvent(i, j);
	}

	public boolean func_183000_F() {
		return true;
	}

	public MobSpawnerBaseLogic getSpawnerBaseLogic() {
		return this.spawnerLogic;
	}
}
