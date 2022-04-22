package peterGraphics.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;
import vectorLibrary.LineSegment;

/**
 * A graphical shape, used by {@code Graphic}
 * @author Peter Crall
 *
 */
public class Shape implements JsonSerializable {	
	/**
	 * x origin of the shape
	 */
	public int xA;
	/**
	 * y origin of the shape
	 */
	public int yA;
	/**
	 * Width of the shape
	 */
	public int wA;
	/**
	 * Height of the shape
	 */
	public int hA;
	/**
	 * Array of x coordinates for polygons
	 */
	public int[] px;
	/**
	 * Array of y coordinates for polygons
	 */
	public int[] py;
	/**
	 * Color of the shape
	 */
	public Color colorA;
	/**
	 * Type of shape
	 */
	public ShapeE shapeA;
	/**
	 * Text of the shape
	 */
	public String textA;
	/**
	 * Font of the text
	 */
	public Font fontA;
	
	/**
	 * Generic Shape Constructor
	 */
	public Shape() {
		clear();
	}
	/**
	 * Shape constructor
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width (for rectange or circle) OR x position 2 (for lines)
	 * @param h : the height (for rectange or circle) OR y position 2 (for lines)
	 * @param pxI : the x coordinate array (for polygons)
	 * @param pyI : the y coordinate array (for polygons)
	 * @param color : the color
	 * @param shape : the type of shape
	 * @param text : the text (for text type)
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
	
	public Shape(JsonObj obj) {
		clear();
		deserialize(obj);
	}
	/**
	 * Set shape
	 *  -Not for text or polygon
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param w : the width (for rectange or circle) OR x position 2 (for lines)
	 * @param h : the height (for rectange or circle) OR y position 2 (for lines)
	 * @param r : the red value of the color
	 * @param g : the green value of the color
	 * @param b : the blue value of the color
	 * @param shape : the type of shape
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
	 * @param pxI : the x coordinate array
	 * @param pyI : the y coordinate array
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
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
	 * @param pxI : the x coordinate array
	 * @param pyI : the y coordinate array
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
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
	 * @param x : the x coordinate
	 * @param y : the y coordinate
	 * @param text : the text to use
	 * @param r : the red color value
	 * @param g : the green color value
	 * @param b : the blue color value
	 * @param font : the font of text
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
	 * Returns an copy of the shape offset by an x and y value
	 * @param xo : the x axis offset
	 * @param yo : the y axis offset
	 * @return An offset copy of the shape
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
	 * Draws the shape using a {@code java.awt.Graphics} object at a position
	 * @param g : the {@code java.awt.Graphics}
	 * @param xO : the x offset
	 * @param yO : the y offset
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
	
	/**
	 * Offsets and zooms an array of points into a new array
	 * @param a : the array of offset
	 * @param offset : the amount to offset by
	 * @param zoom : the zoom level
	 * @return A copy of the array offset and zoomed
	 */
	protected int[] zoomOffset(int[] a, int offset, double zoom) {
		int[] b = new int[a.length];
		for(int i = 0; i < a.length; i++) {
			b[i] = (int)((a[i] + offset)*zoom);
		}
		
		return b;
	}
	
	/**
	 * Copies the shape
	 * @return A copy of the shape
	 */
	public Shape copy() {
		return new Shape(xA,yA,wA,hA,px,py,colorA,shapeA,textA);
	}
	
	/**
	 * Update method for updating shapes
	 *  <li> Called by a Drawing object before drawing it</li>
	 */
	public void update() {}
	
	/**
	 * Returns a string representation of the shape
	 * @param d : the number of tabs to start each line with
	 * @return A string representation of the object
	 */
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
	 * @return An array of the lines making up the shape
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
	 * @param x: the x coordinate
	 * @param y: the y coordinate
	 * @param w: the width
	 * @param h: the height
	 * @return A new rectangle
	 */
	public static Shape Rect(int x, int y, int w, int h) {
		return Rect(x,y,w,h,Color.black);
	}
	/**
	 * Creates a new Rectangle Shape
	 * @param x: the x coordinate
	 * @param y: the y coordinate
	 * @param w: the width
	 * @param h: the height
	 * @param c: the color of the shape
	 * @return A new rectangle
	 */
	public static Shape Rect(int x, int y, int w, int h, Color c) {
		return new Shape(x,y,w,h,null,null,c,ShapeE.RECTANGLE,"");
	}
	/**
	 * Creates a new filled Rectangle Shape
	 * @param x: the x coordinate
	 * @param y: the y coordinate
	 * @param w: the width
	 * @param h: the height
	 * @param c: the color of the shape
	 * @return A new filled rectangle
	 */
	public static Shape RectF(int x, int y, int w, int h, Color c) {
		return new Shape(x,y,w,h,null,null,c,ShapeE.RECTANGLEF,"");
	}
	@Override
	public JsonObj serialize() {
		JsonObj obj = new JsonObj();
		obj.setKey("x", xA);
		obj.setKey("y", yA);
		obj.setKey("w", wA);
		obj.setKey("h", hA);
		obj.setKey("px", px);
		obj.setKey("py", py);
		obj.setKey("cl", new Object[] {colorA.getRed(), colorA.getGreen(), colorA.getBlue(), colorA.getAlpha()});
		obj.setKey("sh", shapeA);
		obj.setKey("tx", textA);
		obj.setKey("ft", new Object[] {fontA.getFontName(), fontA.getStyle(), fontA.getSize()});
		return obj;
	}
	@Override
	public void deserialize(JsonObj obj) {
		if(obj.hasKey("x")) {
			xA = obj.getKey("x").integer();
		}
		if(obj.hasKey("y")) {
			yA = obj.getKey("y").integer();
		}
		if(obj.hasKey("w")) {
			wA = obj.getKey("w").integer();
		}
		if(obj.hasKey("h")) {
			hA = obj.getKey("h").integer();
		}
		if(obj.hasKey("px")) {
			JsonObj[] arr = obj.getKey("px").getArr();
			px = new int[arr.length];
			for(int i = 0; i < arr.length; i++) {
				px[i] = arr[i].integer();
			}
		}
		if(obj.hasKey("py")) {
			JsonObj[] arr = obj.getKey("py").getArr();
			py = new int[arr.length];
			for(int i = 0; i < arr.length; i++) {
				py[i] = arr[i].integer();
			}
		}
		if(obj.hasKey("cl")) {
			JsonObj[] arr = obj.getKey("cl").getArr();
			colorA = new Color(arr[0].integer(), arr[1].integer(), arr[2].integer(), arr[3].integer());
		}
		if(obj.hasKey("sh")) {
			shapeA = ShapeE.valueOf(obj.getKey("sh").string());
		}
		if(obj.hasKey("tx")) {
			textA = obj.getKey("tx").string();
		}
		if(obj.hasKey("ft")) {
			JsonObj[] arr = obj.getKey("ft").getArr();
			fontA = new Font(arr[0].string(), arr[1].integer(), arr[2].integer());
		}
	}
}
