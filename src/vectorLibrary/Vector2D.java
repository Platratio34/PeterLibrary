package vectorLibrary;

public class Vector2D {
	public double x = 0;
	public double y = 0;
	public Vector2D() {
		
	}
	public Vector2D(double xi, double yi) {
		x = xi;
		y = yi;
	}
	public void display() {
		System.out.println("x:" + x + " y:" + y);
	}
	
	public Vector2D copy() {
		return new Vector2D(x,y);
	}
}
