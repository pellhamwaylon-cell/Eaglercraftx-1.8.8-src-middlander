package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockDeadBush extends BlockBush {
	protected BlockDeadBush() {
		super(Material.vine);
		float f = 0.4F;
		this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, 0.8F, 0.5F + f);
	}

	public MapColor getMapColor(IBlockState var1) {
		return MapColor.woodColor;
	}

	protected boolean canPlaceBlockOn(Block block) {
		return block == Blocks.sand || block == Blocks.hardened_clay || block == Blocks.stained_hardened_clay
				|| block == Blocks.dirt;
	}

	public boolean isReplaceable(World var1, BlockPos var2) {
		return true;
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return null;
	}

	public void harvestBlock(World world, EntityPlayer entityplayer, BlockPos blockpos, IBlockState iblockstate,
			TileEntity tileentity) {
		if (!world.isRemote && entityplayer.getCurrentEquippedItem() != null
				&& entityplayer.getCurrentEquippedItem().getItem() == Items.shears) {
			entityplayer.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
			spawnAsEntity(world, blockpos, new ItemStack(Blocks.deadbush, 1, 0));
		} else {
			super.harvestBlock(world, entityplayer, blockpos, iblockstate, tileentity);
		}

	}
}
