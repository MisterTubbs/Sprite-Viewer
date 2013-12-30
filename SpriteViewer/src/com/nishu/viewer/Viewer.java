package com.nishu.viewer;

import org.lwjgl.input.Keyboard;

import com.nishu.utils.Color4f;
import com.nishu.utils.Font;
import com.nishu.utils.ScreenTools;
import com.nishu.utils.Text;
import com.nishu.utils.Texture;

import static org.lwjgl.opengl.GL11.*;

public class Viewer {

	private Spritesheet sheet;
	private Texture crosshair;
	private Font font;

	private TextureRegion[][] textures;
	private TextureRegion currentTexture;

	private int moveSpeed, x, y, ix, iy;
	private float tx = 0, ty = 0;

	public Viewer() {
		init();
		intiGL();
	}

	private void intiGL() {
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}

	public void init() {
		x = 768;
		y = 480;
		sheet = new Spritesheet("sheet.png");
		font = new Font();
		font.loadFont("Default", "pixel.png");
		textures = new TextureRegion[(int) Math.ceil((sheet.width / sheet.oTextureSize))][(int) Math.ceil((sheet.height / sheet.oTextureSize))];
		moveSpeed = (int) sheet.oTextureSize * 2;
		
		initTextures();
	}

	private void initTextures() {
		crosshair = Texture.loadTexture("crosshair.png");
		for (int x = 0; x < Math.ceil(sheet.width / sheet.oTextureSize); x++) {
			for (int y = 0; y < Math.ceil(sheet.height / sheet.oTextureSize); y++) {
				textures[x][y] = new TextureRegion(x, y, 128, sheet.textureSize);
			}
		}
		currentTexture = textures[0][0];
	}

	public void update() {
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
					ix++;
					x += moveSpeed;
					tx += sheet.textureSize;
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
					ix--;
					x -= moveSpeed;
					tx -= sheet.textureSize;
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
					iy++;
					y -= moveSpeed;
					ty += sheet.textureSize;
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
					iy--;
					y += moveSpeed;
					ty -= sheet.textureSize;
				}
			}
		}
		if (x < 768)
			x = 768;
		if (x > 1248)
			x = 1248;
		if (y < 0)
			y = 0;
		if (y > 480)
			y = 480;
		if (tx < 0)
			tx = 0;
		if (tx > 1 - sheet.textureSize)
			tx = 1 - sheet.textureSize;
		if (ty < 0)
			ty = 0;
		if (ty > 1 - sheet.textureSize)
			ty = 1 - sheet.textureSize;
		getCurrentTexture();
	}

	private void getCurrentTexture() {
		if (ix < 0)
			ix = 0;
		if (ix > (sheet.width / sheet.oTextureSize) - 1)
			ix = (int) (sheet.width / sheet.oTextureSize) - 1;
		if (iy < 0)
			iy = 0;
		if (iy > (sheet.height / sheet.oTextureSize) - 1)
			iy = (int) (sheet.height / sheet.oTextureSize) - 1;
		currentTexture = textures[ix][iy];
	}

	public void render() {
		ScreenTools.renderOrtho(0, Main.WIDTH, 0, Main.HEIGHT);
		sheet.render();
		currentTexture.render();

		crosshair.bind();
		Shape.createSquare(x, y, sheet.oTextureSize * 2, sheet.oTextureSize * 2, new float[] { 0, 0 }, 1);

		renderText();
	}

	private void renderText() {
		ScreenTools.render2D();
		font.bind();
		Text.renderString(font, "Sheet width: " + sheet.width, -1f, 0.45f, 1, Color4f.WHITE);
		Text.renderString(font, "Sheet height: " + sheet.height, -1f, 0.4f, 1, Color4f.WHITE);
		Text.renderString(font, "Texture size: " + sheet.textureSize + "F" + " (" + sheet.oTextureSize + "P)", -1f, 0.35f, 1, Color4f.WHITE);
		Text.renderString(font, "Texture x: " + tx + " | " + "Texture y: " + ty, -1f, 0.3f, 1, Color4f.WHITE);
	}

	public void dispose() {
		sheet.spritesheet.delete();
	}
}
