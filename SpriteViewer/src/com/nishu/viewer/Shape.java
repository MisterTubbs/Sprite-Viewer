package com.nishu.viewer;

import static org.lwjgl.opengl.GL11.*;

public class Shape {
	
	public static void createSquare(int sx, int sy, float width, float height, float[] texCoords, float texSize) {
		glBegin(GL_QUADS);
		glTexCoord2f(texCoords[0], texCoords[1]);
		glVertex2f(sx, sy + height);
		
		glTexCoord2f(texCoords[0] + texSize, texCoords[1]);
		glVertex2f(sx + width, sy + height);
		
		glTexCoord2f(texCoords[0] + texSize, texCoords[1] + texSize);
		glVertex2f(sx + width, sy);
		
		glTexCoord2f(texCoords[0], texCoords[1] + texSize);
		glVertex2f(sx, sy);
		glEnd();
	}
	
	public static void createSquare(int sx, int sy, int width, int height) {
		glBegin(GL_QUADS);
		glVertex2f(sx, sy + height);
		glVertex2f(sx + width, sy + height);
		glVertex2f(sx + width, sy);
		glVertex2f(sx, sy);
		glEnd();
	}
	
	public static void createLineSquare(int sx, int sy, int width, int height) {
		glColor4f(1, 0, 0, 1);
		glBegin(GL_LINES);
		glVertex2f(sx, sy);
		glVertex2f(sx + width, sy);
		
		glVertex2f(sx + width, sy);
		glVertex2f(sx + width, sy + height);

		glVertex2f(sx + width, sy + height);
		glVertex2f(sx, sy + height);
		
		glVertex2f(sx, sy + height);
		glVertex2f(sx, sy);
		glEnd();
	}
}