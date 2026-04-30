package net.minecraft.client.renderer.culling;

import net.minecraft.util.AxisAlignedBB;

public class Frustum implements ICamera {
	private ClippingHelper clippingHelper;
	private double xPosition;
	private double yPosition;
	private double zPosition;

	public Frustum() {
		this(ClippingHelperImpl.getInstance());
	}

	public Frustum(ClippingHelper parClippingHelper) {
		this.clippingHelper = parClippingHelper;
	}

	public void setPosition(double d0, double d1, double d2) {
		this.xPosition = d0;
		this.yPosition = d1;
		this.zPosition = d2;
	}

	public boolean isBoxInFrustum(double parDouble1, double parDouble2, double parDouble3, double parDouble4,
			double parDouble5, double parDouble6) {
		return this.clippingHelper.isBoxInFrustum(parDouble1 - this.xPosition, parDouble2 - this.yPosition,
				parDouble3 - this.zPosition, parDouble4 - this.xPosition, parDouble5 - this.yPosition,
				parDouble6 - this.zPosition);
	}

	public boolean isBoundingBoxInFrustum(AxisAlignedBB axisalignedbb) {
		return this.isBoxInFrustum(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX,
				axisalignedbb.maxY, axisalignedbb.maxZ);
	}
}
