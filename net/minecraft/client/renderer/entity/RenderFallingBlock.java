package net.minecraft.client.renderer.entity;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.VertexFormat;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.lax1dude.eaglercraft.v1_8.opengl.ext.deferred.DeferredStateManager;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class RenderFallingBlock extends Render<EntityFallingBlock> {
	public RenderFallingBlock(RenderManager renderManagerIn) {
		super(renderManagerIn);
		this.shadowSize = 0.5F;
	}

	public void doRender(EntityFallingBlock entityfallingblock, double d0, double d1, double d2, float f, float f1) {
		if (entityfallingblock.getBlock() != null) {
			this.bindTexture(TextureMap.locationBlocksTexture);
			IBlockState iblockstate = entityfallingblock.getBlock();
			Block block = iblockstate.getBlock();
			BlockPos blockpos = new BlockPos(entityfallingblock);
			World world = entityfallingblock.getWorldObj();
			if (iblockstate != world.getBlockState(blockpos) && block.getRenderType() != -1) {
				if (block.getRenderType() == 3) {
					GlStateManager.pushMatrix();
					GlStateManager.translate((float) d0, (float) d1, (float) d2);
					GlStateManager.disableLighting();
					Tessellator tessellator = Tessellator.getInstance();
					WorldRenderer worldrenderer = tessellator.getWorldRenderer();
					worldrenderer.begin(7, (DeferredStateManager.isDeferredRenderer()
					/* || DynamicLightsStateManager.isDynamicLightsRender() */) ? VertexFormat.BLOCK_SHADERS
							: DefaultVertexFormats.BLOCK);
					int i = blockpos.getX();
					int j = blockpos.getY();
					int k = blockpos.getZ();
					worldrenderer.setTranslation((double) ((float) (-i) - 0.5F), (double) (-j),
							(double) ((float) (-k) - 0.5F));
					BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft()
							.getBlockRendererDispatcher();
					IBakedModel ibakedmodel = blockrendererdispatcher.getModelFromBlockState(iblockstate, world,
							(BlockPos) null);
					blockrendererdispatcher.getBlockModelRenderer().renderModel(world, ibakedmodel, iblockstate,
							blockpos, worldrenderer, false);
					worldrenderer.setTranslation(0.0D, 0.0D, 0.0D);
					tessellator.draw();
					GlStateManager.enableLighting();
					GlStateManager.popMatrix();
					super.doRender(entityfallingblock, d0, d1, d2, f, f1);
				}
			}
		}
	}

	protected ResourceLocation getEntityTexture(EntityFallingBlock var1) {
		return TextureMap.locationBlocksTexture;
	}
}
