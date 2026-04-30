package net.minecraft.world;

import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManagerHell;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderEnd;

public class WorldProviderEnd extends WorldProvider {
	public void registerWorldChunkManager() {
		this.worldChunkMgr = new WorldChunkManagerHell(BiomeGenBase.sky, 0.0F);
		this.dimensionId = 1;
		this.hasNoSky = true;
	}

	public IChunkProvider createChunkGenerator() {
		return new ChunkProviderEnd(this.worldObj, this.worldObj.getSeed());
	}

	public float calculateCelestialAngle(long var1, float var3) {
		return 0.0F;
	}

	public float[] calcSunriseSunsetColors(float var1, float var2) {
		return null;
	}

	public Vec3 getFogColor(float f, float var2) {
		int i = 10518688;
		float f1 = MathHelper.cos(f * 3.1415927F * 2.0F) * 2.0F + 0.5F;
		f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
		float f2 = (float) (i >> 16 & 255) / 255.0F;
		float f3 = (float) (i >> 8 & 255) / 255.0F;
		float f4 = (float) (i & 255) / 255.0F;
		f2 = f2 * (f1 * 0.0F + 0.15F);
		f3 = f3 * (f1 * 0.0F + 0.15F);
		f4 = f4 * (f1 * 0.0F + 0.15F);
		return new Vec3((double) f2, (double) f3, (double) f4);
	}

	public boolean isSkyColored() {
		return false;
	}

	public boolean canRespawnHere() {
		return false;
	}

	public boolean isSurfaceWorld() {
		return false;
	}

	public float getCloudHeight() {
		return 8.0F;
	}

	public boolean canCoordinateBeSpawn(int i, int j) {
		return this.worldObj.getGroundAboveSeaLevel(new BlockPos(i, 0, j)).getMaterial().blocksMovement();
	}

	public BlockPos getSpawnCoordinate() {
		return new BlockPos(100, 50, 0);
	}

	public int getAverageGroundLevel() {
		return 50;
	}

	public boolean doesXZShowFog(int var1, int var2) {
		return true;
	}

	public String getDimensionName() {
		return "The End";
	}

	public String getInternalNameSuffix() {
		return "_end";
	}
}
