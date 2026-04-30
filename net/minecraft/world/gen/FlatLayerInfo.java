package net.minecraft.world.gen;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;

public class FlatLayerInfo {
	private final int field_175902_a;
	private IBlockState field_175901_b;
	private int layerCount;
	private int layerMinimumY;

	public FlatLayerInfo(int parInt1, Block parBlock) {
		this(3, parInt1, parBlock);
	}

	public FlatLayerInfo(int parInt1, int parInt2, Block parBlock) {
		this.layerCount = 1;
		this.field_175902_a = parInt1;
		this.layerCount = parInt2;
		this.field_175901_b = parBlock.getDefaultState();
	}

	public FlatLayerInfo(int parInt1, int parInt2, Block parBlock, int parInt3) {
		this(parInt1, parInt2, parBlock);
		this.field_175901_b = parBlock.getStateFromMeta(parInt3);
	}

	public int getLayerCount() {
		return this.layerCount;
	}

	public IBlockState func_175900_c() {
		return this.field_175901_b;
	}

	private Block func_151536_b() {
		return this.field_175901_b.getBlock();
	}

	private int getFillBlockMeta() {
		return this.field_175901_b.getBlock().getMetaFromState(this.field_175901_b);
	}

	public int getMinY() {
		return this.layerMinimumY;
	}

	public void setMinY(int parInt1) {
		this.layerMinimumY = parInt1;
	}

	public String toString() {
		String s;
		if (this.field_175902_a >= 3) {
			ResourceLocation resourcelocation = (ResourceLocation) Block.blockRegistry
					.getNameForObject(this.func_151536_b());
			s = resourcelocation == null ? "null" : resourcelocation.toString();
			if (this.layerCount > 1) {
				s = this.layerCount + "*" + s;
			}
		} else {
			s = Integer.toString(Block.getIdFromBlock(this.func_151536_b()));
			if (this.layerCount > 1) {
				s = this.layerCount + "x" + s;
			}
		}

		int i = this.getFillBlockMeta();
		if (i > 0) {
			s = s + ":" + i;
		}

		return s;
	}
}
