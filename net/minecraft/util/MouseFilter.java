package net.minecraft.util;

public class MouseFilter {
	private float field_76336_a;
	private float field_76334_b;
	private float field_76335_c;

	public float smooth(float parFloat1, float parFloat2) {
		this.field_76336_a += parFloat1;
		parFloat1 = (this.field_76336_a - this.field_76334_b) * parFloat2;
		this.field_76335_c += (parFloat1 - this.field_76335_c) * 0.5F;
		if (parFloat1 > 0.0F && parFloat1 > this.field_76335_c || parFloat1 < 0.0F && parFloat1 < this.field_76335_c) {
			parFloat1 = this.field_76335_c;
		}

		this.field_76334_b += parFloat1;
		return parFloat1;
	}

	public void reset() {
		this.field_76336_a = 0.0F;
		this.field_76334_b = 0.0F;
		this.field_76335_c = 0.0F;
	}
}
