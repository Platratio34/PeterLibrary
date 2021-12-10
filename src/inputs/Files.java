package inputs;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Files {
	
	public static String contenceOfFile(String fileName) {
		String out = "";
		Scanner scan = null;
		try {
			scan = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(scan.hasNextLine()) {
			out += scan.nextLine() + "\n";
		}
		
		scan.close();
		return out;
	}
	
	public static String[] fileAsArray(String fileName) {
		return fileAsArray(new File(fileName));
	}
	public static String[] fileAsArray(File file) {
		List<String> out = new ArrayList<String>();
		Scanner scan = null;
		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("invalid file");
			scan = new Scanner(" ");
		}
		while(scan.hasNextLine()) {
			out.add(scan.nextLine());
		}
		
		scan.close();
		return out.toArray(new String[out.size()]);
	}
}
