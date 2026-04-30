package net.minecraft.client.resources.data;

public class FontMetadataSection implements IMetadataSection {
	private final float[] charWidths;
	private final float[] charLefts;
	private final float[] charSpacings;

	public FontMetadataSection(float[] parArrayOfFloat, float[] parArrayOfFloat2, float[] parArrayOfFloat3) {
		this.charWidths = parArrayOfFloat;
		this.charLefts = parArrayOfFloat2;
		this.charSpacings = parArrayOfFloat3;
	}
}
