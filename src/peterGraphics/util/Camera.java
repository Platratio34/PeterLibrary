package peterGraphics.util;

import java.awt.Point;

public class Camera {
	
	/**
	 * Current location of the camera
	 */
	public Point point;
	/**
	 * Current zoom level
	 */
	public double zoom;
	
	/**
	 * Constructor
	 * @param Point : the location of camera
	 * @param Zoom : the zoom of camera
	 */
	public Camera(Point Point, double Zoom) {
		point = Point;
		zoom = Zoom;
	}
	/**
	 * default constructor
	 *  <li> point = 0,0 </li>
	 *  <li> zoom = 1 </li>
	 */
	public Camera() {
		point = new Point(0,0);
		zoom = 1;
	}
}
