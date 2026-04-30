package net.minecraft.util;

import java.util.Iterator;
import java.util.List;

import com.carrotsearch.hppc.ObjectIntIdentityHashMap;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

public class ObjectIntIdentityMap<T> implements IObjectIntIterable<T> {
	private final ObjectIntIdentityHashMap<T> identityMap = new ObjectIntIdentityHashMap<>(512);
	private final List<T> objectList = Lists.newArrayList();

	public void put(T key, int value) {
		this.identityMap.put(key, value);

		while (this.objectList.size() <= value) {
			this.objectList.add((T) null);
		}

		this.objectList.set(value, key);
	}

	public int get(T key) {
		return this.identityMap.getOrDefault(key, -1);
	}

	public final T getByValue(int value) {
		return (T) (value >= 0 && value < this.objectList.size() ? this.objectList.get(value) : null);
	}

	public Iterator<T> iterator() {
		return Iterators.filter(this.objectList.iterator(), Predicates.notNull());
	}
}
