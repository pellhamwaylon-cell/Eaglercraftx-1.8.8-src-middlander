package net.minecraft.client.particle;

import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class EntityHugeExplodeFX extends EntityFX {
	private int timeSinceStart;
	private int maximumTime = 8;

	protected EntityHugeExplodeFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double parDouble1,
			double parDouble2, double parDouble3) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0D, 0.0D, 0.0D);
	}

	public void renderParticle(WorldRenderer var1, Entity var2, float var3, float var4, float var5, float var6,
			float var7, float var8) {
	}

	public void onUpdate() {
		for (int i = 0; i < 6; ++i) {
			double d0 = this.posX + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
			double d1 = this.posY + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
			double d2 = this.posZ + (this.rand.nextDouble() - this.rand.nextDouble()) * 4.0D;
			this.worldObj.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE, d0, d1, d2,
					(double) ((float) this.timeSinceStart / (float) this.maximumTime), 0.0D, 0.0D, new int[0]);
		}

		++this.timeSinceStart;
		if (this.timeSinceStart == this.maximumTime) {
			this.setDead();
		}

	}

	public int getFXLayer() {
		return 1;
	}

	public static class Factory implements IParticleFactory {
		public EntityFX getEntityFX(int var1, World world, double d0, double d1, double d2, double d3, double d4,
				double d5, int... var15) {
			return new EntityHugeExplodeFX(world, d0, d1, d2, d3, d4, d5);
		}
	}
}
