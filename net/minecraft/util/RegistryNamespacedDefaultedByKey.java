package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V> {
	private final K defaultValueKey;
	private V defaultValue;

	public RegistryNamespacedDefaultedByKey(K parObject) {
		this.defaultValueKey = parObject;
	}

	public void register(int id, K parObject, V parObject2) {
		if (this.defaultValueKey.equals(parObject)) {
			this.defaultValue = parObject2;
		}

		super.register(id, parObject, parObject2);
	}

	public void validateKey() {
		Validate.notNull(this.defaultValueKey);
	}

	public V getObject(K name) {
		Object object = super.getObject(name);
		return (V) (object == null ? this.defaultValue : object);
	}

	public V getObjectById(int id) {
		Object object = super.getObjectById(id);
		return (V) (object == null ? this.defaultValue : object);
	}
}
