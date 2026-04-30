package net.minecraft.block;

import java.util.List;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.StatCollector;

public class BlockPrismarine extends Block {
	public static PropertyEnum<BlockPrismarine.EnumType> VARIANT;
	public static final int ROUGH_META = BlockPrismarine.EnumType.ROUGH.getMetadata();
	public static final int BRICKS_META = BlockPrismarine.EnumType.BRICKS.getMetadata();
	public static final int DARK_META = BlockPrismarine.EnumType.DARK.getMetadata();

	public BlockPrismarine() {
		super(Material.rock);
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPrismarine.EnumType.ROUGH));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public static void bootstrapStates() {
		VARIANT = PropertyEnum.<BlockPrismarine.EnumType>create("variant", BlockPrismarine.EnumType.class);
	}

	public String getLocalizedName() {
		return StatCollector.translateToLocal(
				this.getUnlocalizedName() + "." + BlockPrismarine.EnumType.ROUGH.getUnlocalizedName() + ".name");
	}

	public MapColor getMapColor(IBlockState iblockstate) {
		return iblockstate.getValue(VARIANT) == BlockPrismarine.EnumType.ROUGH ? MapColor.cyanColor
				: MapColor.diamondColor;
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((BlockPrismarine.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((BlockPrismarine.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(VARIANT, BlockPrismarine.EnumType.byMetadata(i));
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, ROUGH_META));
		list.add(new ItemStack(item, 1, BRICKS_META));
		list.add(new ItemStack(item, 1, DARK_META));
	}

	public static enum EnumType implements IStringSerializable {
		ROUGH(0, "prismarine", "rough"), BRICKS(1, "prismarine_bricks", "bricks"), DARK(2, "dark_prismarine", "dark");

		private static final BlockPrismarine.EnumType[] META_LOOKUP = new BlockPrismarine.EnumType[3];
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

		public static BlockPrismarine.EnumType byMetadata(int meta) {
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
			BlockPrismarine.EnumType[] types = values();
			for (int i = 0; i < types.length; ++i) {
				META_LOOKUP[types[i].getMetadata()] = types[i];
			}

		}
	}
}
