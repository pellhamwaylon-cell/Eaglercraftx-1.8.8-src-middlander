package net.minecraft.block;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

public abstract class BlockStoneSlab extends BlockSlab {
	public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
	public static PropertyEnum<BlockStoneSlab.EnumType> VARIANT;

	public BlockStoneSlab() {
		super(Material.rock);
		IBlockState iblockstate = this.blockState.getBaseState();
		if (this.isDouble()) {
			iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf(false));
		} else {
			iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
		}

		this.setDefaultState(iblockstate.withProperty(VARIANT, BlockStoneSlab.EnumType.STONE));
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public static void bootstrapStates() {
		VARIANT = PropertyEnum.<BlockStoneSlab.EnumType>create("variant", BlockStoneSlab.EnumType.class);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Item.getItemFromBlock(Blocks.stone_slab);
	}

	public Item getItem(World var1, BlockPos var2) {
		return Item.getItemFromBlock(Blocks.stone_slab);
	}

	public String getUnlocalizedName(int i) {
		return super.getUnlocalizedName() + "." + BlockStoneSlab.EnumType.byMetadata(i).getUnlocalizedName();
	}

	public IProperty<?> getVariantProperty() {
		return VARIANT;
	}

	public Object getVariant(ItemStack itemstack) {
		return BlockStoneSlab.EnumType.byMetadata(itemstack.getMetadata() & 7);
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		if (item != Item.getItemFromBlock(Blocks.double_stone_slab)) {
			BlockStoneSlab.EnumType[] types = BlockStoneSlab.EnumType.META_LOOKUP;
			for (int i = 0; i < types.length; ++i) {
				BlockStoneSlab.EnumType blockstoneslab$enumtype = types[i];
				if (blockstoneslab$enumtype != BlockStoneSlab.EnumType.WOOD) {
					list.add(new ItemStack(item, 1, blockstoneslab$enumtype.getMetadata()));
				}
			}

		}
	}

	public IBlockState getStateFromMeta(int i) {
		IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT,
				BlockStoneSlab.EnumType.byMetadata(i & 7));
		if (this.isDouble()) {
			iblockstate = iblockstate.withProperty(SEAMLESS, Boolean.valueOf((i & 8) != 0));
		} else {
			iblockstate = iblockstate.withProperty(HALF,
					(i & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState iblockstate) {
		int i = 0;
		i = i | ((BlockStoneSlab.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
		if (this.isDouble()) {
			if (((Boolean) iblockstate.getValue(SEAMLESS)).booleanValue()) {
				i |= 8;
			}
		} else if (iblockstate.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
			i |= 8;
		}

		return i;
	}

	protected BlockState createBlockState() {
		return this.isDouble() ? new BlockState(this, new IProperty[] { SEAMLESS, VARIANT })
				: new BlockState(this, new IProperty[] { HALF, VARIANT });
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((BlockStoneSlab.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	public MapColor getMapColor(IBlockState iblockstate) {
		return ((BlockStoneSlab.EnumType) iblockstate.getValue(VARIANT)).func_181074_c();
	}

	public static enum EnumType implements IStringSerializable {
		STONE(0, MapColor.stoneColor, "stone"), SAND(1, MapColor.sandColor, "sandstone", "sand"),
		WOOD(2, MapColor.woodColor, "wood_old", "wood"), COBBLESTONE(3, MapColor.stoneColor, "cobblestone", "cobble"),
		BRICK(4, MapColor.redColor, "brick"), SMOOTHBRICK(5, MapColor.stoneColor, "stone_brick", "smoothStoneBrick"),
		NETHERBRICK(6, MapColor.netherrackColor, "nether_brick", "netherBrick"),
		QUARTZ(7, MapColor.quartzColor, "quartz");

		public static final BlockStoneSlab.EnumType[] META_LOOKUP = new BlockStoneSlab.EnumType[8];
		private final int meta;
		private final MapColor field_181075_k;
		private final String name;
		private final String unlocalizedName;

		private EnumType(int parInt2, MapColor parMapColor, String parString2) {
			this(parInt2, parMapColor, parString2, parString2);
		}

		private EnumType(int parInt2, MapColor parMapColor, String parString2, String parString3) {
			this.meta = parInt2;
			this.field_181075_k = parMapColor;
			this.name = parString2;
			this.unlocalizedName = parString3;
		}

		public int getMetadata() {
			return this.meta;
		}

		public MapColor func_181074_c() {
			return this.field_181075_k;
		}

		public String toString() {
			return this.name;
		}

		public static BlockStoneSlab.EnumType byMetadata(int meta) {
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
			BlockStoneSlab.EnumType[] types = values();
			for (int i = 0; i < types.length; ++i) {
				META_LOOKUP[types[i].getMetadata()] = types[i];
			}

		}
	}
}
