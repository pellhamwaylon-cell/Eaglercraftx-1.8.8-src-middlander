package net.minecraft.world.gen;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkProvider;

public class MapGenBase {
	protected int range = 8;
	protected EaglercraftRandom rand;
	protected World worldObj;

	public MapGenBase() {
		this(true);
	}

	public MapGenBase(boolean scramble) {
		rand = new EaglercraftRandom(scramble);
	}

	public void generate(IChunkProvider chunkProviderIn, World worldIn, int x, int z, ChunkPrimer chunkPrimerIn) {
		int i = this.range;
		this.worldObj = worldIn;
		this.rand.setSeed(worldIn.getSeed());
		long j = this.rand.nextLong();
		long k = this.rand.nextLong();

		for (int l = x - i; l <= x + i; ++l) {
			for (int i1 = z - i; i1 <= z + i; ++i1) {
				long j1 = (long) l * j;
				long k1 = (long) i1 * k;
				this.rand.setSeed(j1 ^ k1 ^ worldIn.getSeed());
				this.recursiveGenerate(worldIn, l, i1, x, z, chunkPrimerIn);
			}
		}

	}

	protected void recursiveGenerate(World var1, int var2, int var3, int var4, int var5, ChunkPrimer var6) {
	}
}
