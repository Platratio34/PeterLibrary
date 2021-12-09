package vectorLibrary;

public class Vector3 {
	public static int x = 0;
	public static int y = 0;
	public static int z = 0;
	public Vector3() {
		
	}
	public Vector3(int xi, int yi, int zi) {
		x = xi;
		y = yi;
		z = zi;
	}
	public void display() {
		System.out.println("x:" + x + " y:" + y + " z:" + z);
	}
}
