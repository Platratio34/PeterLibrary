package peterGraphics.util;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import errorHandler.ErrorLogger;
import errorHandler.ErrorType;
import peterLibrary.Arrays;

public class Graphic {
	private Shape shapes[];
	private Point point;
	private boolean draw;
	private int deapth;
	
	public Graphic() {
		clear();
	}
	public Graphic(Shape[] Shapes) {
		clear();
		shapes = Shapes;
	}
	
	public void clear() {
		draw = true;
		shapes = new Shape[1];
		shapes[0] = new Shape();
		point = new Point();
		deapth = 0;
	}
	
	public void setPoint(Point point_) {
		point = point_;
//		System.out.println("point set");
//		System.out.println(point.toString());
	}
	
	public void setDraw(boolean Draw) {
		draw = Draw;
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
	 * @param x : point 1 x
	 * @param y : point 2 y
	 * @param x2 : point 2 x
	 * @param y2 : point 2 y
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : int[] of x positions
	 * @param y : int[] of y positions
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param w : width
	 * @param h : hight
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : int[] of x positions
	 * @param y : int[] of y positions
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 * @param font : Font to use
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
	 * @param x : x pos
	 * @param y : y pos
	 * @param in : text
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 * @param font : Font to use
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
	
	@Deprecated
	/**
	 *  *DEPRECATED*
	 * @return Shape[] : shapes
	 */
	public Shape[] getShape() {
		return Arrays.copy(shapes);
	}
	@Deprecated
	/**
	 *  *DEPRECATED*
	 * @param xo : x offset
	 * @param yo : y offset
	 * @return Shape[] : shapes with offset
	 */
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
	 * @param g : Graphics object
	 * @param eLogger : ErrorLogger
	 */
	public void draw(Graphics g, ErrorLogger eLogger, Camera camera) {
//		System.out.println(deapth);
		if(draw) {
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
	public void setDeapth(int d) {
		deapth = d;
	}
	public int getDeapth() {
		return deapth;
	}
	
	/**
	 * 
	 * @param d : number of tabs
	 * @return
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
