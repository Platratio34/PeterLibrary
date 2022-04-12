package vectorLibrary;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

public class Vector3 implements JsonSerializable {
	public int x = 0;
	public int y = 0;
	public int z = 0;
	public static Vector3 one = new Vector3(1,1,1);
	
	/**
	 * Blank Constructor for 3d vector, x, y and z are set to 0
	 */
	public Vector3() {}
	/**
	 * Constructor for 3d vector
	 * @param xi : x value
	 * @param yi : y value
	 * @param zi : z value
	 */
	public Vector3(int xi, int yi, int zi) {
		x = xi;
		y = yi;
		z = zi;
	}
	/**
	 * Constructor for 3d vector
	 * @param obj : JsonObj representing the vector
	 */
	public Vector3(JsonObj obj) {
		deserilize(obj);
	}
	
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	
	/**
	 * Adds this vector and v2, return a new vector witch is the sum of the two
	 * @param v2 : the vector to add
	 * @return Sum of the two vectors
	 */
	public Vector3 add(Vector3 v2) {
		return new Vector3(x+v2.x, y+v2.y, z+v2.z);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2 : the vector to subtract
	 * @return Difference of the two vectors
	 */
	public Vector3 subtract(Vector3 v2) {
		return new Vector3(x-v2.x, y-v2.y, z-v2.z);
	}
	
	/**
	 * Scales the vector, and returns a new vector witch is scaled
	 * @param s : the amount to scale by
	 * @return The scaled vector
	 */
	public Vector3 scale(float s) {
		return new Vector3((int)(x*s), (int)(y*s), (int)(z*s));
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return the magnitude
	 */
	public double magnitude() {
		return Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	@Override
	public JsonObj serilize() {
		return new JsonObj(new Object[] {x,y,z});
	}
	
	@Override
	public void deserilize(JsonObj obj) {
		JsonObj[] arr = obj.getArr();
		x = arr[0].integer();
		y = arr[1].integer();
		z = arr[1].integer();
	}
}
