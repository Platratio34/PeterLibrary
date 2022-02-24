package peterGames;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import peterGames.util.Config;
import peterGraphics.util.Drawing;
import peterGraphics.util.Graphic;

public abstract class GameObject {
	
	/**
	 * Texture of the object
	 */
	protected Graphic texture;
	/**
	 * Current location of the object
	 */
	protected Point point;
	/**
	 * Collision mask of the object
	 */
	protected CollisionMask mask;
	/**
	 * The name of the object. Instance specific
	 */
	protected String name;
	/**
	 * The game the object is in
	 */
	protected GameController parentGame;
	/**
	 * If the object is in the destroyed state
	 */
	protected boolean destroyed;
	/**
	 * The game configuration
	 */
	protected Config cfg;
	/**
	 * The tag of the object
	 */
	protected String tag;
	
	/**
	 * Used for calling {@code collideEnter()} and {@code collideExit()} in {@code checkCollide()}
	 */
	private List<GameObject> lCollide;
	
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
		lCollide = new ArrayList<GameObject>();
	}
	
	/**
	 * Initlization event. Should not be overrided, use {@code preInit()} or {@code postInit()} instead
	 * <ul>
	 *  <li> Sets texture </li>
	 *  <li> Sets collision mask </li>
	 * </ul>
	 */
	public void Init() {
		setDraw(texture);
//		System.out.println(point.toString());
		texture.setPoint(point);
		setCollisionMask(mask);
	}
	
	/**
	 * Sets up the collision mask
	 * @param mask : the mask to set up
	 */
	protected abstract void setCollisionMask(CollisionMask mask);
	
	/**
	 * Sets up the texture
	 * @param texture : the texture to set up
	 */
	protected abstract void setDraw(Graphic texture);
	
	/**
	 * Pre-Init event.
	 * <ul>
	 *  <li> Called by GameController at the beginning of the initialization phase </li>
	 * </ul>
	 */
	public abstract void preInit();
	
	/**
	 * Post-Init event.
	 * <ul>
	 *  <li> Called by GameController at the end of the initialization phase </li>
	 * </ul>
	 */
	public abstract void postInit();
	
	/**
	 * on Graphic Tick event
	 * <ul>
	 *  <li> Called by GameController every frame </li>
	 *  <li> Must have subscribed to graphic tick list on parentGame to be called </li>
	 * </ul>
	 */
	public void onGTick() {
		
	}
	
	/**
	 * On Tick Event
	 * <ul>
	 *  <li> Called by GameController every logical tick </li>
	 * </ul>
	 * @param input : input maneger to use from Game
	 */
	public abstract void onTick(InputManeger input);
	
	/**
	 * Sets the texture
	 * @param x : Graphic to set texture to
 	 */
	public void setTexture(Graphic x) {
		texture = x;
		System.out.println("texture chnaged");
	}
	
	/**
	 * Called  when the object collides with something else
	 *  <li> Called by GameController during physics tick </li>
	 *  <li> Also called by {@code moveC()} if the movement collided with something </li>
	 * @param object : the object that collided with this object
	 */
	protected void collided(GameObject object) {}
	
	/**
	 * Called on the first tick that an object is colliding with this object
	 *  <li> Called by GameController during physics tick </li>
	 * @param object
	 */
	protected void collideEnter(GameObject object) {}
	
	/**
	 * Called when an object is no longer colliding with this object
	 *  <li> Called by GameController during physics tick </li>
	 * @param object
	 */
	protected void collideExit(GameObject object) {}
	
	/**
	 * Gets the x position of the object
	 * @return The current x position of the object
	 */
	public int getX() {
		return point.x;
	}
	/**
	 * Gets the y position of the object
	 * @return The current y position of the object
	 */
	public int getY() {
		return point.y;
	}
	/**
	 * Gets combined point of the object
	 * @return The current combined position
	 */
	public Point getPoint() {
		return (Point) point.clone();
	}
	/**
	 * Sets the point of the object.
	 * @param p : the point to set to
	 * @deprecated Use {@code moveA()} instead
	 */
	@Deprecated
	public void setPoint(Point p) {
		point = (Point) p.clone();
	}
	
	/**
	 * Sets the draw depth of the texture
	 * @param Depth : the new draw depth of the texture
	 */
	public void setDepth(int Depth) {
		texture.setDepth(Depth);
	}
	
	/**
	 * Moves the object by an amount
	 * @param p : the amount to move by
	 */
	public void move(Point p) {
		point.x += p.x;
		point.y += p.y;
	}
	/**
	 * Moves the object by an amount
	 * @param x : the amount to move in the x axis
	 * @param y : the amount to move in the y axis
	 */
	public void move(int x, int y) {
		point.x += x;
		point.y += y;		
	}
	
	/**
	 * Moves only as much as it can so that it does not collide with something else.
	 * If the movement would result in a collision, then it decreases the distance by one tenth and tries again, repeating until it does not collide.
	 * If the initial movement would result in a collision, it calls {@code collided()} on this object and the object is is colliding with.
	 * @param p : the amount to move by
	 * @param ignoreTag : the tag to ignore in the collision check
	 * @return If it moved at all, returns false if no movement along that direction is possible
	 */
	public boolean moveC(Point p, String ignoreTag) {
		GameObject other = parentGame.collidingG(this, new Point(p.x + point.x, p.y + point.y),ignoreTag);
		GameObject other2 = other;
		while(other != null) {
			if(p.x == 0 && p.y == 0) {
				return false;
			}
			p.x /= 2;
			p.y /= 2;
			other2 = other;
			other = parentGame.collidingG(this, new Point(p.x + point.x, p.y + point.y),ignoreTag);
		}
		move(p);
		if(other2 != null) {
			collided(other2);
			other2.collided(this);
		}
		return true;
	}
	/**
	 * Moves only as much as it can so that it does not collide with something else.
	 * If the movement would result in a collision, then it decreases the distance by one tenth and tries again, repeating until it does not collide.
	 * If the initial movement would result in a collision, it calls {@code collided()} on this object and the object is is colliding with.
	 * @param p : the amount to move by
	 * @return If it moved at all, returns false if no movement along that direction is possible
	 */
	public boolean moveC(Point p) {
		return moveC(p,"");
	}
	/**
	 * Moves only as much as it can so that it does not collide with something else.
	 * If the movement would result in a collision, then it decreases the distance by one tenth and tries again, repeating until it does not collide.
	 * If the initial movement would result in a collision, it calls {@code collided()} on this object and the object is is colliding with.
	 * @param x : the amount to move in the x axis
	 * @param y : the amount to move in the y axis
	 * @return If it moved at all, returns false if no movement along that direction is possible
	 */
	public boolean moveC(int x, int y) {
		return moveC(new Point(x,y),"");
	}
	/**
	 * Moves only as much as it can so that it does not collide with something else.
	 * If the movement would result in a collision, then it decreases the distance by one tenth and tries again, repeating until it does not collide.
	 * If the initial movement would result in a collision, it calls {@code collided()} on this object and the object is is colliding with.
	 * @param x : the amount to move in the x axis
	 * @param y : the amount to move in the y axis
	 * @param ignoreTag : the tag to ignore in the collision check
	 * @return If it moved at all, returns false if no movement along that direction is possible
	 */
	public boolean moveC(int x, int y, String ignoreTag) {
		return moveC(new Point(x,y),ignoreTag);
	}
	
	/**
	 * Moves to the point {@code (x,y)}
	 * @param x : the x coordinate of the point to move to
	 * @param y : the y coordinate of the point to move to
	 */
	public void moveA(int x, int y) {
		point.x = x;
		point.y = y;
	}
	
	/**
	 * Gets the name of the object
	 * @return The name of the object
	 */
	public String getName() {
		return name;
	}
	/**
	 * Gets the tag of the object
	 * @return The tag of the object
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * Gets if the object is in the destroyed state
	 * @return If the object is destroyed
	 */
	public boolean isDestroyed() {
		return destroyed;
	}
	
	/**
	 * Check if the object is colliding with other and triggers collided() on the both if they are
	 * @param other : the {@code GameObject} to check collision against
	 * @return If they are colliding
	 */
	protected boolean checkcollide(GameObject other) {
		if(mask.checkCollide(other.mask, this.point, other.point)) {
//			System.out.println(this.point + " " + other.point);
			collided(other);
			other.collided(this);
			if (!lCollide.contains(other)) {
				collideEnter(other);
				other.collideEnter(this);
				lCollide.add(other);
			}
			return true;
		} else {
			if(lCollide.contains(other)) {
				lCollide.remove(other);
				collideExit(other);
				other.collideExit(this);
			}
			return false;
		} 
	}
	/**
	 * Checks collision with offset for this game object, does not trigger collided()
	 * @param other : GameObject to check collision against
	 * @param p : Position offset
	 * @return if they collided
	 */
	protected boolean checkcollide(GameObject other, Point p) {
		if(mask.checkCollide(other.mask, p, other.point)) {
			return true;
		} else {
			return false;
		} 
	}
	
	
	/**
	 * Adds the texture to Drawing object
	 * @deprecated
	 * @param draw : Drawing object to add to
	 */
	@Deprecated
	public void draw(Drawing draw) {
		draw.addGraphic(texture,point.x,point.y);
	}
	/**
	 * Adds the debug texture to Drawing object
	 * @deprecated
	 * @param draw : Drawing object to add to
	 */
	@Deprecated
	public void drawD(Drawing draw) {
		draw.addGraphic(mask.getGraphic(),point.x,point.y);
	}
	
	/**
	 * Destroys the object
	 *  <li> sets destroyed to true </li>
	 *  <li> sets texture draw to false </li>
	 */
	public void destroy() {
		destroyed = true;
		texture.setDraw(false);
		onDestroy();
	}
	
	/**
	 * Called when the mouse is pressed
	 *  <li> Called by the {@code mouseListener} on the window </li>
	 *  <li> The object must be subscribed using {@code parentGame.addMouseListener()} to be called </li>
	 * @param x : the x position of the click
	 * @param y : the y position of the click
	 */
	public void onMousePressed(int x, int y) {}
	
	/**
	 * Sets the destroyed state
	 * @param x : the new destroyed state
	 */
	public void setDestroyed(boolean x) {
		destroyed = x;
		texture.setDraw(!x);
		if(x) {
			onDestroy();
		}
	}
	
	/**
	 * Called instead of {@code onTick()} if the object is currently destroyed
	 *  <li> Called by parentGame on the logical tick </li>
	 * @param input : the input manager from parentGame
	 */
	public void deadTick(InputManeger input) {}
	
	/**
	 * Called when the object is destroyed
	 */
	protected void onDestroy() {}
	
	/**
	 * Saves the object to a string
	 *  <li> Called by parentGame on world save </li>
	 *  <li> Calls {@code onSave()} </li>
	 * @return the object as a string
	 */
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
	 * Returns a string that is all of the special data.
	 * <li> All lines should start with "\t\t" </li>
	 * <li> All lines should end with ";" all text after this will be ignored </li>
	 * <li> Use "[" and "]" to define a comma separated array </li>
	 * <li> Use "{" and "}" to define a inner data area </li>
	 * <li> Semicolons are not necessary after a "{" or "}" </li>
	 * <li> Use "#" to denote a comment, any line that has this character outside of a string is not interpreted </li>
	 * <li> Put a ":" between the keyword and the value </li>
	 * @return text
	 */
	public String onSave() {
		return "\t\tnull;" + "\n";
	}
	
	/**
	 * Returns the type of object, class specific
	 * @return the type of the object
	 */
	public abstract String getType();
	
	/**
	 * Creates a new object of the same type based off of a portion of a world file
	 *  <li> Called by the WorldController when loading a world </li>
	 *  <li> should call {@code setDefParm(String[]) on the new object</li>
	 * @param file : the lines of the world file pertaining to the object
	 * @return A new object of the same class with all class specific parameters loaded
	 */
	public abstract GameObject newObj(String[] file);
	
	/**
	 * Sets all generic variables of {@code GameObject}
	 *  <li> Sets position, rotation, destroyed state, name, and tag</li>
	 * @param file : the lines of the world file pertaining to the object
	 */
	protected void setDefParm(String[] file) {
		String posS = file[1].substring(12,file[1].length()-2);
		String[] posSA = posS.split(",");
		point.x = Integer.parseInt(posSA[0]);
		point.y = Integer.parseInt(posSA[1]);
		// TODO when rotation is added, add it here
		destroyed = Boolean.parseBoolean(file[3].substring(12,file[3].length()-1));
		name = file[4].substring(8,file[4].length()-2);
		tag = file[5].substring(7,file[5].length()-2);
	}
}
