package net.minecraft.client.renderer.block.statemap;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.model.ModelResourceLocation;

public class BlockStateMapper {
	public Map<Block, IStateMapper> blockStateMap = Maps.newIdentityHashMap();
	private Set<Block> setBuiltInBlocks = Sets.newIdentityHashSet();

	public void registerBlockStateMapper(Block parBlock, IStateMapper parIStateMapper) {
		this.blockStateMap.put(parBlock, parIStateMapper);
	}

	public void registerBuiltInBlocks(Block... parArrayOfBlock) {
		Collections.addAll(this.setBuiltInBlocks, parArrayOfBlock);
	}

	public Map<IBlockState, ModelResourceLocation> putAllStateModelLocations() {
		IdentityHashMap identityhashmap = Maps.newIdentityHashMap();

		for (Block block : Block.blockRegistry) {
			if (!this.setBuiltInBlocks.contains(block)) {
				identityhashmap.putAll(
						((IStateMapper) Objects.firstNonNull(this.blockStateMap.get(block), new DefaultStateMapper()))
								.putStateModelLocations(block));
			}
		}

		return identityhashmap;
	}
}
