package net.minecraft.item;

import net.minecraft.block.Block;

public class ItemColored extends ItemBlock {
	private final Block coloredBlock;
	private String[] subtypeNames;

	public ItemColored(Block block, boolean hasSubtypes) {
		super(block);
		this.coloredBlock = block;
		if (hasSubtypes) {
			this.setMaxDamage(0);
			this.setHasSubtypes(true);
		}

	}

	public int getColorFromItemStack(ItemStack itemstack, int var2) {
		return this.coloredBlock.getRenderColor(this.coloredBlock.getStateFromMeta(itemstack.getMetadata()));
	}

	public int getMetadata(int i) {
		return i;
	}

	public ItemColored setSubtypeNames(String[] names) {
		this.subtypeNames = names;
		return this;
	}

	public String getUnlocalizedName(ItemStack itemstack) {
		if (this.subtypeNames == null) {
			return super.getUnlocalizedName(itemstack);
		} else {
			int i = itemstack.getMetadata();
			return i >= 0 && i < this.subtypeNames.length
					? super.getUnlocalizedName(itemstack) + "." + this.subtypeNames[i]
					: super.getUnlocalizedName(itemstack);
		}
	}
}
