package net.minecraft.block;

import net.lax1dude.eaglercraft.v1_8.EaglercraftRandom;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockOre extends Block {
	public BlockOre() {
		this(Material.rock.getMaterialMapColor());
	}

	public BlockOre(MapColor parMapColor) {
		super(Material.rock, parMapColor);
		this.setCreativeTab(CreativeTabs.tabBlock);
	}

	public Item getItemDropped(IBlockState var1, EaglercraftRandom var2, int var3) {
		return this == Blocks.coal_ore ? Items.coal
				: (this == Blocks.diamond_ore ? Items.diamond
						: (this == Blocks.lapis_ore ? Items.dye
								: (this == Blocks.emerald_ore ? Items.emerald
										: (this == Blocks.quartz_ore ? Items.quartz : Item.getItemFromBlock(this)))));
	}

	public int quantityDropped(EaglercraftRandom random) {
		return this == Blocks.lapis_ore ? 4 + random.nextInt(5) : 1;
	}

	public int quantityDroppedWithBonus(int i, EaglercraftRandom random) {
		if (i > 0 && Item.getItemFromBlock(this) != this
				.getItemDropped((IBlockState) this.getBlockState().getValidStates().iterator().next(), random, i)) {
			int j = random.nextInt(i + 2) - 1;
			if (j < 0) {
				j = 0;
			}

			return this.quantityDropped(random) * (j + 1);
		} else {
			return this.quantityDropped(random);
		}
	}

	public void dropBlockAsItemWithChance(World world, BlockPos blockpos, IBlockState iblockstate, float f, int i) {
		super.dropBlockAsItemWithChance(world, blockpos, iblockstate, f, i);
		if (this.getItemDropped(iblockstate, world.rand, i) != Item.getItemFromBlock(this)) {
			int j = 0;
			if (this == Blocks.coal_ore) {
				j = MathHelper.getRandomIntegerInRange(world.rand, 0, 2);
			} else if (this == Blocks.diamond_ore) {
				j = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			} else if (this == Blocks.emerald_ore) {
				j = MathHelper.getRandomIntegerInRange(world.rand, 3, 7);
			} else if (this == Blocks.lapis_ore) {
				j = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
			} else if (this == Blocks.quartz_ore) {
				j = MathHelper.getRandomIntegerInRange(world.rand, 2, 5);
			}

			this.dropXpOnBlockBreak(world, blockpos, j);
		}

	}

	public int getDamageValue(World var1, BlockPos var2) {
		return 0;
	}

	public int damageDropped(IBlockState var1) {
		return this == Blocks.lapis_ore ? EnumDyeColor.BLUE.getDyeDamage() : 0;
	}
}
