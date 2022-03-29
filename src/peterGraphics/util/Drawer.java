package peterGraphics.util;

import vectorLibrary.Vector2;
import vectorLibrary.Vector2D;

import java.awt.Graphics;

public class Drawer {
	
	/**
	 * Draws a line from p0 to p1 relitive to pos
	 * @param g Java.awt.Graphics object to draw on
	 * @param p0 The first point of the line
	 * @param p1 The second point of the line
	 * @param pos Origin for line
	 * @param camera Camera to draw from
	 */
	public static void drawLine(Graphics g, Vector2 p0, Vector2 p1, Vector2 pos, Camera camera) {
		g.drawLine((int)((p0.x + pos.x - camera.point.x)*camera.zoom), (int)((p0.y + pos.y - camera.point.y)*camera.zoom), (int)((p1.x + pos.x - camera.point.x)*camera.zoom), (int)((p1.y + pos.y - camera.point.y)*camera.zoom));
	}
	
	/**
	 * Draws a triangle relative to pos
	 * @param g Java.awt.Graphics object to draw on
	 * @param p0 Point 0 for the triangle
	 * @param p1 Point 1 for the triangle
	 * @param p2 Point 2 for the triangle
	 * @param pos Origin for the triangle
	 * @param camera Camera to draw from
	 */
	public static void drawTriangle(Graphics g, Vector2 p0, Vector2 p1, Vector2 p2, Vector2 pos, Camera camera) {
		int[] x = {
				(int)((p0.x + pos.x - camera.point.x)*camera.zoom),
				(int)((p1.x + pos.x - camera.point.x)*camera.zoom),
				(int)((p2.x + pos.x - camera.point.x)*camera.zoom)
		};
		int[] y = {
				(int)((p0.y + pos.y - camera.point.y)*camera.zoom),
				(int)((p1.y + pos.y - camera.point.y)*camera.zoom),
				(int)((p2.y + pos.y - camera.point.y)*camera.zoom)
		};
		g.drawPolygon(x,y,3);
	}
	/**
	 * Fills a triangle relative to pos
	 * @param g Java.awt.Graphics object to draw on
	 * @param p0 Point 0 for the triangle
	 * @param p1 Point 1 for the triangle
	 * @param p2 Point 2 for the triangle
	 * @param pos Origin for the triangle
	 * @param camera Camera to draw from
	 */
	public static void fillTriangle(Graphics g, Vector2 p0, Vector2 p1, Vector2 p2, Vector2 pos, Camera camera) {
		int[] x = {
				(int)((p0.x + pos.x - camera.point.x)*camera.zoom),
				(int)((p1.x + pos.x - camera.point.x)*camera.zoom),
				(int)((p2.x + pos.x - camera.point.x)*camera.zoom)
		};
		int[] y = {
				(int)((p0.y + pos.y - camera.point.y)*camera.zoom),
				(int)((p1.y + pos.y - camera.point.y)*camera.zoom),
				(int)((p2.y + pos.y - camera.point.y)*camera.zoom)
		};
		g.fillPolygon(x,y,3);
	}
	
	/**
	 * Draws a polygon relative to pos
	 * @param g Java.awt.Graphics object to draw on
	 * @param points Array of verticies for the ploygon
	 * @param pos Origin for the polygon
	 * @param camera Camera to draw from
	 */
	public static void drawPoly(Graphics g, Vector2D[] points, Vector2 pos, Camera camera) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = (int)((points[i].x + pos.x - camera.point.x)*camera.zoom);
			y[i] = (int)((points[i].y + pos.y - camera.point.y)*camera.zoom);
		}
		g.drawPolygon(x,y,points.length);
	}
	/**
	 * Fills a polygon relative to pos
	 * @param g Java.awt.Graphics object to draw on
	 * @param points Array of verticies for the ploygon
	 * @param pos Origin for the polygon
	 * @param camera Camera to draw from
	 */
	public static void fillPoly(Graphics g, Vector2D[] points, Vector2 pos, Camera camera) {
		int[] x = new int[points.length];
		int[] y = new int[points.length];
		for(int i = 0; i < points.length; i++) {
			x[i] = (int)((points[i].x + pos.x - camera.point.x)*camera.zoom);
			y[i] = (int)((points[i].y + pos.y - camera.point.y)*camera.zoom);
		}
		g.fillPolygon(x,y,points.length);
	}

}
