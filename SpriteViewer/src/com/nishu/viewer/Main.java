package com.nishu.viewer;

import com.nishu.utils.Color4f;
import com.nishu.utils.GameLoop;
import com.nishu.utils.Screen;
import com.nishu.utils.ScreenTools;
import com.nishu.utils.Window;

import static org.lwjgl.opengl.GL11.*;

public class Main extends Screen{

	public static final int WIDTH = 1280;
	public static final int HEIGHT = 720;
	
	private GameLoop loop;
	private Viewer viewer;
	
	public Main() {
		loop = new GameLoop();
		loop.setScreen(this);
		loop.start(60);
	}

	@Override
	public void init() {
		viewer = new Viewer();
	}

	@Override
	public void initGL() {
		glEnable(GL_TEXTURE_2D);
	}

	@Override
	public void render() {
		ScreenTools.clearScreen(false, Color4f.GRAY);
		viewer.render();
	}

	@Override
	public void update() {
		viewer.update();
	}
	
	@Override
	public void dispose() {
		viewer.dispose();
	}

	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, "SpriteViewer", false, false);
		new Main();
	}
}
