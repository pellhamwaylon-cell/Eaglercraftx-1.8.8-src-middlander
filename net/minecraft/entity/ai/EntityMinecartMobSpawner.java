package net.minecraft.entity.ai;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class EntityMinecartMobSpawner extends EntityMinecart {
	private final MobSpawnerBaseLogic mobSpawnerLogic = new MobSpawnerBaseLogic() {
		public void func_98267_a(int i) {
			EntityMinecartMobSpawner.this.worldObj.setEntityState(EntityMinecartMobSpawner.this, (byte) i);
		}

		public World getSpawnerWorld() {
			return EntityMinecartMobSpawner.this.worldObj;
		}

		public BlockPos getSpawnerPosition() {
			return new BlockPos(EntityMinecartMobSpawner.this);
		}
	};

	public EntityMinecartMobSpawner(World worldIn) {
		super(worldIn);
	}

	public EntityMinecartMobSpawner(World worldIn, double parDouble1, double parDouble2, double parDouble3) {
		super(worldIn, parDouble1, parDouble2, parDouble3);
	}

	public EntityMinecart.EnumMinecartType getMinecartType() {
		return EntityMinecart.EnumMinecartType.SPAWNER;
	}

	public IBlockState getDefaultDisplayTile() {
		return Blocks.mob_spawner.getDefaultState();
	}

	protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		super.readEntityFromNBT(nbttagcompound);
		this.mobSpawnerLogic.readFromNBT(nbttagcompound);
	}

	protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		super.writeEntityToNBT(nbttagcompound);
		this.mobSpawnerLogic.writeToNBT(nbttagcompound);
	}

	public void handleStatusUpdate(byte b0) {
		this.mobSpawnerLogic.setDelayToMin(b0);
	}

	public void onUpdate() {
		super.onUpdate();
		this.mobSpawnerLogic.updateSpawner();
	}

	public MobSpawnerBaseLogic func_98039_d() {
		return this.mobSpawnerLogic;
	}
}
