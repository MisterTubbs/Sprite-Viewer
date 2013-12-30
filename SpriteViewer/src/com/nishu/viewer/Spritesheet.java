package com.nishu.viewer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.nishu.utils.Texture;

public class Spritesheet {

	public Texture spritesheet;
	
	public int width, height;
	public float textureSize, oTextureSize;
	
	public Spritesheet(String path) {
		parse(path);
	}
	
	private void parse(String path) {
		spritesheet = Texture.loadTexture(path);
		width = spritesheet.width;
		height = spritesheet.height;
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("res/sheet.cfg"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            line = br.readLine();
	        }
	        oTextureSize = Float.valueOf(sb.toString());
	        textureSize = 1 / oTextureSize;
	    } catch (IOException e) {
			e.printStackTrace();
		} finally {
	        try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	}
	
	public void render() {
		spritesheet.bind();
		Shape.createSquare(Main.WIDTH - 512, 0, 512, 512, new float[] {0, 0},  1);
	}
	
	public void bind() {
		spritesheet.bind();
	}
	
	public void unbind() {
		spritesheet.unbind();
	}
}
