package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderEntity extends Render<Entity> {
	public RenderEntity(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	public void doRender(Entity entity, double d0, double d1, double d2, float f, float f1) {
		GlStateManager.pushMatrix();
		renderOffsetAABB(entity.getEntityBoundingBox(), d0 - entity.lastTickPosX, d1 - entity.lastTickPosY,
				d2 - entity.lastTickPosZ);
		GlStateManager.popMatrix();
		super.doRender(entity, d0, d1, d2, f, f1);
	}

	protected ResourceLocation getEntityTexture(Entity var1) {
		return null;
	}
}
