package net.minecraft.block;

import java.util.List;
import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class BlockBrewingStand extends BlockContainer {
	public static final PropertyBool[] HAS_BOTTLE = new PropertyBool[] { PropertyBool.create("has_bottle_0"),
			PropertyBool.create("has_bottle_1"), PropertyBool.create("has_bottle_2") };

	public BlockBrewingStand() {
		super(Material.iron);
		this.setDefaultState(this.blockState.getBaseState().withProperty(HAS_BOTTLE[0], Boolean.valueOf(false))
				.withProperty(HAS_BOTTLE[1], Boolean.valueOf(false))
				.withProperty(HAS_BOTTLE[2], Boolean.valueOf(false)));
	}

	public String getLocalizedName() {
		return StatCollector.translateToLocal("item.brewingStand.name");
	}

	public boolean isOpaqueCube() {
		return false;
	}

	public int getRenderType() {
		return 3;
	}

	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBrewingStand();
	}

	public boolean isFullCube() {
		return false;
	}

	public void addCollisionBoxesToList(World world, BlockPos blockpos, IBlockState iblockstate,
			AxisAlignedBB axisalignedbb, List<AxisAlignedBB> list, Entity entity) {
		this.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.875F, 0.5625F);
		super.addCollisionBoxesToList(world, blockpos, iblockstate, axisalignedbb, list, entity);
		this.setBlockBoundsForItemRender();
		super.addCollisionBoxesToList(world, blockpos, iblockstate, axisalignedbb, list, entity);
	}

	public void setBlockBoundsForItemRender() {
		this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
	}

	public boolean onBlockActivated(World world, BlockPos blockpos, IBlockState var3, EntityPlayer entityplayer,
			EnumFacing var5, float var6, float var7, float var8) {
		if (!world.isRemote) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityBrewingStand) {
				entityplayer.displayGUIChest((TileEntityBrewingStand) tileentity);
				entityplayer.triggerAchievement(StatList.field_181729_M);
			}
		}
		return true;
	}

	public void onBlockPlacedBy(World world, BlockPos blockpos, IBlockState var3, EntityLivingBase var4,
			ItemStack itemstack) {
		if (itemstack.hasDisplayName()) {
			TileEntity tileentity = world.getTileEntity(blockpos);
			if (tileentity instanceof TileEntityBrewingStand) {
				((TileEntityBrewingStand) tileentity).setName(itemstack.getDisplayName());
			}
		}

	}

	public void randomDisplayTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom random) {
		double d0 = (double) ((float) blockpos.getX() + 0.4F + random.nextFloat() * 0.2F);
		double d1 = (double) ((float) blockpos.getY() + 0.7F + random.nextFloat() * 0.3F);
		double d2 = (double) ((float) blockpos.getZ() + 0.4F + random.nextFloat() * 0.2F);
		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0D, 0.0D, 0.0D, new int[0]);
	}

	public void breakBlock(World world, BlockPos blockpos, IBlockState iblockstate) {
		TileEntity tileentity = world.getTileEntity(blockpos);
		if (tileentity instanceof TileEntityBrewingStand) {
			InventoryHelper.dropInventoryItems(world, blockpos, (TileEntityBrewingStand) tileentity);
		}

		super.breakBlock(world, blockpos, iblockstate);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return Items.brewing_stand;
	}

	public Item getItem(World var1, BlockPos var2) {
		return Items.brewing_stand;
	}

	public boolean hasComparatorInputOverride() {
		return true;
	}

	public int getComparatorInputOverride(World world, BlockPos blockpos) {
		return Container.calcRedstone(world.getTileEntity(blockpos));
	}

	public EnumWorldBlockLayer getBlockLayer() {
		return EnumWorldBlockLayer.CUTOUT;
	}

	public IBlockState getStateFromMeta(int i) {
		IBlockState iblockstate = this.getDefaultState();

		for (int j = 0; j < 3; ++j) {
			iblockstate = iblockstate.withProperty(HAS_BOTTLE[j], Boolean.valueOf((i & 1 << j) > 0));
		}

		return iblockstate;
	}

	public int getMetaFromState(IBlockState iblockstate) {
		int i = 0;

		for (int j = 0; j < 3; ++j) {
			if (((Boolean) iblockstate.getValue(HAS_BOTTLE[j])).booleanValue()) {
				i |= 1 << j;
			}
		}

		return i;
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { HAS_BOTTLE[0], HAS_BOTTLE[1], HAS_BOTTLE[2] });
	}
}
