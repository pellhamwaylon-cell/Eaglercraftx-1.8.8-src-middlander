package net.minecraft.entity.ai.attributes;

import java.util.Collection;
import net.lax1dude.eaglercraft.v1_8.EaglercraftUUID;

public interface IAttributeInstance {
	IAttribute getAttribute();

	double getBaseValue();

	void setBaseValue(double var1);

	Collection<AttributeModifier> getModifiersByOperation(int var1);

	Collection<AttributeModifier> func_111122_c();

	boolean hasModifier(AttributeModifier var1);

	AttributeModifier getModifier(EaglercraftUUID var1);

	void applyModifier(AttributeModifier var1);

	void removeModifier(AttributeModifier var1);

	void removeAllModifiers();

	double getAttributeValue();
}
