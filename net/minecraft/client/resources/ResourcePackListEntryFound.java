package net.minecraft.client.resources;

import net.minecraft.client.gui.GuiScreenResourcePacks;

public class ResourcePackListEntryFound extends ResourcePackListEntry {
	private final ResourcePackRepository.Entry field_148319_c;

	public ResourcePackListEntryFound(GuiScreenResourcePacks resourcePacksGUIIn,
			ResourcePackRepository.Entry parEntry) {
		super(resourcePacksGUIIn);
		this.field_148319_c = parEntry;
	}

	protected void func_148313_c() {
		this.field_148319_c.bindTexturePackIcon(this.mc.getTextureManager());
	}

	protected int func_183019_a() {
		return this.field_148319_c.func_183027_f();
	}

	protected String func_148311_a() {
		return this.field_148319_c.getTexturePackDescription();
	}

	protected String func_148312_b() {
		return this.field_148319_c.getResourcePackEaglerDisplayName();
	}

	public ResourcePackRepository.Entry func_148318_i() {
		return this.field_148319_c;
	}

	@Override
	protected String getEaglerFolderName() {
		return field_148319_c.getResourcePackName();
	}
}
