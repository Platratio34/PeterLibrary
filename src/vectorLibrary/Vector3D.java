package vectorLibrary;

public class Vector3D {
	public static double x = 0;
	public static double y = 0;
	public static double z = 0;
	public Vector3D() {
		
	}
	public Vector3D(double xi, double yi, double zi) {
		x = xi;
		y = yi;
		z = zi;
	}
	public void display() {
		System.out.println("x:" + x + " y:" + y + " z:" + z);
	}
}
