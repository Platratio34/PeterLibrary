package peterGraphics.util;

/**
 * An Enum defining what type of shape.
 * Used by {@code Shape}
 * @author Peter Crall
 *
 */
public enum ShapeE {
	/**
	 * An outline of a rectangle
	 */
	RECTANGLE,
	/**
	 * A filled rectangle
	 */
	RECTANGLEF,
	/**
	 * A line
	 */
	LINE,
	/**
	 * An outline of a circle
	 */
	CIRCLE,
	/**
	 * A filled circle
	 */
	CIRCLEF,
	/**
	 * An outline of a custom polygon
	 */
	POLYGON,
	/**
	 * A filled in custom polygon
	 */
	POLYGONF,
	/**
	 * Text
	 */
	TEXT;
}
