package peterGames;

import java.awt.Point;

import peterGames.util.Config;
import peterGraphics.util.Drawing;
import peterGraphics.util.Graphic;

public abstract class GameObject {
	protected Graphic texture;
	protected Point point;
	protected CollisionMask mask;
	protected String name;
	protected GameController parentGame;
	protected boolean destroyed;
	protected Config cfg;
	protected String tag;
	
	/**
	 * Parent constructor
	 * @param game : GameController object
	 * @param Cfg : Config of game
	 */
	public GameObject(GameController game, Config Cfg) {
		parentGame = game;
		setup();
		cfg = Cfg;
	}
	
	/**
	 * Sets up the variables for use
	 */
	public void setup() {
		tag = "null";
		destroyed = false;
		texture = new Graphic();
		point = new Point();
		mask = new CollisionMask();
		name = "object";
	}
	
	/**
	 * Initlization event
	 *  -Sets texture
	 *  -Sets collision mask
	 */
	public void Init() {
		setDraw(texture);
//		System.out.println(point.toString());
		texture.setPoint(point);
		setCollisionMask(mask);
	}
	/**
	 * Sets up Collision Mask
	 * @param mask : Mask to add to
	 */
	protected abstract void setCollisionMask(CollisionMask mask);
	/**
	 * Sets up Graphic
	 * @param texture : Graphic to add to
	 */
	protected abstract void setDraw(Graphic texture);
	
	/**
	 * Pre-Init event
	 *  -Called by GameController
	 */
	public abstract void preInit();
	/**
	 * Post-Init event
	 *  -Called by GameController
	 */
	public abstract void postInit();
	
	/**
	 * on Graphic Tick event
	 *  -Called by GameController
	 * Must have subscribed to graphic tick list on parentGame to use
	 */
	public void onGTick() {
		
	}
	/**
	 * On Tick Event
	 *  -Called by GameController
	 * @param input : Input manger to use from Game
	 */
	public abstract void onTick(InputManeger input);
	
	/**
	 * sets Graphic
	 * @param x : Graphic to set texture to
 	 */
	public void setTexture(Graphic x) {
		texture = x;
		System.out.println("texture chnaged");
	}
	
	/**
	 * on collision with object
	 *  -Called by GameController during physics tick
	 * @param object
	 */
	protected abstract void collided(GameObject object);
	
	/**
	 * get X pos of object
	 * @return int point.x
	 */
	public int getX() {
		return point.x;
	}
	/**
	 * get Y pos of object
	 * @return int point.y
	 */
	public int getY() {
		return point.y;
	}
	/**
	 * get point of object
	 * @return Point point
	 */
	public Point getPoint() {
		return (Point) point.clone();
	}
	/**
	 * sets point of object
	 * @param p : Point to set to copy of
	 */
	public void setPoint(Point p) {
		point = (Point) p.clone();
	}
	
	/**
	 * sets the depth of the object
	 */
	public void setDepth(int Depth) {
		texture.setDepth(Depth);
	}
	
	/**
	 * Moves the object by point
	 * @param p : Point offest 
	 */
	public void move(Point p) {
		point.x += p.x;
		point.y += p.y;
	}
	/**
	 * Moves the object by x and y
	 * @param x : x offest
	 * @param y : y offest
	 */
	public void move(int x, int y) {
		point.x += x;
		point.y += y;		
	}
	
	/**
	 * get name of Object
	 * @return String : name
	 */
	public String getName() {
		return name;
	}
	/**
	 * get tag of object
	 * @return String : tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * is object destroyed
	 * @return boolean : destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}
	
	/**
	 * Check if object is colliding with other
	 * @param other : GameObject to check collision against
	 * @return boolean : is colliding
	 */
	protected boolean checkcollide(GameObject other) {
		if(mask.checkCollide(other.mask, this.point, other.point)) {
//			System.out.println(this.point + " " + other.point);
			collided(other);
			other.collided(this);
			return true;
		} else {
			return false;
		} 
	}
	
	@Deprecated
	/**
	 *  *DEPRICATED*
	 *  adds texture to Drawing object
	 * @param draw : Drawing object to add to
	 */
	public void draw(Drawing draw) 
	{
		draw.addGraphic(texture,point.x,point.y);
	}
	@Deprecated
	/**
	 *  *DEPRICATED*
	 *  adds debug texture to Drawing object
	 * @param draw : Drawing object to add to
	 */
	public void drawD(Drawing draw) 
	{
		draw.addGraphic(mask.getGraphic(),point.x,point.y);
	}
	
	/**
	 * destroyes object
	 *  -sets destroyed to true
	 *  -sets texture draw to false
	 */
	public void destroy() {
		destroyed = true;
		texture.setDraw(false);
	}
	
	/**
	 * On mouse down
	 *  -must be subscribed to MouseLister list on parentGame
	 *  -called by mouseListener
	 * @param x : x pos of click
	 * @param y : y pos of click
	 */
	public void onMousePressed(int x, int y) {
		
	}
	
	/**
	 * set Destroyed status
	 * @param x : is destroyed
	 */
	public void setDestroyed(boolean x) {
		destroyed = x;
		texture.setDraw(!x);
	}
	
	/**
	 * on tick if destroyed
	 *  -called by parentGame
	 * @param input : Input manger to use from Game
	 */
	public void deadTick(InputManeger input) {
		
	}
	
	
	public String save() {
		String out = "";
		out += "\tobject: {" + "\n";
		out += "\t\ttype:" + getType() + ";" + "\n";
		out += "\t\tposition:[" + point.x + "," + point.y + "];" + "\n";
		out += "\t\trotation:[" + 0 +"];" + "\n";
		out += "\t\tdestroyed:" + destroyed + ";" + "\n";
		out += "\t\tname:\"" + name + "\";" + "\n";
		out += "\t\ttag:\"" + tag + "\";" + "\n";
//		out += "\t\tmask: {" + "\n";
//		out += mask.save(3);
//		out += "\t\t}" + "\n";
//		out += "\t\ttexture: {" + "\n";
//		out += texture.save(3);
//		out += "\t\t}" + "\n";
		out += onSave();
		out += "\t}" + "\n";
		return out;
	}
	
	/**
	 * Returns a string that is all of the special data<br>
	 * all lines should start with "\t\t"<br>
	 * all lines should end with ";" all text after this will be ignored<br>
	 * use "[" and "]" to define a comma separated array<br>
	 * use "{" and "}" to define a inner data area<br>
	 * semicolons are not necessary after a "{" or "}"<br>
	 * use "#" to denote a comment, any line that has this character outside of a string is counted<br>
	 * put a ":" between the keyword and the value<br>
	 * @return text
	 */
	public String onSave() {
		return "\t\tnull;" + "\n";
	}
	
	public abstract String getType();
}
