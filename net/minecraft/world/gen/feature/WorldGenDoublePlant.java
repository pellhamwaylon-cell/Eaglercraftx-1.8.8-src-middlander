package net.minecraft.world.gen.feature;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class WorldGenDoublePlant extends WorldGenerator {
	private BlockDoublePlant.EnumPlantType field_150549_a;

	public void setPlantType(BlockDoublePlant.EnumPlantType parEnumPlantType) {
		this.field_150549_a = parEnumPlantType;
	}

	public boolean generate(World world, EaglercraftRandom random, BlockPos blockpos) {
		boolean flag = false;

		for (int i = 0; i < 64; ++i) {
			BlockPos blockpos1 = blockpos.add(random.nextInt(8) - random.nextInt(8),
					random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
			if (world.isAirBlock(blockpos1) && (!world.provider.getHasNoSky() || blockpos1.getY() < 254)
					&& Blocks.double_plant.canPlaceBlockAt(world, blockpos1)) {
				Blocks.double_plant.placeAt(world, blockpos1, this.field_150549_a, 2);
				flag = true;
			}
		}

		return flag;
	}
}
