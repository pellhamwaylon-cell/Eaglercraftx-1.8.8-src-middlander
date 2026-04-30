package net.minecraft.world.chunk;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import com.google.common.base.Predicate;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EmptyChunk extends Chunk {
	public EmptyChunk(World worldIn, int x, int z) {
		super(worldIn, x, z);
	}

	public boolean isAtLocation(int x, int z) {
		return x == this.xPosition && z == this.zPosition;
	}

	public int getHeightValue(int x, int z) {
		return 0;
	}

	public void generateHeightMap() {
	}

	public void generateSkylightMap() {
	}

	public Block getBlock(BlockPos pos) {
		return Blocks.air;
	}

	public int getBlockLightOpacity(BlockPos pos) {
		return 255;
	}

	public int getBlockMetadata(BlockPos pos) {
		return 0;
	}

	public int getLightFor(EnumSkyBlock pos, BlockPos parBlockPos) {
		return pos.defaultLightValue;
	}

	public void setLightFor(EnumSkyBlock pos, BlockPos value, int parInt1) {
	}

	public int getLightSubtracted(BlockPos pos, int amount) {
		return 0;
	}

	public void addEntity(Entity entityIn) {
	}

	public void removeEntity(Entity entityIn) {
	}

	public void removeEntityAtIndex(Entity entityIn, int parInt1) {
	}

	public boolean canSeeSky(BlockPos pos) {
		return false;
	}

	public TileEntity getTileEntity(BlockPos pos, Chunk.EnumCreateEntityType parEnumCreateEntityType) {
		return null;
	}

	public void addTileEntity(TileEntity tileEntityIn) {
	}

	public void addTileEntity(BlockPos pos, TileEntity tileEntityIn) {
	}

	public void removeTileEntity(BlockPos pos) {
	}

	public void onChunkLoad() {
	}

	public void onChunkUnload() {
	}

	public void setChunkModified() {
	}

	public void getEntitiesWithinAABBForEntity(Entity entityIn, AxisAlignedBB aabb, List<Entity> listToFill,
			Predicate<? super Entity> parPredicate) {
	}

	public <T extends Entity> void getEntitiesOfTypeWithinAAAB(Class<? extends T> entityClass, AxisAlignedBB aabb,
			List<T> listToFill, Predicate<? super T> parPredicate) {
	}

	public boolean needsSaving(boolean parFlag) {
		return false;
	}

	public EaglercraftRandom getRandomWithSeed(long seed) {
		return new EaglercraftRandom(
				this.getWorld().getSeed() + (long) (this.xPosition * this.xPosition * 4987142)
						+ (long) (this.xPosition * 5947611) + (long) (this.zPosition * this.zPosition) * 4392871L
						+ (long) (this.zPosition * 389711) ^ seed,
				!this.getWorld().getWorldInfo().isOldEaglercraftRandom());
	}

	public boolean isEmpty() {
		return true;
	}

	public boolean getAreLevelsEmpty(int startY, int endY) {
		return true;
	}
}
