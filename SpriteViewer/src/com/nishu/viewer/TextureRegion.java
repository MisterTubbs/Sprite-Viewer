package com.nishu.viewer;

public class TextureRegion {

	public int x, y;
	public float size, textureSize;

	public TextureRegion(int x, int y, float size, float textureSize) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.textureSize = textureSize;
	}

	public void render() {
		Shape.createSquare(Main.WIDTH / 2 - 512, Main.HEIGHT / 2, size, size, new float[] { x * textureSize, y * textureSize },  textureSize);
	}
}
