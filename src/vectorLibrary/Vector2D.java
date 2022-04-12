package vectorLibrary;

import java.awt.Point;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

public class Vector2D implements JsonSerializable {
	public double x = 0;
	public double y = 0;
	public static Vector2D one = new Vector2D(1,1);
	
	/**
	 * Blank Constructor for double 2d vector, x and y are set to 0
	 */
	public Vector2D() {}
	/**
	 * Constructor for double 2d vector
	 * @param xi : x value
	 * @param yi : y value
	 */
	public Vector2D(double xi, double yi) {
		x = xi;
		y = yi;
	}
	/**
	 * Constructor for double 2d vector
	 * @param obj : JsonObj representing the vector
	 */
	public Vector2D(JsonObj obj) {
		deserilize(obj);
	}
	
	/**
	 * Returns a copy of the vector
	 * @return: exact copy of the vector
	 */
	public Vector2D copy() {
		return new Vector2D(x,y);
	}
	

	
	public String toString() {
		return "("+x+","+y+")";
	}
	
	/**
	 * Adds this vector and v2, return a new vector witch is the sum of the two
	 * @param v2 : the vector to add
	 * @return Sum of the two vectors
	 */
	public Vector2D add(Vector2D v2) {
		return new Vector2D(x+v2.x, y+v2.y);
	}
	/**
	 * Adds this vector and v2, return a new vector witch is the sum of the two
	 * @param v2 : the point to add
	 * @return Sum of the two vectors
	 */
	public Vector2D add(Point v2) {
		return new Vector2D(x+v2.x, y+v2.y);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2 : the vector to subtract
	 * @return Difference of the two vectors
	 */
	public Vector2D subtract(Vector2D v2) {
		return new Vector2D(x-v2.x, y-v2.y);
	}
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2 : the point to subtract
	 * @return Difference of the two vectors
	 */
	public Vector2D subtract(Point v2) {
		return new Vector2D(x-v2.x, y-v2.y);
	}
	
	/**
	 * Scales the vector, and returns a new vector witch is scaled
	 * @param s : the amount to scale by
	 * @return The scaled vector
	 */
	public Vector2D scale(double s) {
		return new Vector2D(x*s, y*s);
	}
	
	/**
	 * Scales the vector, and returns a new integer vector which is scaled by size
	 * @param size The amount to scale by
	 * @return a new scaled Vector2
	 */
	public Vector2 scaleI(float size) {
		return new Vector2((int)(size*x), (int)(size*y));
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return the magnitude
	 */
	public double magnitude() {
		return Math.sqrt((x*x)+(y*y));
	}
	
	/**
	 * Normalizes the vector. Scales the vector down so that the magnitude is 1
	 */
	public void normalize() {
		scale(1d/magnitude());
	}
	
	@Override
	public JsonObj serilize() {
		return new JsonObj(new Object[] {x,y});
	}
	
	@Override
	public void deserilize(JsonObj obj) {
		JsonObj[] arr = obj.getArr();
		x = arr[0].doubleP();
		y = arr[1].doubleP();
	}
}
