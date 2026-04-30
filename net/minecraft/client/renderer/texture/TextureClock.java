package net.minecraft.client.renderer.texture;

import net.lax1dude.eaglercraft.v1_8.minecraft.EaglerTextureAtlasSprite;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class TextureClock extends EaglerTextureAtlasSprite {
	private double field_94239_h;
	private double field_94240_i;

	public TextureClock(String iconName) {
		super(iconName);
	}

	public void updateAnimation() {
		if (!this.framesTextureData.isEmpty()) {
			Minecraft minecraft = Minecraft.getMinecraft();
			double d0 = 0.0D;
			if (minecraft.theWorld != null && minecraft.thePlayer != null) {
				d0 = (double) minecraft.theWorld.getCelestialAngle(1.0F);
				if (!minecraft.theWorld.provider.isSurfaceWorld()) {
					d0 = Math.random();
				}
			}

			double d1;
			for (d1 = d0 - this.field_94239_h; d1 < -0.5D; ++d1) {
				;
			}

			while (d1 >= 0.5D) {
				--d1;
			}

			d1 = MathHelper.clamp_double(d1, -1.0D, 1.0D);
			this.field_94240_i += d1 * 0.1D;
			this.field_94240_i *= 0.8D;
			this.field_94239_h += this.field_94240_i;

			int i;
			for (i = (int) ((this.field_94239_h + 1.0D) * (double) this.framesTextureData.size())
					% this.framesTextureData
							.size(); i < 0; i = (i + this.framesTextureData.size()) % this.framesTextureData.size()) {
				;
			}

			if (i != this.frameCounter) {
				this.frameCounter = i;
				currentAnimUpdater = (mapWidth, mapHeight, mapLevel) -> {
					animationCache.copyFrameToTex2D(this.frameCounter, mapLevel, this.originX >> mapLevel,
							this.originY >> mapLevel, this.width >> mapLevel, this.height >> mapLevel, mapWidth,
							mapHeight);
				};
			} else {
				currentAnimUpdater = null;
			}

		} else {
			currentAnimUpdater = null;
		}
	}

}
