package net.minecraft.client;

import net.lax1dude.eaglercraft.v1_8.opengl.GlStateManager;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IProgressUpdate;

public class LoadingScreenRenderer implements IProgressUpdate {
	private String message = "";
	private Minecraft mc;
	private String currentlyDisplayedText = "";
	private long systemTime = Minecraft.getSystemTime();
	private boolean field_73724_e;

	public LoadingScreenRenderer(Minecraft mcIn) {
		this.mc = mcIn;
	}

	public void resetProgressAndMessage(String message) {
		this.field_73724_e = false;
		this.displayString(message);
	}

	public void displaySavingString(String message) {
		this.field_73724_e = true;
		this.displayString(message);
	}

	private void displayString(String message) {
		this.currentlyDisplayedText = message;
		if (this.mc.running) {
			GlStateManager.clear(256);
			GlStateManager.matrixMode(5889);
			GlStateManager.loadIdentity();
			GlStateManager.ortho(0.0D, mc.scaledResolution.getScaledWidth_double(),
					mc.scaledResolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
			GlStateManager.matrixMode(5888);
			GlStateManager.loadIdentity();
			GlStateManager.translate(0.0F, 0.0F, -200.0F);
		}
	}

	public void displayLoadingString(String message) {
		if (this.mc.running) {
			this.systemTime = 0L;
			this.message = message;
			this.setLoadingProgress(-1);
			this.systemTime = 0L;
		}
	}

	public void eaglerShow(String line1, String line2) {
		if (this.mc.running) {
			this.systemTime = 0L;
			this.currentlyDisplayedText = line1;
			this.message = line2;
			this.setLoadingProgress(-1);
			this.systemTime = 0L;
		}
	}

	public void eaglerShowRefreshResources() {
		eaglerShow(I18n.format("resourcePack.load.refreshing"), I18n.format("resourcePack.load.pleaseWait"));
	}

	public void setLoadingProgress(int progress) {
		if (this.mc.running) {
			long i = Minecraft.getSystemTime();
			if (i - this.systemTime >= 100L) {
				this.systemTime = i;
				ScaledResolution scaledresolution = mc.scaledResolution;
				int j = scaledresolution.getScaleFactor();
				int k = scaledresolution.getScaledWidth();
				int l = scaledresolution.getScaledHeight();
				GlStateManager.clear(256);
				GlStateManager.matrixMode(5889);
				GlStateManager.loadIdentity();
				GlStateManager.ortho(0.0D, scaledresolution.getScaledWidth_double(),
						scaledresolution.getScaledHeight_double(), 0.0D, 100.0D, 300.0D);
				GlStateManager.matrixMode(5888);
				GlStateManager.loadIdentity();
				GlStateManager.translate(0.0F, 0.0F, -200.0F);
				GlStateManager.clear(16640);
				GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

				Tessellator tessellator = Tessellator.getInstance();
				WorldRenderer worldrenderer = tessellator.getWorldRenderer();
				this.mc.getTextureManager().bindTexture(Gui.optionsBackground);
				float f = 32.0F;
				worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
				worldrenderer.pos(0.0D, (double) l, 0.0D).tex(0.0D, (double) ((float) l / f)).color(64, 64, 64, 255)
						.endVertex();
				worldrenderer.pos((double) k, (double) l, 0.0D).tex((double) ((float) k / f), (double) ((float) l / f))
						.color(64, 64, 64, 255).endVertex();
				worldrenderer.pos((double) k, 0.0D, 0.0D).tex((double) ((float) k / f), 0.0D).color(64, 64, 64, 255)
						.endVertex();
				worldrenderer.pos(0.0D, 0.0D, 0.0D).tex(0.0D, 0.0D).color(64, 64, 64, 255).endVertex();
				tessellator.draw();
				if (progress >= 0) {
					byte b0 = 100;
					byte b1 = 2;
					int i1 = k / 2 - b0 / 2;
					int j1 = l / 2 + 16;
					GlStateManager.disableTexture2D();
					worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
					worldrenderer.pos((double) i1, (double) j1, 0.0D).color(128, 128, 128, 255).endVertex();
					worldrenderer.pos((double) i1, (double) (j1 + b1), 0.0D).color(128, 128, 128, 255).endVertex();
					worldrenderer.pos((double) (i1 + b0), (double) (j1 + b1), 0.0D).color(128, 128, 128, 255)
							.endVertex();
					worldrenderer.pos((double) (i1 + b0), (double) j1, 0.0D).color(128, 128, 128, 255).endVertex();
					worldrenderer.pos((double) i1, (double) j1, 0.0D).color(128, 255, 128, 255).endVertex();
					worldrenderer.pos((double) i1, (double) (j1 + b1), 0.0D).color(128, 255, 128, 255).endVertex();
					worldrenderer.pos((double) (i1 + progress), (double) (j1 + b1), 0.0D).color(128, 255, 128, 255)
							.endVertex();
					worldrenderer.pos((double) (i1 + progress), (double) j1, 0.0D).color(128, 255, 128, 255)
							.endVertex();
					tessellator.draw();
					GlStateManager.enableTexture2D();
				}

				GlStateManager.enableBlend();
				GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
				this.mc.fontRendererObj.drawStringWithShadow(this.currentlyDisplayedText,
						(float) ((k - this.mc.fontRendererObj.getStringWidth(this.currentlyDisplayedText)) / 2),
						(float) (l / 2 - 4 - 16), 16777215);
				if (this.message != null) {
					this.mc.fontRendererObj.drawStringWithShadow(this.message,
							(float) ((k - this.mc.fontRendererObj.getStringWidth(this.message)) / 2),
							(float) (l / 2 - 4 + 8), 16777215);
				}
				this.mc.updateDisplay();
			}
		}
	}

	public void setDoneWorking() {
	}
}
