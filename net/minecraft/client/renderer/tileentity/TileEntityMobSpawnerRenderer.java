package net.minecraft.client.renderer.tileentity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.MobSpawnerBaseLogic;
import net.minecraft.tileentity.TileEntityMobSpawner;

public class TileEntityMobSpawnerRenderer extends TileEntitySpecialRenderer<TileEntityMobSpawner> {
	public void renderTileEntityAt(TileEntityMobSpawner tileentitymobspawner, double d0, double d1, double d2, float f,
			int var9) {
		GlStateManager.pushMatrix();
		GlStateManager.translate((float) d0 + 0.5F, (float) d1, (float) d2 + 0.5F);
		renderMob(tileentitymobspawner.getSpawnerBaseLogic(), d0, d1, d2, f);
		GlStateManager.popMatrix();
	}

	public static void renderMob(MobSpawnerBaseLogic mobSpawnerLogic, double posX, double posY, double posZ,
			float partialTicks) {
		Entity entity = mobSpawnerLogic.func_180612_a(mobSpawnerLogic.getSpawnerWorld());
		if (entity != null) {
			float f = 0.4375F;
			GlStateManager.translate(0.0F, 0.4F, 0.0F);
			GlStateManager.rotate((float) (mobSpawnerLogic.getPrevMobRotation()
					+ (mobSpawnerLogic.getMobRotation() - mobSpawnerLogic.getPrevMobRotation()) * (double) partialTicks)
					* 10.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(-30.0F, 1.0F, 0.0F, 0.0F);
			GlStateManager.translate(0.0F, -0.4F, 0.0F);
			GlStateManager.scale(f, f, f);
			entity.setLocationAndAngles(posX, posY, posZ, 0.0F, 0.0F);
			Minecraft.getMinecraft().getRenderManager().renderEntityWithPosYaw(entity, 0.0D, 0.0D, 0.0D, 0.0F,
					partialTicks);
		}

	}
}
