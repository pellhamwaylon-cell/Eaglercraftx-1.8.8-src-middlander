package net.minecraft.client.renderer.block.model;

import org.json.JSONException;
import org.json.JSONObject;

import net.lax1dude.eaglercraft.v1_8.json.JSONTypeDeserializer;
import net.lax1dude.eaglercraft.v1_8.json.JSONTypeProvider;
import net.minecraft.util.EnumFacing;

public class BlockPartFace {
	public static final EnumFacing FACING_DEFAULT = null;
	public final EnumFacing cullFace;
	public final int tintIndex;
	public final String texture;
	public final BlockFaceUV blockFaceUV;

	public BlockPartFace(EnumFacing cullFaceIn, int tintIndexIn, String textureIn, BlockFaceUV blockFaceUVIn) {
		this.cullFace = cullFaceIn;
		this.tintIndex = tintIndexIn;
		this.texture = textureIn;
		this.blockFaceUV = blockFaceUVIn;
	}

	public static class Deserializer implements JSONTypeDeserializer<JSONObject, BlockPartFace> {
		public BlockPartFace deserialize(JSONObject jsonobject) throws JSONException {
			EnumFacing enumfacing = this.parseCullFace(jsonobject);
			int i = this.parseTintIndex(jsonobject);
			String s = this.parseTexture(jsonobject);
			BlockFaceUV blockfaceuv = JSONTypeProvider.deserialize(jsonobject, BlockFaceUV.class);
			return new BlockPartFace(enumfacing, i, s, blockfaceuv);
		}

		protected int parseTintIndex(JSONObject parJsonObject) {
			return parJsonObject.optInt("tintindex", -1);
		}

		private String parseTexture(JSONObject parJsonObject) {
			return parJsonObject.getString("texture");
		}

		private EnumFacing parseCullFace(JSONObject parJsonObject) {
			String s = parJsonObject.optString("cullface", "");
			return EnumFacing.byName(s);
		}
	}
}
