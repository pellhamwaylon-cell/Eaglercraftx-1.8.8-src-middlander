package net.minecraft.util;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import com.google.common.collect.Maps;

public class MapPopulator {
	public static <K, V> Map<K, V> createMap(Iterable<K> keys, Iterable<V> values) {
		return populateMap(keys, values, Maps.newLinkedHashMap());
	}

	public static <K, V> Map<K, V> populateMap(Iterable<K> keys, Iterable<V> values, Map<K, V> map) {
		Iterator iterator = values.iterator();

		for (Object object : keys) {
			map.put((K) object, (V) iterator.next());
		}

		if (iterator.hasNext()) {
			throw new NoSuchElementException();
		} else {
			return map;
		}
	}
}
