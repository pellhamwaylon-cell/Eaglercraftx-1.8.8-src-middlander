package net.minecraft.pathfinding;

public class Path {
	private PathPoint[] pathPoints = new PathPoint[1024];
	private int count;

	public PathPoint addPoint(PathPoint point) {
		if (point.index >= 0) {
			throw new IllegalStateException("OW KNOWS!");
		} else {
			if (this.count == this.pathPoints.length) {
				PathPoint[] apathpoint = new PathPoint[this.count << 1];
				System.arraycopy(this.pathPoints, 0, apathpoint, 0, this.count);
				this.pathPoints = apathpoint;
			}

			this.pathPoints[this.count] = point;
			point.index = this.count;
			this.sortBack(this.count++);
			return point;
		}
	}

	public void clearPath() {
		this.count = 0;
	}

	public PathPoint dequeue() {
		PathPoint pathpoint = this.pathPoints[0];
		this.pathPoints[0] = this.pathPoints[--this.count];
		this.pathPoints[this.count] = null;
		if (this.count > 0) {
			this.sortForward(0);
		}

		pathpoint.index = -1;
		return pathpoint;
	}

	public void changeDistance(PathPoint parPathPoint, float parFloat1) {
		float f = parPathPoint.distanceToTarget;
		parPathPoint.distanceToTarget = parFloat1;
		if (parFloat1 < f) {
			this.sortBack(parPathPoint.index);
		} else {
			this.sortForward(parPathPoint.index);
		}

	}

	private void sortBack(int parInt1) {
		PathPoint pathpoint = this.pathPoints[parInt1];

		int i;
		for (float f = pathpoint.distanceToTarget; parInt1 > 0; parInt1 = i) {
			i = parInt1 - 1 >> 1;
			PathPoint pathpoint1 = this.pathPoints[i];
			if (f >= pathpoint1.distanceToTarget) {
				break;
			}

			this.pathPoints[parInt1] = pathpoint1;
			pathpoint1.index = parInt1;
		}

		this.pathPoints[parInt1] = pathpoint;
		pathpoint.index = parInt1;
	}

	private void sortForward(int parInt1) {
		PathPoint pathpoint = this.pathPoints[parInt1];
		float f = pathpoint.distanceToTarget;

		while (true) {
			int i = 1 + (parInt1 << 1);
			int j = i + 1;
			if (i >= this.count) {
				break;
			}

			PathPoint pathpoint1 = this.pathPoints[i];
			float f1 = pathpoint1.distanceToTarget;
			PathPoint pathpoint2;
			float f2;
			if (j >= this.count) {
				pathpoint2 = null;
				f2 = Float.POSITIVE_INFINITY;
			} else {
				pathpoint2 = this.pathPoints[j];
				f2 = pathpoint2.distanceToTarget;
			}

			if (f1 < f2) {
				if (f1 >= f) {
					break;
				}

				this.pathPoints[parInt1] = pathpoint1;
				pathpoint1.index = parInt1;
				parInt1 = i;
			} else {
				if (f2 >= f) {
					break;
				}

				this.pathPoints[parInt1] = pathpoint2;
				pathpoint2.index = parInt1;
				parInt1 = j;
			}
		}

		this.pathPoints[parInt1] = pathpoint;
		pathpoint.index = parInt1;
	}

	public boolean isPathEmpty() {
		return this.count == 0;
	}
}
