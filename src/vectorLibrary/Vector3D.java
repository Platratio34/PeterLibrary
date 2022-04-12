package vectorLibrary;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

public class Vector3D implements JsonSerializable {
	public double x = 0;
	public double y = 0;
	public double z = 0;
	public static Vector3D one = new Vector3D(1,1,1);
	/**
	 * Blank Constructor for double 3d vector, x, y and z are set to 0
	 */
	public Vector3D() {}
	/**
	 * Constructor for double 3d vector
	 * @param xi: x value
	 * @param yi: y value
	 * @param zi: z value
	 */
	public Vector3D(double xi, double yi, double zi) {
		x = xi;
		y = yi;
		z = zi;
	}
	/**
	 * Constructor for double 3d vector
	 * @param obj : JsonObj representing the vector
	 */
	public Vector3D(JsonObj obj) {
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
	public Vector3D add(Vector3D v2) {
		return new Vector3D(x+v2.x, y+v2.y, z+v2.z);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2 : the vector to subtract
	 * @return Difference of the two vectors
	 */
	public Vector3D subtract(Vector3D v2) {
		return new Vector3D(x-v2.x, y-v2.y, z-v2.z);
	}
	
	/**
	 * Scales the vector, and returns a new vector witch is scaled
	 * @param s : the amount to scale by
	 * @return The scaled vector
	 */
	public Vector3D scale(double s) {
		return new Vector3D((x*s), (y*s), (z*s));
	}
	
	/**
	 * Returns the magnitude of the vector
	 * @return the magnitude
	 */
	public double magnitude() {
		return Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	/**
	 * Normalizes the vector. Scales the vector down so that the magnitude is 1
	 */
	public void normalize() {
		scale(magnitude());
	}
	
	@Override
	public JsonObj serilize() {
		return new JsonObj(new Object[] {x,y,z});
	}
	
	@Override
	public void deserilize(JsonObj obj) {
		JsonObj[] arr = obj.getArr();
		x = arr[0].doubleP();
		y = arr[1].doubleP();
		z = arr[1].doubleP();
	}
}
