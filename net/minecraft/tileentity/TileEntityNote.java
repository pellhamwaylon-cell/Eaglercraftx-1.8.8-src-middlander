package net.minecraft.tileentity;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class TileEntityNote extends TileEntity {
	public byte note;
	public boolean previousRedstoneState;

	public void writeToNBT(NBTTagCompound nbttagcompound) {
		super.writeToNBT(nbttagcompound);
		nbttagcompound.setByte("note", this.note);
	}

	public void readFromNBT(NBTTagCompound nbttagcompound) {
		super.readFromNBT(nbttagcompound);
		this.note = nbttagcompound.getByte("note");
		this.note = (byte) MathHelper.clamp_int(this.note, 0, 24);
	}

	public void changePitch() {
		this.note = (byte) ((this.note + 1) % 25);
		this.markDirty();
	}

	public void triggerNote(World worldIn, BlockPos parBlockPos) {
		if (worldIn.getBlockState(parBlockPos.up()).getBlock().getMaterial() == Material.air) {
			Material material = worldIn.getBlockState(parBlockPos.down()).getBlock().getMaterial();
			byte b0 = 0;
			if (material == Material.rock) {
				b0 = 1;
			}

			if (material == Material.sand) {
				b0 = 2;
			}

			if (material == Material.glass) {
				b0 = 3;
			}

			if (material == Material.wood) {
				b0 = 4;
			}

			worldIn.addBlockEvent(parBlockPos, Blocks.noteblock, b0, this.note);
		}
	}
}
