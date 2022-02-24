package peterGraphics.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;

/**
 * Graphical Text, used for drawing text in a {@code Drawing} object without using a {@code Drawing}
 * @author Peter Crall
 *
 */
public class GText {
	/**
	 * Location of the text
	 */
	public Point point;
	/**
	 * Text to display
	 */
	public String text;
	/**
	 * Text color
	 */
	public Color color;
	
	/**
	 * Graphical text constructor
	 * @param point_ : the location of the text
	 * @param Text : the text to display
	 * @param color_ : the color of the text
	 */
	public GText(Point point_, String Text, Color color_) {
		point = point_;
		text = Text;
		color = color_;
	}
	
	/**
	 * Draws the text using {@code java.awt.Graphics}
	 * @param g : a {@code java.awt.Graphics} object from a {@code JFrame} or {@code Drawing}
	 */
	public void draw(Graphics g) {
		g.setColor(color);
		g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
		String text2 = text;
		int l = 0;
		for(int i = 0; i < text.length(); i++) {
			if(text.charAt(i) == '\n') {
				g.drawString(text2.substring(0,i),point.x, point.y);
				text2 = text.substring(i+1);
				l += 15;
				i = 0;
			}
		}
		g.drawString(text2,point.x, point.y+l);
	}
}
