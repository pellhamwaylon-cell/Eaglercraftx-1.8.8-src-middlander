package net.minecraft.block;

import java.util.List;

import com.google.common.base.Predicate;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockNewLeaf extends BlockLeaves {
	public static PropertyEnum<BlockPlanks.EnumType> VARIANT;

	public BlockNewLeaf() {
		this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, BlockPlanks.EnumType.ACACIA)
				.withProperty(CHECK_DECAY, Boolean.valueOf(true)).withProperty(DECAYABLE, Boolean.valueOf(true)));
	}

	public static void bootstrapStates() {
		VARIANT = PropertyEnum.create("variant", BlockPlanks.EnumType.class, new Predicate<BlockPlanks.EnumType>() {
			public boolean apply(BlockPlanks.EnumType blockplanks$enumtype) {
				return blockplanks$enumtype.getMetadata() >= 4;
			}
		});
	}

	protected void dropApple(World world, BlockPos blockpos, IBlockState iblockstate, int i) {
		if (iblockstate.getValue(VARIANT) == BlockPlanks.EnumType.DARK_OAK && world.rand.nextInt(i) == 0) {
			spawnAsEntity(world, blockpos, new ItemStack(Items.apple, 1, 0));
		}
	}

	public int damageDropped(IBlockState iblockstate) {
		return ((BlockPlanks.EnumType) iblockstate.getValue(VARIANT)).getMetadata();
	}

	public int getDamageValue(World world, BlockPos blockpos) {
		IBlockState iblockstate = world.getBlockState(blockpos);
		return iblockstate.getBlock().getMetaFromState(iblockstate) & 3;
	}

	public void getSubBlocks(Item item, CreativeTabs var2, List<ItemStack> list) {
		list.add(new ItemStack(item, 1, 0));
		list.add(new ItemStack(item, 1, 1));
	}

	protected ItemStack createStackedBlock(IBlockState iblockstate) {
		return new ItemStack(Item.getItemFromBlock(this), 1,
				((BlockPlanks.EnumType) iblockstate.getValue(VARIANT)).getMetadata() - 4);
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(VARIANT, this.getWoodType(i))
				.withProperty(DECAYABLE, Boolean.valueOf((i & 4) == 0))
				.withProperty(CHECK_DECAY, Boolean.valueOf((i & 8) > 0));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		int i = 0;
		i = i | ((BlockPlanks.EnumType) iblockstate.getValue(VARIANT)).getMetadata() - 4;
		if (!((Boolean) iblockstate.getValue(DECAYABLE)).booleanValue()) {
			i |= 4;
		}

		if (((Boolean) iblockstate.getValue(CHECK_DECAY)).booleanValue()) {
			i |= 8;
		}

		return i;
	}

	public BlockPlanks.EnumType getWoodType(int i) {
		return BlockPlanks.EnumType.byMetadata((i & 3) + 4);
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { VARIANT, CHECK_DECAY, DECAYABLE });
	}

	public void harvestBlock(World world, EntityPlayer entityplayer, BlockPos blockpos, IBlockState iblockstate,
			TileEntity tileentity) {
		if (!world.isRemote && entityplayer.getCurrentEquippedItem() != null
				&& entityplayer.getCurrentEquippedItem().getItem() == Items.shears) {
			entityplayer.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
			spawnAsEntity(world, blockpos, new ItemStack(Item.getItemFromBlock(this), 1,
					((BlockPlanks.EnumType) iblockstate.getValue(VARIANT)).getMetadata() - 4));
		} else {
			super.harvestBlock(world, entityplayer, blockpos, iblockstate, tileentity);
		}
	}
}
