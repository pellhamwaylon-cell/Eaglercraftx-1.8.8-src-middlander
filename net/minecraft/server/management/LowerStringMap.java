package net.minecraft.server.management;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;

public class LowerStringMap<V> implements Map<String, V> {
	private final Map<String, V> internalMap = Maps.newLinkedHashMap();

	public int size() {
		return this.internalMap.size();
	}

	public boolean isEmpty() {
		return this.internalMap.isEmpty();
	}

	public boolean containsKey(Object parObject) {
		return this.internalMap.containsKey(parObject.toString().toLowerCase());
	}

	public boolean containsValue(Object parObject) {
		return this.internalMap.containsKey(parObject);
	}

	public V get(Object parObject) {
		return (V) this.internalMap.get(parObject.toString().toLowerCase());
	}

	public V put(String parString1, V parObject) {
		return (V) this.internalMap.put(parString1.toLowerCase(), parObject);
	}

	public V remove(Object object) {
		return (V) this.internalMap.remove(object.toString().toLowerCase());
	}

	public void putAll(Map<? extends String, ? extends V> parMap) {
		for (Entry entry : parMap.entrySet()) {
			this.put((String) entry.getKey(), (V) entry.getValue());
		}

	}

	public void clear() {
		this.internalMap.clear();
	}

	public Set<String> keySet() {
		return this.internalMap.keySet();
	}

	public Collection<V> values() {
		return this.internalMap.values();
	}

	public Set<Entry<String, V>> entrySet() {
		return this.internalMap.entrySet();
	}
}
