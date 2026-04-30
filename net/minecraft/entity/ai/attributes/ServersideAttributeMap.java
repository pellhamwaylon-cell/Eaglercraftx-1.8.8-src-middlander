package net.minecraft.entity.ai.attributes;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.minecraft.server.management.LowerStringMap;

public class ServersideAttributeMap extends BaseAttributeMap {
	private final Set<IAttributeInstance> attributeInstanceSet = Sets.newHashSet();
	protected final Map<String, IAttributeInstance> descriptionToAttributeInstanceMap = new LowerStringMap();

	public ModifiableAttributeInstance getAttributeInstance(IAttribute iattribute) {
		return (ModifiableAttributeInstance) super.getAttributeInstance(iattribute);
	}

	public ModifiableAttributeInstance getAttributeInstanceByName(String s) {
		IAttributeInstance iattributeinstance = super.getAttributeInstanceByName(s);
		if (iattributeinstance == null) {
			iattributeinstance = (IAttributeInstance) this.descriptionToAttributeInstanceMap.get(s);
		}

		return (ModifiableAttributeInstance) iattributeinstance;
	}

	public IAttributeInstance registerAttribute(IAttribute iattribute) {
		IAttributeInstance iattributeinstance = super.registerAttribute(iattribute);
		if (iattribute instanceof RangedAttribute && ((RangedAttribute) iattribute).getDescription() != null) {
			this.descriptionToAttributeInstanceMap.put(((RangedAttribute) iattribute).getDescription(),
					iattributeinstance);
		}

		return iattributeinstance;
	}

	protected IAttributeInstance func_180376_c(IAttribute iattribute) {
		return new ModifiableAttributeInstance(this, iattribute);
	}

	public void func_180794_a(IAttributeInstance iattributeinstance) {
		if (iattributeinstance.getAttribute().getShouldWatch()) {
			this.attributeInstanceSet.add(iattributeinstance);
		}

		for (IAttribute iattribute : this.field_180377_c.get(iattributeinstance.getAttribute())) {
			ModifiableAttributeInstance modifiableattributeinstance = this.getAttributeInstance(iattribute);
			if (modifiableattributeinstance != null) {
				modifiableattributeinstance.flagForUpdate();
			}
		}

	}

	public Set<IAttributeInstance> getAttributeInstanceSet() {
		return this.attributeInstanceSet;
	}

	public Collection<IAttributeInstance> getWatchedAttributes() {
		HashSet hashset = Sets.newHashSet();

		for (IAttributeInstance iattributeinstance : this.getAllAttributes()) {
			if (iattributeinstance.getAttribute().getShouldWatch()) {
				hashset.add(iattributeinstance);
			}
		}

		return hashset;
	}
}
