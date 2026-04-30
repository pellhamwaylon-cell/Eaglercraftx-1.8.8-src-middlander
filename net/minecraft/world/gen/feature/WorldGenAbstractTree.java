package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public abstract class WorldGenAbstractTree extends WorldGenerator {
	public WorldGenAbstractTree(boolean parFlag) {
		super(parFlag);
	}

	protected boolean func_150523_a(Block parBlock) {
		Material material = parBlock.getMaterial();
		return material == Material.air || material == Material.leaves || parBlock == Blocks.grass
				|| parBlock == Blocks.dirt || parBlock == Blocks.log || parBlock == Blocks.log2
				|| parBlock == Blocks.sapling || parBlock == Blocks.vine;
	}

	public void func_180711_a(World worldIn, EaglercraftRandom parRandom, BlockPos parBlockPos) {
	}

	protected void func_175921_a(World worldIn, BlockPos parBlockPos) {
		if (worldIn.getBlockState(parBlockPos).getBlock() != Blocks.dirt) {
			this.setBlockAndNotifyAdequately(worldIn, parBlockPos, Blocks.dirt.getDefaultState());
		}

	}
}
