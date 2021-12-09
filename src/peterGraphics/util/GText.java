package peterGraphics.util;

import java.awt.Color;
import java.awt.Point;

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
}
