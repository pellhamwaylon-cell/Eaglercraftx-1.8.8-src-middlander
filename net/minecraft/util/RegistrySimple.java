package net.minecraft.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.Validate;

import com.google.common.collect.Maps;

import net.lax1dude.eaglercraft.v1_8.log4j.LogManager;
import net.lax1dude.eaglercraft.v1_8.log4j.Logger;

public class RegistrySimple<K, V> implements IRegistry<K, V> {
	private static final Logger logger = LogManager.getLogger();
	protected final Map<K, V> registryObjects = this.createUnderlyingMap();

	protected Map<K, V> createUnderlyingMap() {
		return Maps.newHashMap();
	}

	public V getObject(K object) {
		return (V) this.registryObjects.get(object);
	}

	public void putObject(K object, V object1) {
		Validate.notNull(object);
		Validate.notNull(object1);
		if (this.registryObjects.containsKey(object)) {
			logger.debug("Adding duplicate key \'" + object + "\' to registry");
		}

		this.registryObjects.put(object, object1);
	}

	public Set<K> getKeys() {
		return Collections.unmodifiableSet(this.registryObjects.keySet());
	}

	public boolean containsKey(K object) {
		return this.registryObjects.containsKey(object);
	}

	public Iterator<V> iterator() {
		return this.registryObjects.values().iterator();
	}
}
