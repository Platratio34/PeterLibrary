package peterGraphics.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import errorHandler.ErrorLogger;
import errorHandler.ErrorType;
import peterLibrary.Arrays;

/**
 * Equivelint of a texture
 * @author Peter Crall
 *
 */
public class Graphic {
	private Shape shapes[];
	private Point point;
	private boolean draw;
	private int deapth;
	
	/**
	 * Constructor
	 */
	public Graphic() {
		clear();
	}
	/**
	 * Constructor
	 * @param Shapes : array of shapes to draw
	 */
	public Graphic(Shape[] Shapes) {
		clear();
		shapes = Shapes;
	}
	
	/**
	 * Clears and resets the graphic
	 */
	public void clear() {
		draw = true;
		shapes = new Shape[1];
		shapes[0] = new Shape();
		point = new Point();
		deapth = 0;
	}
	
	/**
	 * Sets the location of the graphic
	 * @param point_ : the new location
	 */
	public void setPoint(Point point_) {
		point = point_;
//		System.out.println("point set");
//		System.out.println(point.toString());
	}
	
	/**
	 * Sets if it should be drawn
	 * @param Draw : if the graphic should be drawn
	 */
	public void setDraw(boolean Draw) {
		draw = Draw;
	}
	
	//Outlined Stuff
	/**
	 * Makes a new outlined rectangle
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width
	 * @param h : the hight
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void rect(int x, int y, int w, int h, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[t.length].newShape(ShapeE.RECTANGLE, x, y, w, h, r, g, b);
	}
	
	/**
	 * Makes a new line from point 1 to point 2
	 * @param x : point 1 x coordinate
	 * @param y : point 2 y coordinate
	 * @param x2 : point 2 x coordinate
	 * @param y2 : point 2 y coordinate
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void line(int x, int y, int x2, int y2, int r, int g, int b) {
		
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newShape(ShapeE.LINE, x, y, x2, y2, r, g, b);
	}
	
	/**
	 * Makes a new outlined oval
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width
	 * @param h : the hight
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void circle(int x, int y, int w, int h, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newShape(ShapeE.CIRCLE, x, y, w, h, r, g, b);
	}
	
	/**
	 * Makes a new 3 sided outlined polygon
	 * @param x1 : vertex 1 x coordinate
	 * @param y1 : vertex 1 y coordinate
	 * @param x2 : vertex 2 x coordinate
	 * @param y2 : vertex 2 y coordinate
	 * @param x3 : vertex 3 x coordinate
	 * @param y3 : vertex 3 y coordinate
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void polygon(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygon(new int[] { x1,x2,x3 }, new int[] { y1,y2,y3 }, r, g, b);
	}
	/**
	 * Makes a new 4 sided outlined polygon
	 * @param x1 : vertex 1 x coordinate
	 * @param y1 : vertex 1 y coordinate
	 * @param x2 : vertex 2 x coordinate
	 * @param y2 : vertex 2 y coordinate
	 * @param x3 : vertex 3 x coordinate
	 * @param y3 : vertex 3 y coordinate
	 * @param x4 : vertex 4 x coordinate
	 * @param y4 : vertex 4 y coordinate
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void polygon(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygon(new int[] { x1,x2,x3,x4 }, new int[] { y1,y2,y3,y4 }, r, g, b);
	}
	/**
	 * Makes a new n sided outlined polygon
	 * @param x : array of x coordinates
	 * @param y : array of y coordinates
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void polygon(int[] x, int[] y, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygon(x, y, r, g, b);
	}
	
	//Filled stuff
	/**
	 * Makes a new filled rectangle
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width
	 * @param h : the hight
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void rectF(int x, int y, int w, int h, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newShape(ShapeE.RECTANGLEF, x, y, w, h, r, g, b);
	}
	
	/**
	 * Makes a new filled oval
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width
	 * @param h : the hight
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void circleF(int x, int y, int w, int h, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newShape(ShapeE.CIRCLEF, x, y, w, h, r, g, b);
	}
	
	/**
	 * Makes a new 3 sided filled polygon
	 * @param x1 : vertex 1 x coordinate
	 * @param y1 : vertex 1 y coordinate
	 * @param x2 : vertex 2 x coordinate
	 * @param y2 : vertex 2 y coordinate
	 * @param x3 : vertex 3 x coordinate
	 * @param y3 : vertex 3 y coordinate
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void polygonF(int x1, int y1, int x2, int y2, int x3, int y3, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygonF(new int[] { x1,x2,x3 }, new int[] { y1,y2,y3 }, r, g, b);
		//System.out.println("poly setup");
		//Arrays.printArray(shapes[shapes.length-1].px);
		//System.out.println();
	}
	/**
	 * Makes a new 4 sided filled polygon
	 * @param x1 : vertex 1 x coordinate
	 * @param y1 : vertex 1 y coordinate
	 * @param x2 : vertex 2 x coordinate
	 * @param y2 : vertex 2 y coordinate
	 * @param x3 : vertex 3 x coordinate
	 * @param y3 : vertex 3 y coordinate
	 * @param x4 : vertex 4 x coordinate
	 * @param y4 : vertex 4 y coordinate
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void polygonF(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygonF(new int[] { x1,x2,x3,x4 }, new int[] { y1,y2,y3,y4 }, r, g, b);
	}
	/**
	 * Makes a new n sided filled polygon
	 * @param x : array of x coordinates
	 * @param y : array of y coordinates
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the color value
	 */
	public void polygonF(int[] x, int[] y, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i].copy();
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newPolygonF(x, y, r, g, b);
	}
	
	/**
	 * Adds text to the screen
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param in : the text
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void text(int x, int y, char in, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i];
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newText(x, y, Character.toString(in), r, g, b, new Font(Font.MONOSPACED, Font.PLAIN, 12));
	}
	/**
	 * Adds text to the screen
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param in : the text
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 */
	public void text(int x, int y, String in, int r, int g, int b) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i];
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newText(x, y, in, r, g, b, new Font(Font.MONOSPACED, Font.PLAIN, 12));
	}
	/**
	 * Adds text to the screen
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param in : the text
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 * @param font : the Font to use
	 */
	public void text(int x, int y, char in, int r, int g, int b, Font font) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i];
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newText(x, y, Character.toString(in), r, g, b, font);
	}
	/**
	 * Adds text to the screen
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param in : the text
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 * @param font : the Font to use
	 */
	public void text(int x, int y, String in, int r, int g, int b, Font font) {
		Shape[] t = shapes;
		shapes = new Shape[t.length+1];
		for(int i = 0;i < t.length; i++) {
			shapes[i] = t[i];
		}
		shapes[t.length] = new Shape();
		shapes[shapes.length-1].newText(x, y, in, r, g, b, font);
	}
	
	
	/**
	 * Returns a copy the array of shapes
	 * @return A copy of the array of shapes
	 * @deprecated Bad practice
	 */
	@Deprecated
	public Shape[] getShape() {
		return Arrays.copy(shapes);
	}
	/**
	 * Returns a copy the array of shapes offset by a position
	 * @param xo : the offset in the x axis
	 * @param yo : the offset in the y axis
	 * @return A copy of the array of shapes offset
	 * @deprecated Bad practice
	 */
	@Deprecated
	public Shape[] getShape(int xo, int yo) {
		//System.out.println("graphic DEBUG: offset "+ xo + "," + yo);
		Shape[] o = new Shape[shapes.length];
		o = Arrays.copy(shapes);
		
			for(int i = 1; i < o.length; i++) {
				o[i] = o[i].offset(xo,yo);
				//System.out.println("graphic DEBUG: output " + o[i].xA + "," + o[i].yA + "," + o[i].wA + "," + o[i].hA);
			}
		
		return o;
	}
	
	/**
	 * Draws the shapes using the the Graphics object g
	 * @param g : the {@code java.awt.Graphics} object do draw on
	 * @param eLogger : Logger for errors encounted in drawing
	 */
	public void draw(Graphics g, ErrorLogger eLogger, Camera camera) {
//		System.out.println(deapth);
		if(draw) {
			onDraw(g, eLogger, camera);
			for(int i = 0; i < shapes.length; i++) {
				//System.out.println("TEST 2");
				try {
					//System.out.println(shapes[i]);
	//				System.out.println(point.toString());
					if(deapth < 20) {
						shapes[i].draw(g, point.x, point.y, camera);
					} else {
						shapes[i].draw(g, point.x, point.y, new Camera());
					}
				}
				catch(ArrayIndexOutOfBoundsException e) {
					eLogger.logError(ErrorType.ArrayIndexOutOfBounds, "Graphic.java", 211);
				}
				catch(NullPointerException e) {
					eLogger.logError(ErrorType.NullPointer, "Graphic.java", 211, "@IGNORE");
				}
				catch(Exception e) {
					//eLogger.logError(e, "Drawing.java", 171);
					System.out.println("Error: An error has ocured; At: Graphic.java:211; Error: " + e);
				}
			}
		}
	}
	/**
	 * Called when the Graphic is being drawn for custom Graphics.
	 * @param g java.awt.Graphics to draw on
	 * @param eLogger Error Logger for drawing
	 * @param camera Camera to draw from
	 */
	protected void onDraw(Graphics g, ErrorLogger eLogger, Camera camera) {}
	
	/**
	 * Sets the draw depth of the graphic
	 * @param d : the new draw depth
	 */
	public void setDepth(int d) {
		deapth = d;
	}
	/**
	 * Gets the draw depth
	 * @return the current draw depth
	 */
	public int getDeapth() {
		return deapth;
	}
	
	/**
	 * Returns a representation of the graphic as a string, inteded for saving. WARNING there is currenly no load function
	 * @param d : the number of tabs to start each line with
	 * @return A string representation of the graphic
	 */
	public String save(int d) {
		String out = "";
		for(int j = 1; j < shapes.length; j++) {
			for(int i = 0; i < d; i++) {
				out += "\t";
			}
			out += "shape:{" + "\n";
			out += shapes[j].save(d+1);
			for(int i = 0; i < d; i++) {
				out += "\t";
			}
			out += "}" + "\n";
		}
//		for(int i = 0; i < d; i++) {
//			out += "\t";
//		}
//		out += "TEXT;" + "\n";
		return out;
	}
}
