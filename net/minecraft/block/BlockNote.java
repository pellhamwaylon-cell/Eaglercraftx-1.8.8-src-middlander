package net.minecraft.block;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityNote;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class BlockNote extends BlockContainer {
	private static final List<String> INSTRUMENTS = Lists
			.newArrayList(new String[] { "harp", "bd", "snare", "hat", "bassattack" });

	public BlockNote() {
		super(Material.wood);
		this.setCreativeTab(CreativeTabs.tabRedstone);
	}

	public void onNeighborBlockChange(World world, BlockPos blockpos, IBlockState var3, Block var4) {
		boolean flag = world.isBlockPowered(blockpos);
		TileEntity tileentity = world.getTileEntity(blockpos);
		if (tileentity instanceof TileEntityNote) {
			TileEntityNote tileentitynote = (TileEntityNote) tileentity;
			if (tileentitynote.previousRedstoneState != flag) {
				if (flag) {
					tileentitynote.triggerNote(world, blockpos);
				}

				tileentitynote.previousRedstoneState = flag;
			}
		}

	}

	public boolean onBlockActivated(World world, BlockPos blockpos, IBlockState var3, EntityPlayer entityplayer,
			EnumFacing var5, float var6, float var7, float var8) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityNote) {
				TileEntityNote tileentitynote = (TileEntityNote) tileentity;
				tileentitynote.changePitch();
				tileentitynote.triggerNote(world, blockpos);
				entityplayer.triggerAchievement(StatList.field_181735_S);
			}

			return true;
		}
	}

	public void onBlockClicked(World world, BlockPos blockpos, EntityPlayer entityplayer) {
		if (!world.isRemote) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityNote) {
				((TileEntityNote) tileentity).triggerNote(world, blockpos);
				entityplayer.triggerAchievement(StatList.field_181734_R);
			}
		}
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityNote();
	}

	private String getInstrument(int id) {
		if (id < 0 || id >= INSTRUMENTS.size()) {
			id = 0;
		}

		return (String) INSTRUMENTS.get(id);
	}

	public boolean onBlockEventReceived(World world, BlockPos blockpos, IBlockState var3, int i, int j) {
		float f = (float) Math.pow(2.0D, (double) (j - 12) / 12.0D);
		world.playSoundEffect((double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 0.5D,
				(double) blockpos.getZ() + 0.5D, "note." + this.getInstrument(i), 3.0F, f);
		world.spawnParticle(EnumParticleTypes.NOTE, (double) blockpos.getX() + 0.5D, (double) blockpos.getY() + 1.2D,
				(double) blockpos.getZ() + 0.5D, (double) j / 24.0D, 0.0D, 0.0D, new int[0]);
		return true;
	}

	public int getRenderType() {
		return 3;
	}
}
