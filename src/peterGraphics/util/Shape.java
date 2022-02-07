package peterGraphics.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import vectorLibrary.LineSegment;
import vectorLibrary.Vector2;

public class Shape {	
	public int xA;
	public int yA;
	public int wA;
	public int hA;
	public int[] px;
	public int[] py;
	public Color colorA;
	public ShapeE shapeA;
	public String textA;
	public Font fontA;
	
	/**
	 * Generic Shape Constructor
	 */
	public Shape() {
		clear();
	}
	/**
	 * Shape constructor
	 * @param x : x position
	 * @param y : y position
	 * @param w : width OR x position 2 (for lines)
	 * @param h : height OR y position 2 (for lines)
	 * @param pxI : x position array (for polygons)
	 * @param pyI : y position array (for polygons)
	 * @param color : color
	 * @param shape : type of shape
	 * @param text : text (for text type)
	 */
	public Shape(int x, int y, int w, int h, int[] pxI, int[] pyI, Color color, ShapeE shape, String text) {
		clear();
		xA = x;
		yA = y;
		wA = w;
		hA = h;
		px = pxI;
		py = pyI;
		colorA = color;
		shapeA = shape;
		textA = text;
	}
	
	/**
	 * Set shape
	 *  -Not for text or polygon
	 * @param shape : type of shape
	 * @param x : x position
	 * @param y : y position
	 * @param w : width OR x position 2 (for lines)
	 * @param h : height OR y position 2 (for lines)
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void newShape(ShapeE shape, int x, int y, int w, int h, int r, int g, int b) {
		xA = x;
		yA = y;
		wA = w;
		hA = h;
		shapeA = shape;
		colorA = new Color(r,g,b);
		px = new int[1];
		py = new int[1];
		textA = "";
	}
	
	/**
	 * Set shape to outlined polygon
	 * @param pxI : x position array
	 * @param pyI : y position array
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void newPolygon(int[] x, int[] y, int r, int g, int b) {
		xA = 0;
		yA = 0;
		wA = 0;
		hA = 0;
		shapeA = ShapeE.POLYGON;
		colorA = new Color(r,g,b);
		px = x;
		py = y;
		textA = "";
	}
	/**
	 * Set shape to filled polygon
	 * @param pxI : x position array
	 * @param pyI : y position array
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 */
	public void newPolygonF(int[] x, int[] y, int r, int g, int b) {
		xA = 0;
		yA = 0;
		wA = 0;
		hA = 0;
		shapeA = ShapeE.POLYGONF;
		colorA = new Color(r,g,b);
		px = x;
		py = y;
		textA = "";
	}
	
	/**
	 * Set shape to text
	 * @param x : x position
	 * @param y : y position
	 * @param text : text to use
	 * @param r : red color value
	 * @param g : green color value
	 * @param b : blue color value
	 * @param font : font of text
	 */
	public void newText(int x, int y, String text, int r, int g, int b, Font font) {
		xA = x;
		yA = y;
		wA = 0;
		hA = 0;
		shapeA = ShapeE.TEXT;
		colorA = new Color(r,g,b);
		px = new int[1];
		py = new int[1];
		textA = text;
		fontA = font;
	}
	
	/**
	 * reset the Shape object to default
	 */
	public void clear() {
		xA = 0;
		yA = 0;
		wA = 0;
		hA = 0;
		px = new int[1];
		py = new int[1];
		colorA = new Color(0,0,0);
		shapeA = null;
		textA = "";
		fontA = new Font(Font.MONOSPACED, Font.PLAIN, 12);
	}
	
	/**
	 * Returns an copy of the shape offset by a value
	 * @param xo : x offset
	 * @param yo : y offset
	 * @return Offset Shape object by (xo,yo)
	 */
	public Shape offset(int xo, int yo) {
		//System.out.println("DEBUG: "+xo + "," + yo);
		
		int[] pxO = px.clone();
		for(int i = 0; i < pxO.length; i++) {
			pxO[i] += xo;
		}
		int[] pyO = py.clone();
		for(int i = 0; i < pyO.length; i++) {
			pyO[i] += yo;
		}
		
		int wI = wA;
		int hI = hA;
		
		if(shapeA == ShapeE.LINE) {
			wI = wA + xo;
			hI = hA + yo;
		}
		
		int xI = xA + xo;
		int yI = yA + yo;
		
		//System.out.println("Shape DEBUG: offset, "+xI+","+yI);
		
		Shape o = new Shape(xI,yI,wI,hI,pxO,pyO,colorA,shapeA,textA);
		//System.out.println("Shape DEBUG: test " + o.xA+","+o.yA);
		
		return o;
	}
	
	/**
	 * Draws the shape using the Graphics object g
	 * @param g : Graphics
	 * @param xO : x offset
	 * @param yO : y offset
	 */
	public void draw(Graphics g, int xO, int yO, Camera camera) {
		g.setColor(colorA);
		xO -= camera.point.x;
		yO -= camera.point.y;
//		System.out.println(xO + "," + yO);
		//System.out.println("shape DEBUG: " + xA + "," + yA + "," + wA + "," + hA);
		if(shapeA == ShapeE.RECTANGLE) {
			g.drawRect((int)((xA + xO)*camera.zoom), (int)((yA + yO)*camera.zoom), wA, hA);
		} else if(shapeA == ShapeE.RECTANGLEF) {
			g.fillRect((int)((xA + xO)*camera.zoom), (int)((yA + yO)*camera.zoom), wA, hA);
		} else if(shapeA == ShapeE.CIRCLE) {
			g.drawOval((int)((xA + xO)*camera.zoom), (int)((yA + yO)*camera.zoom), wA, hA);
		} else if(shapeA == ShapeE.CIRCLEF) {
			g.fillOval((int)((xA + xO)*camera.zoom), (int)((yA + yO)*camera.zoom), wA, hA);
		} else if(shapeA == ShapeE.LINE) {
			g.drawLine((int)((xA + xO)*camera.zoom), (int)((yA + yO)*camera.zoom), (int)((wA + xO)*camera.zoom), (int)((hA + yO)*camera.zoom));
		} else if(shapeA == ShapeE.POLYGON) {
			g.drawPolygon(zoomOffset(px, xO, camera.zoom), zoomOffset(py, yO, camera.zoom), px.length);
		} else if(shapeA == ShapeE.POLYGONF) {
			g.fillPolygon(zoomOffset(px, xO, camera.zoom), zoomOffset(py, yO, camera.zoom), px.length);
			//System.out.println("polyF");
		} else if(shapeA == ShapeE.TEXT) {
			g.setFont(fontA);
			
			String text = textA;
			int l = 0;
			for(int i = 0; i < text.length(); i++) {
				if(text.charAt(i) == '\n') {
//					System.out.println("Multiine at: " + (xA+xO) + "," + (yA+yO+l) + "; Text: \"" + text.substring(0,i) + "\"");
					g.drawString(text.substring(0,i),xA + xO, yA+l + yO);
					text = text.substring(i+1);
					l += 15;
					i = 0;
				}
			}
//			System.out.println("Multiine at: " + (xA+xO) + "," + (yA+yO+l) + "; Text: \"" + text + "\"");
			g.drawString(text,xA + xO, yA+l + yO);
		}
	}
	
	protected int[] zoomOffset(int[] a, int offset, double zoom) {
		int[] b = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (int)((a[i] + offset)*zoom);
		}
		
		return b;
	}
	
	/**
	 * Copies this shape
	 * @return copy of this shape object
	 */
	public Shape copy() {
		return new Shape(xA,yA,wA,hA,px,py,colorA,shapeA,textA);
	}
	
	/**
	 * update method for updating shapes
	 *  -Called by Drawing object
	 */
	public void update() {
		
	}
	public String save(int d) {
		String out = "";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "pos:[" + xA + "," + yA + "];" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "size:[" + wA + "," + hA + "];" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "px:[";
		for(int i = 0; i < px.length; i++) {
			out += px[i];
			if(i < px.length-1) {
				out += ",";
			}
		}
		out += "];" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "py:[";
		for(int i = 0; i < py.length; i++) {
			out += py[i];
			if(i < py.length-1) {
				out += ",";
			}
		}
		out += "];" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "color:[" + colorA.getAlpha() + "," + colorA.getRed() + "," + colorA.getGreen() + "," + colorA.getBlue() + "," + colorA.getTransparency() + "];" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "shape:" + shapeA + ";" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "text:\"" + textA + "\";" + "\n";
		
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "font:[" + fontA.getFontName() + "," + fontA.getSize() + "];" + "\n";
		
		return out;
	}
	
	/**
	 * Creates an array of LineSemgnets, intend for inputing into a collision mask, of the shape
	 * @return LineSemgnet array of the lines making up the shape
	 */
	public LineSegment[] getLines() {
		LineSegment[] lines = new LineSegment[0];;
		if(shapeA == ShapeE.RECTANGLE || shapeA == ShapeE.RECTANGLEF) {
			lines = new LineSegment[] {
					new LineSegment(xA,yA,xA+wA,yA),
					new LineSegment(xA+wA,yA,xA+wA,yA+hA),
					new LineSegment(xA,yA+hA,xA+wA,yA+hA),
					new LineSegment(xA,yA+hA,xA,yA)
			};
		} else if(shapeA == ShapeE.POLYGON || shapeA == ShapeE.POLYGONF) {
			lines = new LineSegment[px.length];
			for(int i = 0; i < px.length; i++) {
				if(i < px.length - 1) {
					lines[i] = new LineSegment(px[i], py[i], px[i+1], py[i+1]);
				} else {
					lines[i] = new LineSegment(px[i], py[i], px[0], py[0]);
				}
			}
		} else if(shapeA == ShapeE.LINE) {
			lines = new LineSegment[] { new LineSegment(xA, yA, wA, hA) };
		}
		return lines;
	}
	
	/**
	 * Creates a new Rectangle Shape
	 * @param x: x position
	 * @param y: y position
	 * @param w: width
	 * @param h: height
	 * @return
	 */
	public static Shape Rect(int x, int y, int w, int h) {
		return Rect(x,y,w,h,Color.black);
	}
	/**
	 * Creates a new Rectangle Shape
	 * @param x: x position
	 * @param y: y position
	 * @param w: width
	 * @param h: height
	 * @param c: the color of the shape
	 * @return
	 */
	public static Shape Rect(int x, int y, int w, int h, Color c) {
		return new Shape(x,y,w,h,null,null,c,ShapeE.RECTANGLE,"");
	}
	/**
	 * Creates a new filled Rectangle Shape
	 * @param x: x position
	 * @param y: y position
	 * @param w: width
	 * @param h: height
	 * @param c: the color of the shape
	 * @return
	 */
	public static Shape RectF(int x, int y, int w, int h, Color c) {
		return new Shape(x,y,w,h,null,null,c,ShapeE.RECTANGLEF,"");
	}
}
