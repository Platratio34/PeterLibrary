package peterLibrary;

import java.util.Scanner;

public class Scanning {
	public static String scanNext() {
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		String output = scan.next();
		scan.close();
		return output;
	}
	public static void scanNext(String output) {
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		output = scan.next();
		scan.close();
	}
	
	public static String scanNextLine() {
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		String output = scan.nextLine();
		scan.close();
		return output;
	}
	public static void scanNextLine(String output) {
		Scanner scan = new Scanner(System.in);
		System.out.print(">");
		output = scan.nextLine();
		scan.close();
	}
}
