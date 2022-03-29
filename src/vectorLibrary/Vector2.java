package vectorLibrary;

import java.awt.Point;
import dataManagment.JsonObj;

public class Vector2 {
	public int x = 0;
	public int y = 0;
	public static Vector2 one = new Vector2(1,1);
	
	/**
	 * Blank constructor for 2d vector. x and y are equal to 0
	 */
	public Vector2() {}
	/**
	 * Constructor for 2d vector
	 * @param xi: x value
	 * @param yi: y value
	 */
	public Vector2(int xi, int yi) {
		x = xi;
		y = yi;
	}
	public Vector2(Point p) {
		x = p.x;
		y = p.y;
	}
	
	public String toString() {
		return "("+x+","+y+")";
	}
	
	/**
	 * Adds this vector and v2, return a new vector witch is the sum of the two
	 * @param v2: the vector to add
	 * @return Sum of the two vectors
	 */
	public Vector2 add(Vector2 v2) {
		return new Vector2(x+v2.x, y+v2.y);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2: the vector to subtract
	 * @return Diffrence of the two vectors
	 */
	public Vector2 subtract(Vector2 v2) {
		return new Vector2(x-v2.x, y-v2.y);
	}
	
	/**
	 * Scales the vecotr, and returns a new vecotr witch is scaled
	 * @param s: the amount to scale by
	 * @return The sclaed vector
	 */
	public Vector2 scale(float s) {
		return new Vector2((int)(x*s), (int)(y*s));
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return the magnitude
	 */
	public double magnitude() {
		return Math.sqrt((x*x)+(y*y));
	}
	
	/**
	 * Returns a JsonObj representing the vector
	 * @return a new JsonObj with 2 parameters, x and y
	 */
	public JsonObj save() {
		JsonObj obj = new JsonObj();
		obj.addArray(x);
		obj.addArray(y);
		return obj;
	}
}
