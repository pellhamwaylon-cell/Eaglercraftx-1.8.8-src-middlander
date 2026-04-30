package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockPressurePlateWeighted extends BlockBasePressurePlate {
	public static final PropertyInteger POWER = PropertyInteger.create("power", 0, 15);
	private final int field_150068_a;

	protected BlockPressurePlateWeighted(Material parMaterial, int parInt1) {
		this(parMaterial, parInt1, parMaterial.getMaterialMapColor());
	}

	protected BlockPressurePlateWeighted(Material parMaterial, int parInt1, MapColor parMapColor) {
		super(parMaterial, parMapColor);
		this.setDefaultState(this.blockState.getBaseState().withProperty(POWER, Integer.valueOf(0)));
		this.field_150068_a = parInt1;
	}

	protected int computeRedstoneStrength(World world, BlockPos blockpos) {
		int i = Math.min(world.getEntitiesWithinAABB(Entity.class, this.getSensitiveAABB(blockpos)).size(),
				this.field_150068_a);
		if (i > 0) {
			float f = (float) Math.min(this.field_150068_a, i) / (float) this.field_150068_a;
			return MathHelper.ceiling_float_int(f * 15.0F);
		} else {
			return 0;
		}
	}

	protected int getRedstoneStrength(IBlockState iblockstate) {
		return ((Integer) iblockstate.getValue(POWER)).intValue();
	}

	protected IBlockState setRedstoneStrength(IBlockState iblockstate, int i) {
		return iblockstate.withProperty(POWER, Integer.valueOf(i));
	}

	public int tickRate(World var1) {
		return 10;
	}

	public IBlockState getStateFromMeta(int i) {
		return this.getDefaultState().withProperty(POWER, Integer.valueOf(i));
	}

	public int getMetaFromState(IBlockState iblockstate) {
		return ((Integer) iblockstate.getValue(POWER)).intValue();
	}

	protected BlockState createBlockState() {
		return new BlockState(this, new IProperty[] { POWER });
	}
}
