package peterGames;

import java.awt.Point;

import peterGraphics.util.Graphic;
import peterLibrary.*;
import vectorLibrary.LineSegment;

public class CollisionMask {
	protected LineSegment[] lines;
	protected Graphic thing;
	
	/**
	 * default constructor
	 */
	public CollisionMask() {
		reset();
	}
	
	/**
	 * gets a copy of the LineSegment[]
	 * @return copy of lines
	 */
	public LineSegment[] getLines() {
		return lines.clone();
	}
	
	/**
	 * sets the LineSegment[] to x
	 * @param x : LineSegment[] to set to
	 */
	public void setLines(LineSegment[] x) {
		lines = x.clone();
	}
	
	/**
	 * returns a graphic with lines representing the collision mask
	 * @return Graphic
	 */
	public Graphic getGraphic() {
		return thing;
	}
	
	/**
	 * add a line to the mask
	 * @param line : LineSegment to add
	 */
	public void addLine(LineSegment line) {
		lines = Arrays.expand(lines, line);
		thing.line((int)line.getP1().x, (int)line.getP1().y, (int)line.getP2().x, (int)line.getP2().y, 255, 0, 0);
	}
	
	/**
	 * resets to mask
	 */
	public void reset() {
		lines = new LineSegment[1];
		lines[0] = new LineSegment(0,0,0,0);
		thing = new Graphic();
	}
	
	/**
	 * checks if it is collision with other mask at given offset
	 * @param mask : other mask
	 * @param offset : this mask's position
	 * @param oOffset : other mask's position
	 * @return is colliding
	 */
	public boolean checkCollide(CollisionMask mask, Point offset, Point oOffset) {
		boolean collided = false;
		LineSegment[] other = mask.getLines();
		for(int i = 1; i < lines.length; i++) {
			for(int j = 1; j < other.length; j++) {
//				System.out.println("mabey " + i + " " + j);
				if(lines[i].offset(offset.x, offset.y).colliding(other[j].offset(oOffset.x, oOffset.y))) {
//					System.out.println("yay");
					collided = true;
				}
			}
		}
		
		return collided;
	}
	
	/**
	 * saves the mask to a string
	 * @param d : depth (number of tabs)
	 * @return string version of mask;
	 */
	public String save(int d) {
		String out = "";
		for(int i = 0; i < d; i++) {
			out += "\t";
		}
		out += "null; no saving yet" + "\n";
		return out;
	}
}
