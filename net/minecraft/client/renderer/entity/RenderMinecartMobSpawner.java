package net.minecraft.client.renderer.entity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.tileentity.TileEntityMobSpawnerRenderer;
import net.minecraft.entity.ai.EntityMinecartMobSpawner;
import net.minecraft.init.Blocks;

public class RenderMinecartMobSpawner extends RenderMinecart<EntityMinecartMobSpawner> {
	public RenderMinecartMobSpawner(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	protected void func_180560_a(EntityMinecartMobSpawner entityminecartmobspawner, float f, IBlockState iblockstate) {
		super.func_180560_a(entityminecartmobspawner, f, iblockstate);
		if (iblockstate.getBlock() == Blocks.mob_spawner) {
			TileEntityMobSpawnerRenderer.renderMob(entityminecartmobspawner.func_98039_d(),
					entityminecartmobspawner.posX, entityminecartmobspawner.posY, entityminecartmobspawner.posZ, f);
		}

	}
}
