package vectorLibrary;

public class Vector3 {
	public int x = 0;
	public int y = 0;
	public int z = 0;
	public static Vector3 one = new Vector3(1,1,1);
	
	/**
	 * Blank Constuctor for 3d vector, x, y and z are set to 0
	 */
	public Vector3() {}
	/**
	 * Constructor for 3d vector
	 * @param xi: x value
	 * @param yi: y value
	 * @param zi: z value
	 */
	public Vector3(int xi, int yi, int zi) {
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
	public Vector3 add(Vector3 v2) {
		return new Vector3(x+v2.x, y+v2.y, z+v2.z);
	}
	
	/**
	 * Subtracts v2 from this vector, return a new vector witch is the difference of the two
	 * @param v2: the vector to subtract
	 * @return Diffrence of the two vectors
	 */
	public Vector3 subtract(Vector3 v2) {
		return new Vector3(x-v2.x, y-v2.y, z-v2.z);
	}
	
	/**
	 * Scales the vector, and returns a new vecotr witch is scaled
	 * @param s: the amount to scale by
	 * @return The sclaed vector
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
}
