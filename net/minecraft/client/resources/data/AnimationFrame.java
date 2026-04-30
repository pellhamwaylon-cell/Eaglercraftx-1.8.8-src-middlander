package net.minecraft.client.resources.data;

public class AnimationFrame {
	private final int frameIndex;
	private final int frameTime;

	public AnimationFrame(int parInt1) {
		this(parInt1, -1);
	}

	public AnimationFrame(int parInt1, int parInt2) {
		this.frameIndex = parInt1;
		this.frameTime = parInt2;
	}

	public boolean hasNoTime() {
		return this.frameTime == -1;
	}

	public int getFrameTime() {
		return this.frameTime;
	}

	public int getFrameIndex() {
		return this.frameIndex;
	}
}
