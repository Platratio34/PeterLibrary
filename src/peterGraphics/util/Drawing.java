package peterGraphics.util;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import errorHandler.*;

@SuppressWarnings("serial")
public class Drawing extends JPanel {
	
	private Graphic[] graphics;
	private List<Shape> updatingShapes;
	private ErrorLogger eLogger;
	private Font font;
	private Camera camera1;
	public List<GText> gText;
	
	/**
	 * Generic constructor
	 *  -Font "TimesRoman", Font.PLAIN, 14
	 */
	public Drawing () {
		clear();
		eLogger = new ErrorLogger();
		font = new Font("TimesRoman", Font.PLAIN, 14);
	}
	/**
	 * Constructor with eLogger, and font
	 * @param log : ErrorLogger to use
	 * @param Font : Font to use
	 */
	public Drawing (ErrorLogger log, Font Font) {
		clear();
		eLogger = log;
		font = Font;
	}
	/**
	 * Constructor with eLogger
	 *  -Font "TimesRoman", Font.PLAIN, 14
	 * @param log : ErrorLogger to use
	 */
	public Drawing (ErrorLogger log) {
		clear();
		eLogger = log;
		font = new Font("TimesRoman", Font.PLAIN, 14);
	}
	/**
	 * Constructor with font
	 * @param Font : Font to use
	 */
	public Drawing (Font Font) {
		clear();
		eLogger = new ErrorLogger();
		font = Font;
	}
	
	/**
	 * clears and resets the Drawing object
	 */
	public void clear() {
		gText = new ArrayList<GText>();
		graphics = new Graphic[1];
		graphics[0] = new Graphic();
		updatingShapes = new ArrayList<Shape>();
		camera1 = new Camera();
	}
	
	/**
	 * adds a updating Shape object
	 * @param shape : Shape to add
	 */
	public void add(Shape shape) {
		updatingShapes.add(shape);
	}
	
	//Outlined Stuff
	/**
	 * Makes a new outlined rectangle
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void rect(int x, int y, int w, int h, int r, int g, int b) {
		graphics[0].rect(x,y,w,h,r,g,b);
	}
	
	/**
	 * Makes a new line from point 1 to point 2
	 * @param x : point 1 x
	 * @param y : point 2 y
	 * @param x2 : point 2 x
	 * @param y2 : point 2 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void line(int x, int y, int x2, int y2, int r, int g, int b) {
		graphics[0].line(x,y,x2,y2,r,g,b);
	}
	
	/**
	 * Makes a new outlined oval
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void circle(int x, int y, int w, int h, int r, int g, int b) {
		graphics[0].circle(x,y,w,h,r,g,b);
	}
	
	/**
	 * Makes a new 3 sided outlined polygon
	 * @param x1 : vertex 1 x
	 * @param y1 : vertex 1 y
	 * @param x2 : vertex 2 x
	 * @param y2 : vertex 2 y
	 * @param x3 : vertex 3 x
	 * @param y3 : vertex 3 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygon(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		graphics[0].polygon(x1,y1,x2,y2,x3,y3,r,g,b);
	}
	/**
	 * Makes a new 4 sided outlined polygon
	 * @param x1 : vertex 1 x
	 * @param y1 : vertex 1 y
	 * @param x2 : vertex 2 x
	 * @param y2 : vertex 2 y
	 * @param x3 : vertex 3 x
	 * @param y3 : vertex 3 y
	 * @param x4 : vertex 4 x
	 * @param y4 : vertex 4 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygon(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r, int g, int b) {
		graphics[0].polygon(x1,y1,x2,y2,x3,y3,x4,y4,r,g,b);
	}
	/**
	 * Makes a new n sided outlined polygon
	 * @param x : int[] of x positions
	 * @param y : int[] of y positions
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygon(int[] x, int[] y, int r, int g, int b) {
		graphics[0].polygon(x,y,r,g,b);
	}
	
	//Filled stuff
	/**
	 * Makes a new filled rectangle
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void rectF(int x, int y, int w, int h, int r, int g, int b) {
		graphics[0].rectF(x,y,w,h,r,g,b);
	}
	
	/**
	 * Makes a new filled oval
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void circleF(int x, int y, int w, int h, int r, int g, int b) {
		graphics[0].circleF(x,y,w,h,r,g,b);
	}
	
	/**
	 * Makes a new 3 sided filled polygon
	 * @param x1 : vertex 1 x
	 * @param y1 : vertex 1 y
	 * @param x2 : vertex 2 x
	 * @param y2 : vertex 2 y
	 * @param x3 : vertex 3 x
	 * @param y3 : vertex 3 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygonF(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		graphics[0].polygonF(x1,y1,x2,y2,x3,y3,r,g,b);
	}
	/**
	 * Makes a new 4 sided filled polygon
	 * @param x1 : vertex 1 x
	 * @param y1 : vertex 1 y
	 * @param x2 : vertex 2 x
	 * @param y2 : vertex 2 y
	 * @param x3 : vertex 3 x
	 * @param y3 : vertex 3 y
	 * @param x4 : vertex 4 x
	 * @param y4 : vertex 4 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygonF(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r, int g, int b) {
		graphics[0].polygonF(x1,y1,x2,y2,x3,y3,x4,y4,r,g,b);
	}
	/**
	 * Makes a new n sided filled polygon
	 * @param x : int[] of x positions
	 * @param y : int[] of y positions
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void polygonF(int[] x, int[] y, int r, int g, int b) {
		graphics[0].polygonF(x,y,r,g,b);
	}
	
	/**
	 * Adds text to the screen
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void text(int x, int y, char in, int r, int g, int b) {
		graphics[0].text(x,y,in,r,g,b);
	}
	/**
	 * Adds text to the screen
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void text(int x, int y, String in, int r, int g, int b) {
		graphics[0].text(x,y,in,r,g,b);
		
	}
	
	//Graphic input
	/**
	 * adds a graphic to the internal array
	 * @param img : Graphic to use
	 */
	public void addGraphic(Graphic img) {
		graphics = addToArray(graphics, img);
	}
	@Deprecated
	/**
	 * *DEPRICATED* Use addGraphic(Graphic img) instead
	 * adds a graphic to the Drawing object with a x and y offset
	 * @param img : graphic to add
	 * @param xo : x offset
	 * @param yo : y offset
	 */
	public void addGraphic(Graphic img, int xo, int yo) {
		try {
			graphics = addToArray(graphics, new Graphic(img.getShape(xo,yo)));
		}
		catch(NullPointerException e) {
			//eLogger.logError(e, "a", 1);
			eLogger.logError(ErrorType.NullPointer, "Drawing.java", 169, " " + xo + "," + yo);
		}
	}
	
	/**
	 * adds a Graphic to a Graphic[]
	 * @param a : Graphic[]
	 * @param b : Graphic to add
	 * @return Graphic[] with Graphic b
	 */
	private Graphic[] addToArray(Graphic[] a, Graphic b) {
		Graphic[] c = new Graphic[a.length+1];
		for(int i = 0; i < a.length; i++) {
			c[i] = a[i];
		}
		c[a.length] = b;
		return c;
	}
	
	public void paint(Graphics g) {
		graphics = sortByDeapth(graphics);
		g.setFont(font); 
		//System.out.println("Drawing");
		for(int i = 0; i < graphics.length; i++) {
			//System.out.println("TEST 2");
			try {
//				System.out.println(i + ", " + graphics[i].getDeapth());
				graphics[i].draw(g,eLogger,camera1);
			}
			catch(ArrayIndexOutOfBoundsException e) {
				eLogger.logError(ErrorType.ArrayIndexOutOfBounds, "Drawing.java", 309);
			}
			catch(NullPointerException e) {
				eLogger.logError(ErrorType.NullPointer, "Drawing.java", 309);
			}
			catch(Exception e) {
				//eLogger.logError(e, "Drawing.java", 171);
				System.out.println("Error: An error has ocured; At: Drawing.java:197; Error: " + e);
			}
		}
		
		for(int i = 0; i< updatingShapes.size(); i++) {
			Shape shape = updatingShapes.get(i);
			shape.update();
			shape.draw(g, 0, 0, camera1);
//			System.out.println("drawing thing");
		}
		
		for(int i = 0; i < gText.size(); i++) {
			gText.get(i).draw(g);
		}
	}
	
	protected Graphic[] sortByDeapth(Graphic[] a) {
		Graphic[][] b = new Graphic[30][a.length];
		for(int i = 0; i < a.length; i++) {
			int d = a[i].getDeapth();
			b[d][i] = a[i];
		}
		Graphic[] c = new Graphic[a.length];
		int j = 0;
		for(int i = 0; i < 30; i++) {
			for(int k = 0; k < a.length; k++) {
				if(b[i][k] != null) {
					c[j] = b [i][k];
					j++;
				}
			}
			
		}
		return c;
	}
	
	/**
	 * gets the ErrorLogger from the Drawing object
	 * @return ErrorLogger eLogger
	 */
	public ErrorLogger getELogger() {
		return eLogger;
	}
	
	/**
	 * gets the main Camera from the Drawing object
	 * @return Camera camera
	 */
	public Camera getCamera() {
		return camera1;
	}
}
