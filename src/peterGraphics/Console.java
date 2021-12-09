package peterGraphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Console {
	public JFrame frame = new JFrame("Testing");
	public JTextArea label = new JTextArea("Console:\n");
	public boolean enterHit = false;
	public PrintStream oldPS;
	public PrintStream outPS;
	public ByteArrayOutputStream out;
	public String str;
	public JScrollPane scrolll = new JScrollPane(label);
	public boolean overided;
	private String startingText;
	
	public Console() {
		startingText = "Console:\n";
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		System.setOut(outPS);
		overided = true;
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(800, 800));
		update();
	}
	public Console(boolean overide) {
		startingText = "Console:\n";
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		if(overide) {
			overided = true;
			System.setOut(outPS);
		}
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(800, 800));
		update();
	}
	public Console(boolean exit, boolean overide) {
		startingText = "Console:\n";
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		if(overide) {
			overided = true;
			System.setOut(outPS);
		}
		
		if(exit) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(800, 800));
		update();
	}
	public Console(boolean exit, boolean overide, String start) {
		startingText = start+"\n";
		label.setText(start+"\n");
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		if(overide) {
			overided = true;
			System.setOut(outPS);
		}
		
		if(exit) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(800, 800));
		update();
	}
	public Console(boolean exit, int w, int h, boolean overide) {
		startingText = "Console:\n";
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		if(overide) {
			overided = true;
			System.setOut(outPS);
		}
		
		if(exit) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(h, w));
		update();
	}
	public Console(boolean exit, int w, int h, boolean overide, String start) {
		startingText = start+"\n";
		label.setText(start+"\n");
		oldPS = System.out;
		out = new ByteArrayOutputStream();
		outPS = new PrintStream(out);
		if(overide) {
			overided = true;
			System.setOut(outPS);
		}
		
		if(exit) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		label.setFont(new Font("Consolas", Font.PLAIN, 12));
		label.setPreferredSize(new Dimension(h, w));
		update();
	}
	
	public void visible(boolean visibility) {
		frame.setVisible(visibility);
	}
	
	public void appendln(String x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	public void appendln(int x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	public void appendln(char x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	public void appendln(double x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	
	public void append(String x) {
		label.setText(label.getText() + x);
		update();
	}
	public void append(int x) {
		label.setText(label.getText() + x);
		update();
	}
	public void append(char x) {
		label.setText(label.getText() + x);
		update();
	}
	public void append(double x) {
		label.setText(label.getText() + x);
		update();
	}
	
	public void clearText() {
		label.setText(startingText);
		update();
	}
	
	public void setText(String str) {
		label.setText(startingText + str);
		update();
	}
	public void setText(String str, boolean start) {
		if(start) {
			label.setText(startingText + str);
		}
		update();
	}
	
	public String getText() {
		return label.getText();
	}
	
	public String scan() {
		int start = label.getText().length();
		append(">");
		String output = "";
		label.setEditable(true);
		boolean done = false;
		for(int i = 0; i <= 999999999; i++) {
			String tempS = label.getText();
			if(tempS != null) {
				int temp = tempS.length() - 1;
				if(tempS.charAt(temp)=='\n') {
					output = tempS.substring(start+1,temp);
					//System.out.println(output);
					label.setEditable(false);
					i = 999999999;
					done = true;
				}
			}
		}
		if(!overided) {
			if(!done) {
				System.out.println("Scanner timed out");
			}
			System.out.println("Scanner output: " + output);
		}
		return output;
	}
	public int scanInt() {
		int start = label.getText().length();
		append(">");
		String output = "";
		label.setEditable(true);
		boolean done = false;
		for(int i = 0; i <= 999999999; i++) {
			String tempS = label.getText();
			int temp = tempS.length() - 1;
			if(tempS.charAt(temp)=='\n') {
				output = tempS.substring(start+1,temp);
				//System.out.println(output);
				label.setEditable(false);
				i = 999999999;
				done = true;
			}
		}
		if(!overided) {
			if(!done) {
				System.out.println("Scanner timed out");
			}
			System.out.println("Scanner output: " + output);
		}
		return Integer.parseInt(output);
	}
	
	public void overideSystem (boolean overide) {
		if(overide) {
			System.setOut(outPS);
		} else {
			System.setOut(oldPS);
		}
	}
	
	public void update() {
		label.setEditable(false);
		frame.getContentPane().add(scrolll);
		frame.getContentPane().add(label, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		str = label.getText();
	}
	
	/*public void add(peterGraphics.util.Drawing draw) {
		frame.add(draw);
		update();
	}*/
	
	public void disp() {
		System.out.println(" -- ++ -- Contence of Console -- ++ -- \n" + str + "\n -- ++ -- End of Console Contence -- ++ --\n");
	}
}