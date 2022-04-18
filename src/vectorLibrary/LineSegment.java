package vectorLibrary;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

/**
 * 
 * @author Peter Crall
 *
 */
public class LineSegment implements JsonSerializable {
	
	protected Vector2D p1;
	protected Vector2D p2;
	protected double m;
	protected double b;
	protected boolean vertical;
	/**
	 * Makes a new LineSegment between points a and b
	 * @param x1 a.x
	 * @param y1 a.y
	 * @param x2 b.x
	 * @param y2 b.y
	 */
	public LineSegment(double x1, double y1, double x2, double y2) {
		p1 = new Vector2D(x1,y1);
		p2 = new Vector2D(x2,y2);
//		System.out.println("new line");
		update();
	}
	/**
	 * Makes a new LineSegment between points a and b
	 * @param point1 a
	 * @param point2 b
	 */
	public LineSegment(Vector2D point1, Vector2D point2) {
		p1 = point1;
		p2 = point2;
		
		update();
	}
	/**
	 * Makes a new LineSegment between from JSON
	 * @param obj the JSON object representing the line segment
	 */
	public LineSegment(JsonObj obj) {
		deserialize(obj);
	}
	
	public Vector2D getP1() {
		return new Vector2D(p1.x,p1.y);
	}
	public Vector2D getP2() {
		return new Vector2D(p2.x,p2.y);
	}
	public double getM() {
		return m;
	}
	public double getB() {
		return b;
	}
	
	public void setP1(Vector2D point) {
		p1 = new Vector2D(point.x, point.y);
		update();
	}
	public void setP2(Vector2D point) {
		p2 = new Vector2D(point.x, point.y);
		update();
	}
	
	protected void update() {
		m = (p2.y - p1.y) / (p2.x - p1.x);
		b = p1.y - (m * p1.x);
//		System.out.println(m);
		if((p2.x - p1.x)==0) {
			vertical = true;
		}	else {
			vertical = false;
		}
	}
	/**
	 * Checks if point c is on the line segment
	 * @param x  c.x
	 * @param y  c.y
	 * @return if point c is on the line segment
	 */
	public boolean isOnLine(double x, double y) {
		boolean onLine = true;
		
//		System.out.println("\tx:" + x + " y:" + y + " x2:" + p1.x + " y2:" + p1.y);
//		System.out.println("\t");
		double m2;
		if(y == p1.y && x== p1.x) {
			m2 = (y - p2.y) / (x - p2.x);
		} else {
			m2 = (y - p1.y) / (x - p1.x);
		}
//		System.out.println(m2);
		//System.out.println(m2);
		if(m2 != m) {
			onLine = false;
//			System.out.println("\t" + m2 + " != " + m);
		}
		//System.out.println(onLine);
		if(onLine) {
			if(!(x <= Math.max(p1.x,p2.x) && x >= Math.min(p1.x,p2.x) && y <= Math.max(p1.y,p2.y) && y >= Math.min(p1.y,p2.y))) {
				onLine = false;
			}
		}
		
		return onLine;
	}
	
	public boolean colliding(LineSegment line2) {
		boolean colliding = true;
		double interX = 0;
		double interY = 0;
//		boolean test = false;
//		System.out.println("g");
		interX = (line2.b + b) / (m - line2.m);
		if(m==line2.m && (!vertical || !line2.vertical)) {
			if(b==line2.b) {
				colliding = true;
//				test = true;
//				System.out.println("\t?");
			} else {
				colliding = false;
//				System.out.println("\t??");
			}
		} else if(m == line2.m && vertical) {
			if(p1.x==line2.p1.x) {
				interX = p1.x;
			}
		} else if(m==0 && line2.vertical) {
			interX = line2.p1.x;
//			System.out.println("\t2 is v");
		} else if(line2.m==0 && vertical) {
			interX = this.p1.x;
//			System.out.println("\t1 is v");
		} 
		
		if(! vertical) {
			interY = (m * interX) + b;
		} else {
			interY = (line2.m * interX) + line2.b;
		}
		//System.out.println(interX + "," + interY);
		if( !( this.isOnLine(interX,interY) && line2.isOnLine(interX,interY) ) ) {
			if(this.isOnLine(interX,interY)) {
//				System.out.println("1 true");
			}
			if(line2.isOnLine(interX,interY)) {
//				System.out.println("2 true");
			}
//			System.out.println("colliding " + colliding);
//			System.out.println(interX + "," + interY);
			colliding = false;
		}
//		System.out.println("colliding " + colliding);
		return colliding;
	}
	
	public LineSegment clone() {
		return new LineSegment(p1.copy(),p2.copy());
	}
	
	public LineSegment offset(int xo, int yo) {
//		System.out.println("offseting line " + xo + "," + yo);
		return new LineSegment(p1.x + xo, p1.y + yo, p2.x + xo, p2.y + yo);
	}
	@Override
	public JsonObj serialize() {
		JsonObj obj = new JsonObj();
		obj.setKey("p1", p1);
		obj.setKey("p2", p2);
 		return obj;
	}
	@Override
	public void deserialize(JsonObj obj) {
		if(obj.hasKey("p1")) {
			p1.deserialize(obj.getKey("p1"));
		}
		if(obj.hasKey("p2")) {
			p2.deserialize(obj.getKey("p2"));
		}
		update();
	}
}
