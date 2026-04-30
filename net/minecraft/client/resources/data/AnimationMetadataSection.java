package net.minecraft.client.resources.data;

import java.util.List;
import com.carrotsearch.hppc.IntHashSet;
import com.carrotsearch.hppc.IntSet;

public class AnimationMetadataSection implements IMetadataSection {
	private final List<AnimationFrame> animationFrames;
	private final int frameWidth;
	private final int frameHeight;
	private final int frameTime;
	private final boolean interpolate;

	public AnimationMetadataSection(List<AnimationFrame> parList, int parInt1, int parInt2, int parInt3,
			boolean parFlag) {
		this.animationFrames = parList;
		this.frameWidth = parInt1;
		this.frameHeight = parInt2;
		this.frameTime = parInt3;
		this.interpolate = parFlag;
	}

	public int getFrameHeight() {
		return this.frameHeight;
	}

	public int getFrameWidth() {
		return this.frameWidth;
	}

	public int getFrameCount() {
		return this.animationFrames.size();
	}

	public int getFrameTime() {
		return this.frameTime;
	}

	public boolean isInterpolate() {
		return this.interpolate;
	}

	private AnimationFrame getAnimationFrame(int parInt1) {
		return (AnimationFrame) this.animationFrames.get(parInt1);
	}

	public int getFrameTimeSingle(int parInt1) {
		AnimationFrame animationframe = this.getAnimationFrame(parInt1);
		return animationframe.hasNoTime() ? this.frameTime : animationframe.getFrameTime();
	}

	public boolean frameHasTime(int parInt1) {
		return !((AnimationFrame) this.animationFrames.get(parInt1)).hasNoTime();
	}

	public int getFrameIndex(int parInt1) {
		return ((AnimationFrame) this.animationFrames.get(parInt1)).getFrameIndex();
	}

	public IntSet getFrameIndexSet() {
		IntHashSet hashset = new IntHashSet();

		for (int i = 0, l = this.animationFrames.size(); i < l; ++i) {
			hashset.add(this.animationFrames.get(i).getFrameIndex());
		}

		return hashset;
	}
}
