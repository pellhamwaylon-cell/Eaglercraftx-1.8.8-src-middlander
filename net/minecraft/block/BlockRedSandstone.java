package net.minecraft.block;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockRedSandstone extends Block {
	public static PropertyEnum<BlockRedSandstone.EnumType> TYPE;

	public BlockRedSandstone() {
		super(Material.rock, BlockSand.EnumType.RED_SAND.getMapColor());
		this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, BlockRedSandstone.EnumType.DEFAULT));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public static void bootstrapStates() {
		TYPE = PropertyEnum.<BlockRedSandstone.EnumType>create("type", BlockRedSandstone.EnumType.class);
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((BlockRedSandstone.EnumType) iblockstate.getValue(TYPE)).getMetadata();
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		BlockRedSandstone.EnumType[] types = BlockRedSandstone.EnumType.META_LOOKUP;
		for (int i = 0; i < types.length; ++i) {
			list.add(new ItemStack(item, 1, types[i].getMetadata()));
		}

	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(TYPE, BlockRedSandstone.EnumType.byMetadata(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((BlockRedSandstone.EnumType) iblockstate.getValue(TYPE)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { TYPE });
	}

	public static enum EnumType implements IStringSerializable {
		DEFAULT(0, "red_sandstone", "default"), CHISELED(1, "chiseled_red_sandstone", "chiseled"),
		SMOOTH(2, "smooth_red_sandstone", "smooth");

		public static final BlockRedSandstone.EnumType[] META_LOOKUP = new BlockRedSandstone.EnumType[3];
		private final int meta;
		private final String name;
		private final String unlocalizedName;

		private EnumType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		public static BlockRedSandstone.EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public String getName() {
			return this.name;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		static {
			BlockRedSandstone.EnumType[] types = values();
			for (int i = 0; i < types.length; ++i) {
				META_LOOKUP[types[i].getMetadata()] = types[i];
			}

		}
	}
}
