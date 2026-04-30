package net.minecraft.world.biome;

import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BiomeColorHelper {

	private static final BiomeColorHelper.ColorResolver field_180291_a = new BiomeColorHelper.ColorResolver() {
		public int getColorAtPos(BiomeGenBase blockPosition, BlockPos parBlockPos) {
			return blockPosition.getGrassColorAtPos(parBlockPos);
		}
	};
	private static final BiomeColorHelper.ColorResolver field_180289_b = new BiomeColorHelper.ColorResolver() {
		public int getColorAtPos(BiomeGenBase biomegenbase, BlockPos blockpos) {
			return biomegenbase.getFoliageColorAtPos(blockpos);
		}
	};
	private static final BiomeColorHelper.ColorResolver field_180290_c = new BiomeColorHelper.ColorResolver() {
		public int getColorAtPos(BiomeGenBase biomegenbase, BlockPos var2) {
			return biomegenbase.waterColorMultiplier;
		}
	};

	private static int func_180285_a(IBlockAccess parIBlockAccess, BlockPos parBlockPos,
			BiomeColorHelper.ColorResolver parColorResolver) {
		int i = 0;
		int j = 0;
		int k = 0;

		for (BlockPos blockpos$mutableblockpos : BlockPos.getAllInBoxMutable(parBlockPos.add(-1, 0, -1),
				parBlockPos.add(1, 0, 1))) {
			int l = parColorResolver.getColorAtPos(parIBlockAccess.getBiomeGenForCoords(blockpos$mutableblockpos),
					blockpos$mutableblockpos);
			i += (l & 16711680) >> 16;
			j += (l & '\uff00') >> 8;
			k += l & 255;
		}

		return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
	}

	public static int getGrassColorAtPos(IBlockAccess parIBlockAccess, BlockPos parBlockPos) {
		return parIBlockAccess.getBiomeColorForCoords(parBlockPos, 0);
	}

	public static int getFoliageColorAtPos(IBlockAccess parIBlockAccess, BlockPos parBlockPos) {
		return parIBlockAccess.getBiomeColorForCoords(parBlockPos, 1);
	}

	public static int getWaterColorAtPos(IBlockAccess parIBlockAccess, BlockPos parBlockPos) {
		return parIBlockAccess.getBiomeColorForCoords(parBlockPos, 2);
	}

	public static int getBiomeColorForCoordsOld(IBlockAccess parIBlockAccess, BlockPos parBlockPos, int index) {
		if (index == 0) {
			return func_180285_a(parIBlockAccess, parBlockPos, field_180291_a);
		} else if (index == 1) {
			return func_180285_a(parIBlockAccess, parBlockPos, field_180289_b);
		} else {
			return func_180285_a(parIBlockAccess, parBlockPos, field_180290_c);
		}
	}

	interface ColorResolver {
		int getColorAtPos(BiomeGenBase var1, BlockPos var2);
	}
}
