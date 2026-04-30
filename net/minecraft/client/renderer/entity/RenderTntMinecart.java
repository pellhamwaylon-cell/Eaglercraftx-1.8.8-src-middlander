package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.entity.item.EntityMinecartTNT;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;

public class RenderTntMinecart extends RenderMinecart<EntityMinecartTNT> {
	public RenderTntMinecart(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	protected void func_180560_a(EntityMinecartTNT entityminecarttnt, float f, IBlockState iblockstate) {
		int i = entityminecarttnt.getFuseTicks();
		if (i > -1 && (float) i - f + 1.0F < 10.0F) {
			float f1 = 1.0F - ((float) i - f + 1.0F) / 10.0F;
			f1 = MathHelper.clamp_float(f1, 0.0F, 1.0F);
			f1 = f1 * f1;
			f1 = f1 * f1;
			float f2 = 1.0F + f1 * 0.3F;
			GlStateManager.scale(f2, f2, f2);
		}

		super.func_180560_a(entityminecarttnt, f, iblockstate);
		if (i > -1 && i / 5 % 2 == 0) {
			BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
			GlStateManager.disableTexture2D();
			GlStateManager.disableLighting();
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(770, 772);
			GlStateManager.color(1.0F, 1.0F, 1.0F, (1.0F - ((float) i - f + 1.0F) / 100.0F) * 0.8F);
			GlStateManager.pushMatrix();
			blockrendererdispatcher.renderBlockBrightness(Blocks.tnt.getDefaultState(), 1.0F);
			GlStateManager.popMatrix();
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.disableBlend();
			GlStateManager.enableLighting();
			GlStateManager.enableTexture2D();
		}

	}
}
