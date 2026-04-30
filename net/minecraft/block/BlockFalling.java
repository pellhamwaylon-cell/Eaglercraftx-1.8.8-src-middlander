package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BlockFalling extends Block {
	public static boolean fallInstantly;

	public BlockFalling() {
		super(Material.sand);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public BlockFalling(Material materialIn) {
		super(materialIn);
	}

	public void onBlockAdded(World world, BlockPos blockpos, IBlockState var3) {
		world.scheduleUpdate(blockpos, this, this.tickRate(world));
	}

	public void onNeighborBlockChange(World world, BlockPos blockpos, IBlockState var3, Block var4) {
		world.scheduleUpdate(blockpos, this, this.tickRate(world));
	}

	public void updateTick(World world, BlockPos blockpos, IBlockState var3, EaglercraftRandom var4) {
		if (!world.isRemote) {
			this.checkFallable(world, blockpos);
		}
	}

	private void checkFallable(World worldIn, BlockPos pos) {
		if (canFallInto(worldIn, pos.down()) && pos.getY() >= 0) {
			byte b0 = 32;
			if (!fallInstantly && worldIn.isAreaLoaded(pos.add(-b0, -b0, -b0), pos.add(b0, b0, b0))) {
				EntityFallingBlock entityfallingblock = new EntityFallingBlock(worldIn, (double) pos.getX() + 0.5D,
						(double) pos.getY(), (double) pos.getZ() + 0.5D, worldIn.getBlockState(pos));
				this.onStartFalling(entityfallingblock);
				worldIn.spawnEntityInWorld(entityfallingblock);
			} else {
				worldIn.setBlockToAir(pos);

				BlockPos blockpos;
				for (blockpos = pos.down(); canFallInto(worldIn, blockpos)
						&& blockpos.getY() > 0; blockpos = blockpos.down()) {
					;
				}

				if (blockpos.getY() > 0) {
					worldIn.setBlockState(blockpos.up(), this.getDefaultState());
				}
			}

		}
	}

	protected void onStartFalling(EntityFallingBlock var1) {
	}

	public int tickRate(World var1) {
		return 2;
	}

	public static boolean canFallInto(World worldIn, BlockPos pos) {
		Block block = worldIn.getBlockState(pos).getBlock();
		Material material = block.blockMaterial;
		return block == Blocks.fire || material == Material.air || material == Material.water
				|| material == Material.lava;
	}

	public void onEndFalling(World var1, BlockPos var2) {
	}
}
