package net.minecraft.util;

import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class RegistryNamespaced<K, V> extends RegistrySimple<K, V> implements IObjectIntIterable<V> {
	protected final ObjectIntIdentityMap<V> underlyingIntegerMap = new ObjectIntIdentityMap();
	protected final Map<V, K> inverseObjectRegistry;

	public RegistryNamespaced() {
		this.inverseObjectRegistry = ((BiMap) this.registryObjects).inverse();
	}

	public void register(int i, K object, V object1) {
		this.underlyingIntegerMap.put(object1, i);
		this.putObject(object, object1);
	}

	protected Map<K, V> createUnderlyingMap() {
		return HashBiMap.create();
	}

	public V getObject(K object) {
		return super.getObject(object);
	}

	public K getNameForObject(V parObject) {
		return (K) this.inverseObjectRegistry.get(parObject);
	}

	public boolean containsKey(K parObject) {
		return super.containsKey(parObject);
	}

	public int getIDForObject(V parObject) {
		return this.underlyingIntegerMap.get(parObject);
	}

	public V getObjectById(int i) {
		return (V) this.underlyingIntegerMap.getByValue(i);
	}

	public Iterator<V> iterator() {
		return this.underlyingIntegerMap.iterator();
	}
}
