package net.minecraft.block;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockPotato extends BlockCrops {
	protected Item getSeed() {
		return Items.potato;
	}

	protected Item getCrop() {
		return Items.potato;
	}

	public void dropBlockAsItemWithChance(World world, BlockPos blockpos, IBlockState iblockstate, float f, int i) {
		super.dropBlockAsItemWithChance(world, blockpos, iblockstate, f, i);
		if (!world.isRemote) {
			if (((Integer) iblockstate.getValue(AGE)).intValue() >= 7 && world.rand.nextInt(50) == 0) {
				spawnAsEntity(world, blockpos, new ItemStack(Items.poisonous_potato));
			}
		}
	}
}
