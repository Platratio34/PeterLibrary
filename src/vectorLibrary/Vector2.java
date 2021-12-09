package vectorLibrary;

public class Vector2 {
	public int x = 0;
	public int y = 0;
	public Vector2() {
		
	}
	public Vector2(int xi, int yi) {
		x = xi;
		y = yi;
	}
	public void display() {
		System.out.println("x:" + x + " y:" + y);
	}
}
