package peterGraphics.util;

import java.awt.Point;

public class Camera {
	public Point point;
	public double zoom;
	
	/**
	 * constructor
	 * @param Point : location of cameras
	 * @param Zoom : zoom of camera
	 */
	public Camera(Point Point, double Zoom) {
		point = Point;
		zoom = Zoom;
	}
	/**
	 * default constructor
	 *  -point 0,0
	 *  -zoom 1
	 */
	public Camera() {
		point = new Point(0,0);
		zoom = 1;
	}
}
