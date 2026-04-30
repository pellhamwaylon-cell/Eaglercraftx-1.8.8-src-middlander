package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumWorldBlockLayer;

public class BlockGlass extends BlockBreakable {
	public BlockGlass(Material materialIn, boolean ignoreSimilarity) {
		super(materialIn, ignoreSimilarity);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public int quantityDropped(EaglercraftRandom var1) {
		return 0;
	}

	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	public boolean isFullCube() {
		return false;
	}

	protected boolean canSilkHarvest() {
		return true;
	}

	public boolean eaglerShadersShouldRenderGlassHighlights() {
		return DeferredStateManager.isRenderingGlassHighlights();
	}
}
