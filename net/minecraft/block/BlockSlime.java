package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;

public class BlockSlime extends BlockBreakable {
	public BlockSlime() {
		super(Material.clay, false, MapColor.grassColor);
		this.setCreativeTab(CreativeTabs.tabDecorations);
		this.slipperiness = 0.8F;
	}

	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.TRANSLUCENT;
	}

	public void onFallenUpon(World world, BlockPos blockpos, Entity entity, float f) {
		if (entity.isSneaking()) {
			super.onFallenUpon(world, blockpos, entity, f);
		} else {
			entity.fall(f, 0.0F);
		}

	}

	public void onLanded(World world, Entity entity) {
		if (entity.isSneaking()) {
			super.onLanded(world, entity);
		} else if (entity.motionY < 0.0D) {
			entity.motionY = -entity.motionY;
		}

	}

	public void onEntityCollidedWithBlock(World world, BlockPos blockpos, Entity entity) {
		if (Math.abs(entity.motionY) < 0.1D && !entity.isSneaking()) {
			double d0 = 0.4D + Math.abs(entity.motionY) * 0.2D;
			entity.motionX *= d0;
			entity.motionZ *= d0;
		}

		super.onEntityCollidedWithBlock(world, blockpos, entity);
	}
}
