package peterGraphics;

public class Main {
	public static void main(String[] args) {
		Console mainC = new Console(false);
		mainC.append("aaa");
		mainC.clearText();
		System.out.println("input: " + mainC.scan());
	}
}