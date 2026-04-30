package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenFlowers extends WorldGenerator {
	private BlockFlower flower;
	private IBlockState field_175915_b;

	public WorldGenFlowers(BlockFlower parBlockFlower, BlockFlower.EnumFlowerType parEnumFlowerType) {
		this.setGeneratedBlock(parBlockFlower, parEnumFlowerType);
	}

	public void setGeneratedBlock(BlockFlower parBlockFlower, BlockFlower.EnumFlowerType parEnumFlowerType) {
		this.flower = parBlockFlower;
		this.field_175915_b = parBlockFlower.getDefaultState().withProperty(parBlockFlower.getTypeProperty(),
				parEnumFlowerType);
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos1) && (!world.provider.getHasNoSky() || blockpos1.getY() < 255)
					&& this.flower.canBlockStay(world, blockpos1, this.field_175915_b)) {
				world.setBlockState(blockpos1, this.field_175915_b, 2);
			}
		}

		return true;
	}
}
