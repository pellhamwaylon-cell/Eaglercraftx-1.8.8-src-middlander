package net.minecraft.client.renderer;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.util.Vec3;

public class RenderHelper {
	private static final Vec3 LIGHT0_POS = (new Vec3(0.20000000298023224D, 1.0D, -0.699999988079071D)).normalize();
	private static final Vec3 LIGHT1_POS = (new Vec3(-0.20000000298023224D, 1.0D, 0.699999988079071D)).normalize();

	public static void disableStandardItemLighting() {
		if (!DeferredStateManager.isInDeferredPass()) {
			GlStateManager.disableLighting();
			GlStateManager.disableMCLight(0);
			GlStateManager.disableMCLight(1);
			GlStateManager.disableColorMaterial();
		}
	}

	public static void enableStandardItemLighting() {
		if (!DeferredStateManager.isInDeferredPass()) {
			GlStateManager.enableLighting();
			GlStateManager.enableMCLight(0, 0.6f, LIGHT0_POS.xCoord, LIGHT0_POS.yCoord, LIGHT0_POS.zCoord, 0.0D);
			GlStateManager.enableMCLight(1, 0.6f, LIGHT1_POS.xCoord, LIGHT1_POS.yCoord, LIGHT1_POS.zCoord, 0.0D);
			GlStateManager.setMCLightAmbient(0.4f, 0.4f, 0.4f);
			GlStateManager.enableColorMaterial();
		}
	}

	public static void enableGUIStandardItemLighting() {
		if (!DeferredStateManager.isInDeferredPass()) {
			GlStateManager.pushMatrix();
			GlStateManager.rotate(-30.0F, 0.0F, 1.0F, 0.0F);
			GlStateManager.rotate(165.0F, 1.0F, 0.0F, 0.0F);
			enableStandardItemLighting();
			GlStateManager.popMatrix();
		}
	}
}
