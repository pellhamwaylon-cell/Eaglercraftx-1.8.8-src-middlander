package net.minecraft.block;

import java.util.List;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;

public class BlockSand extends BlockFalling {
	public static PropertyEnum<BlockSand.EnumType> VARIANT;

	public BlockSand() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockSand.EnumType.SAND));
	}

	public static void bootstrapStates() {
		VARIANT = PropertyEnum.<BlockSand.EnumType>create("variant", BlockSand.EnumType.class);
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((BlockSand.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		BlockSand.EnumType[] blocks = BlockSand.EnumType.META_LOOKUP;
		for (int i = 0; i < blocks.length; ++i) {
			list.add(new ItemStack(item, 1, blocks[i].getMetadata()));
		}

	}

	public MapColor getMapColor(IBlockState iblockstate) {
		return ((BlockSand.EnumType) iblockstate.getValue(VARIANT)).getMapColor();
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(VARIANT, BlockSand.EnumType.byMetadata(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((BlockSand.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { VARIANT });
	}

	public static enum EnumType implements IStringSerializable {
		SAND(0, "sand", "default", MapColor.sandColor), RED_SAND(1, "red_sand", "red", MapColor.adobeColor);

		public static final BlockSand.EnumType[] META_LOOKUP = new BlockSand.EnumType[2];
		private final int meta;
		private final String name;
		private final MapColor mapColor;
		private final String unlocalizedName;

		private EnumType(int meta, String name, String unlocalizedName, MapColor mapColor) {
			this.meta = meta;
			this.name = name;
			this.mapColor = mapColor;
			this.unlocalizedName = unlocalizedName;
		}

		public int getMetadata() {
			return this.meta;
		}

		public String toString() {
			return this.name;
		}

		public MapColor getMapColor() {
			return this.mapColor;
		}

		public static BlockSand.EnumType byMetadata(int meta) {
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
			BlockSand.EnumType[] types = values();
			for (int i = 0; i < types.length; ++i) {
				META_LOOKUP[types[i].getMetadata()] = types[i];
			}

		}
	}
}
