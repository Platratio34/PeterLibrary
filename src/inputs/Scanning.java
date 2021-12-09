package inputs;

import java.util.Scanner;

public class Scanning {
	
	public static int nextInt(Scanner scan) {
		int out = 0;
		while(!scan.hasNextInt()) {
			System.out.println("I need a number");
			scan.nextLine();
		}
		out = scan.nextInt();
		
		return out;
	}
}
