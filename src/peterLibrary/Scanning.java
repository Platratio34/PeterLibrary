package peterLibrary;

import java.util.Scanner;

public class Scanning {
	public static String scanNext() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		String output = scan.next();
		return output;
	}
	public static void scanNext(String output) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		output = scan.next();
	}
	
	public static String scanNextLine() {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		String output = scan.nextLine();
		return output;
	}
	public static void scanNextLine(String output) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		output = scan.nextLine();
	}
}
