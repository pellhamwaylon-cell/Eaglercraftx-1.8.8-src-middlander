package net.minecraft.world.gen;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockBush;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GeneratorBushFeature extends WorldGenerator {

	private BlockBush field_175908_a;

	public GeneratorBushFeature(BlockBush parBlockBush) {
		this.field_175908_a = parBlockBush;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos1) && (!world.provider.getHasNoSky() || blockpos1.getY() < 255)
					&& this.field_175908_a.canBlockStay(world, blockpos1, this.field_175908_a.getDefaultState())) {
				world.setBlockState(blockpos1, this.field_175908_a.getDefaultState(), 2);
			}
		}

		return true;
	}
}
