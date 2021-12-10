package peterLibrary;

import java.awt.Color;
import java.awt.Point;
import peterGraphics.util.Shape;
import peterGraphics.util.ShapeE;
import vectorLibrary.LineSegment;

public class Arrays {
	
	//print arrays
	//1D
	public static void printArray (int[] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	public static void printArray (String[] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	public static void printArray (char[] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	public static void printArray (double[] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	public static void printArray (float[] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i] + ", ");
		}
	}
	
	//2D
	public static void printArray (int[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print("\n");
		}
	}
	public static void printArray (String[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print("\n");
		}
	}
	public static void printArray (char[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print("\n");
		}
	}
	public static void printArray (double[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print("\n");
		}
	}
	public static void printArray (float[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j] + ", ");
			}
			System.out.print("\n");
		}
	}
	public static void printArray (Object[][] array) {
		System.out.print("\n");
		for(int i = 0; i < array.length; i++) {
			for(int j = 0; j< array[1].length;j++) {
				System.out.print(array[i][j].toString() + ", ");
			}
			System.out.print("\n");
		}
	}
	
	public static int[] setArray(int[] a, int b) {
		int[] c = new int[a.length+1];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	public static int[][] setArray(int[][] a, int[] b) {
		int[][] c = new int[a.length+1][];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	public static Color[] setArray(Color[] a, Color b) {
		Color[] c = new Color[a.length+1];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	public static ShapeE[] setArray(ShapeE[] a, ShapeE b) {
		ShapeE[] c = new ShapeE[a.length+1];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	public static String[] setArray(String[] a, String b) {
		String[] c = new String[a.length+1];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	
	public static Shape[] copy(Shape[] a) {
		Shape[] b = new Shape[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = a[i].copy();
		}
		return b;
	}
	
	public static int[] offset(int[] a, int o) {
		int[] c = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i] + o;
		}
		return c;
	}
	
	public static Point[] expand(Point[] a, Point b) {
		Point[] c = new Point[a.length + 1];
		for(int i = 0; i < a.length; i++) {
			c[i] = (Point) a[i].clone();
		}
		c[a.length] = (Point) b.clone();
		return c;
	}
	public static LineSegment[] expand(LineSegment[] a, LineSegment b) {
		LineSegment[] c = new LineSegment[a.length + 1];
		for(int i = 0; i < a.length; i++) {
			c[i] = (LineSegment) a[i].clone();
		}
		c[a.length] = (LineSegment) b.clone();
		return c;
	}
	public static boolean contains(int[] arr, int key) {
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] == key) {
				return true;
			}
		}
		return false;
	}
}
