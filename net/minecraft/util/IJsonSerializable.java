package net.minecraft.util;

public interface IJsonSerializable {
	void fromJson(Object var1);

	Object getSerializableElement();
}
