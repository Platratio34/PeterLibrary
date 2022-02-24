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
	private JFrame frame = new JFrame("Testing");
	private JTextArea label = new JTextArea("Console:\n");
	private boolean enterHit = false;
	private PrintStream oldPS;
	private PrintStream outPS;
	private ByteArrayOutputStream out;
	private String str;
	private JScrollPane scrolll = new JScrollPane(label);
	private boolean overided;
	private String startingText;
	
	/**
	 * Default constructor
	 *  <li> {@code override = false} </li>
	 *  <li> {@code exit = true} </li>
	 *  <li> {@code start = "Console:\n"} </li>
	 *  <li> {@code width, hight = 800}px </li>
	 */
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
	/**
	 * Paramatized constructor
	 *  <li> {@code exit = true} </li>
	 *  <li> {@code start = "Console:\n"} </li>
	 *  <li> {@code width, hight = 800}px </li>
	 * @param overide : take over {@code System.out}
	 */
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
	/**
	 * Paramatized constructor
	 *  <li> {@code start = "Console:\n"} </li>
	 *  <li> {@code width, hight = 800}px </li>
	 * @param exit : should the program exit on window close
	 * @param overide : take over {@code System.out}
	 */
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
	/**
	 * Paramatized constructor
	 *  <li> {@code width, hight = 800}px </li>
	 * @param exit : should the program exit on window close
	 * @param overide : take over {@code System.out}
	 * @param start : starting text
	 */
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
	/**
	 * Paramatized constructor
	 *  <li> {@code start = "Console:\n"} </li>
	 * @param exit : should the program exit on window close
	 * @param w : width of console in pixels
	 * @param h : height of console in pixels
	 * @param overide : take over {@code System.out}
	 */
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
	/**
	 * Paramatized constructor
	 * @param exit : should the program exit on window close
	 * @param w : width of console in pixels
	 * @param h : height of console in pixels
	 * @param overide : take over {@code System.out}
	 * @param start : starting text
	 */
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
	
	/**
	 * Sets the visibility of the console
	 * @param visibility : the visibility of console
	 */
	public void visible(boolean visibility) {
		frame.setVisible(visibility);
	}
	
	/**
	 * Appends the text and a newline character
	 * @param x : the text to append
	 */
	public void appendln(String x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	/**
	 * Appends the integer and a newline character
	 * @param x : integer to append
	 */
	public void appendln(int x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	/**
	 * Appends the charecter and a newline character
	 * @param x : charecter to append
	 */
	public void appendln(char x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	/**
	 * Appends the double and a newline character
	 * @param x : double to append
	 */
	public void appendln(double x) {
		label.setText(label.getText() + x + "\n");
		update();
	}
	/**
	 * Appends the object and a newline character
	 * @param x : object to append, {@code toString()} is used
	 */
	public void appendln(Object x) {
		label.setText(label.getText() + x.toString() + "\n");
		update();
	}
	

	/**
	 * Appends the text
	 * @param x : text to append
	 */
	public void append(String x) {
		label.setText(label.getText() + x);
		update();
	}
	/**
	 * Appends an integer
	 * @param x : integer to append
	 */
	public void append(int x) {
		label.setText(label.getText() + x);
		update();
	}
	/**
	 * Appends a character
	 * @param x : character to append
	 */
	public void append(char x) {
		label.setText(label.getText() + x);
		update();
	}
	/**
	 * Appends a double
	 * @param x : double to append
	 */
	public void append(double x) {
		label.setText(label.getText() + x);
		update();
	}
	/**
	 * Appends an object
	 * @param x : object to append, {@code toString()} is used
	 */
	public void append(Object x) {
		label.setText(label.getText() + x.toString());
		update();
	}
	
	/**
	 * Clears the console
	 */
	public void clearText() {
		label.setText(startingText);
		update();
	}
	
	/**
	 * Sets the text on the console
	 * @param str : the text to set the console to, the starting text is put on the begining
	 */
	public void setText(String str) {
		label.setText(startingText + str);
		update();
	}
	/**
	 * Sets the text on the console
	 * @param str : text to set the console to
	 * @param start : if the starting text should be used
	 */
	public void setText(String str, boolean start) {
		if(start) {
			label.setText(startingText + str);
		}
		update();
	}
	
	/**
	 * Returns the full text of the console
	 * @return the contnce of the console
	 */
	public String getText() {
		return label.getText();
	}
	
	/**
	 * Waits for newline from the user then returns the line
	 * @return The input from the user
	 */
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
	/**
	 * Waits for newline from the user then returns tries to return an integer
	 * @return The input from the user
	 */
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
	
	/**
	 * Overrides {@code System.out} or returns it to it's normal output
	 * @param overide : override {@code System.out}
	 */
	public void overideSystem (boolean overide) {
		if(overide) {
			System.setOut(outPS);
		} else {
			System.setOut(oldPS);
		}
	}
	
	/**
	 * Updates the console frame;
	 */
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
	
	/**
	 * Displays console content to {@code System.out}
	 */
	public void disp() throws Exception {
		if(overided) {
			System.out.println("Don't print the content of the console to the console!");
//			throw new Exception("Tried to show the contence of the console to the System.out, when System.out has been set to the console");
		}
		System.out.println(" -- ++ -- Contence of Console -- ++ -- \n" + str + "\n -- ++ -- End of Console Contence -- ++ --\n");
	}
	
	/**
	 * Gets the {@code JFrame} of the console
	 * @return the console {@code JFrame}
	 */
	public JFrame getFrame() {
		return frame;
	}
}