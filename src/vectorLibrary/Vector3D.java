package vectorLibrary;

public class Vector3D {
	public double x = 0;
	public double y = 0;
	public double z = 0;
	public static Vector3D one = new Vector3D(1,1,1);
	/**
	 * Blank Constuctor for double 3d vector, x, y and z are set to 0
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
	
	public String toString() {
		return "("+x+","+y+","+z+")";
	}
	
	/**
	 * Adds this vector and v2, return a new vector witch is the sum of the two
	 * @param v2: the vector to add
	 * @return Sum of the two vectors
	 */
	public Vector3D add(Vector3D v2) {
		return new Vector3D(x+v2.x, y+v2.y, z+v2.z);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2: the vector to subtract
	 * @return Diffrence of the two vectors
	 */
	public Vector3D subtract(Vector3D v2) {
		return new Vector3D(x-v2.x, y-v2.y, z-v2.z);
	}
	
	/**
	 * Scales the vector, and returns a new vecotr witch is scaled
	 * @param s: the amount to scale by
	 * @return The sclaed vector
	 */
	public Vector3D scale(double s) {
		return new Vector3D((x*s), (y*s), (z*s));
	}
	
	/**
	 * Retruns the magnitude of the vector
	 * @return the magnitude
	 */
	public double magnitude() {
		return Math.sqrt((x*x)+(y*y)+(z*z));
	}
	
	/**
	 * Normalizes the vecotr. Scales the vector down so that the mangnitude is 1
	 */
	public void normalize() {
		scale(magnitude());
	}
}
