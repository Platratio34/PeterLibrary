package peterGraphics.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.Graphics;

public class GText {
	public Point point;
	public String text;
	public Color color;
	
	/**
	 * Graphical text constructor
	 * @param point_ : location of text
	 * @param Text : text to display
	 * @param color_ : color of text
	 */
	public GText(Point point_, String Text, Color color_) {
		point = point_;
		text = Text;
		color = color_;
	}
	
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
