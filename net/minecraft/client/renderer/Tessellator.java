package net.minecraft.client.renderer;

import net.lax1dude.eaglercraft.v1_8.opengl.RealOpenGLEnums;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldRenderer;
import net.lax1dude.eaglercraft.v1_8.opengl.WorldVertexBufferUploader;

public class Tessellator {
	private WorldRenderer worldRenderer;

	public static final int GL_TRIANGLES = RealOpenGLEnums.GL_TRIANGLES;
	public static final int GL_TRIANGLE_STRIP = RealOpenGLEnums.GL_TRIANGLE_STRIP;
	public static final int GL_TRIANGLE_FAN = RealOpenGLEnums.GL_TRIANGLE_FAN;
	public static final int GL_QUADS = RealOpenGLEnums.GL_QUADS;
	public static final int GL_LINES = RealOpenGLEnums.GL_LINES;
	public static final int GL_LINE_STRIP = RealOpenGLEnums.GL_LINE_STRIP;
	public static final int GL_LINE_LOOP = RealOpenGLEnums.GL_LINE_LOOP;

	private static final Tessellator instance = new Tessellator(2097152);

	public static Tessellator getInstance() {
		return instance;
	}

	public Tessellator(int bufferSize) {
		this.worldRenderer = new WorldRenderer(bufferSize);
	}

	public void draw() {
		this.worldRenderer.finishDrawing();
		WorldVertexBufferUploader.func_181679_a(this.worldRenderer);
	}

	public void uploadDisplayList(int displayList) {
		this.worldRenderer.finishDrawing();
		WorldVertexBufferUploader.uploadDisplayList(displayList, this.worldRenderer);
	}

	public WorldRenderer getWorldRenderer() {
		return this.worldRenderer;
	}
}
