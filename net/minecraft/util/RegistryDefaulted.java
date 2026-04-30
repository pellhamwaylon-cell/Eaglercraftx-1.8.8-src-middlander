package net.minecraft.util;

public class RegistryDefaulted<K, V> extends RegistrySimple<K, V> {
	private final V defaultObject;

	public RegistryDefaulted(V defaultObjectIn) {
		this.defaultObject = defaultObjectIn;
	}

	public V getObject(K object) {
		Object object1 = super.getObject(object);
		return (V) (object1 == null ? this.defaultObject : object1);
	}
}
