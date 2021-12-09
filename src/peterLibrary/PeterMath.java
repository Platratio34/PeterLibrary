package peterLibrary;

public class PeterMath {
	public static int clampE(int n, int max, int min) {
		if(n >= max) { n = max - 1; }
		if(n <= min) { n = min + 1; }
		return n;
	}
	public static int clampI(int n, int max, int min) {
		if(n > max) { n = max; }
		if(n < min) { n = min; }
		return n;
	}
}
