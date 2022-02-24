package peterGames.objects;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;

/**
 * Simple button in game. Tag: "button". Can be extended for custom functionality
 * @author Peter Crall
 *
 */
public abstract class GameButton extends GameObject {
	
	protected int w;
	protected int h;
	protected boolean pressed;
	
	/**
	 * constructor
	 * @param game : the current {@code GameController} object
	 * @param Cfg : the current {@code Config} from the game
	 * @param Name : the name of button
	 * @param W : the width of button
	 * @param H : the height of button
	 */
	public GameButton(GameController game, Config Cfg, String Name, int W, int H) {
		super(game, Cfg);
		name = Name;
		game.addMouseUser(this);
		w = W;
		h = H;
		pressed = false;
		tag = "button";
	}
	/**
	 * constructor
	 * @param game : the current {@code GameController} object
	 * @param Cfg : the current {@code Config} from the game
	 * @param Name : the name of button
	 * @param x : the starting x coordinate
	 * @param y : the starting y coordinate
	 * @param W : the width of button
	 * @param H : the height of button
	 */
	public GameButton(GameController game, Config Cfg, String Name, int x, int y, int W, int H) {
		super(game, Cfg);
		name = Name;
		moveA(x,y);
		game.addMouseUser(this);
		w = W;
		h = H;
		pressed = false;
		tag = "button";
	}
	
	@Override
	public void onMousePressed(int x, int y) {
		if(x >= point.x && y >= point.y && x <= point.x + w && y <= point.y + h) {
			pressed = !pressed;
//			System.out.println("button Pressed " + pressed);
			onPressed(pressed);
		}
	}
	
	/**
	 * Gets the current state of the button
	 * @return if the button is pressed
	 */
	public boolean isPressed() {
		return pressed;
	}
	
	/**
	 * Sets the state of the button
	 * @param Pressed : the new state
	 */
	public void setPressed(boolean Pressed) {
		pressed = Pressed;
		onPressed(true);
	}
	
	/**
	 * Called when the button is pressed
	 *  <li> Called from onMousePressed if it is inside the button size </li>
	 * @param pressed : the new button state
	 */
	protected abstract void onPressed(boolean pressed);
	
	/**
	 * Waits for the next press of the button, then returns. NOT RECOMENDED, ties up the main thred until the button is pressed
	 * @return true
	 */
	public Boolean nextPress() {
		while(!pressed) {
			System.out.print("");
		}
		return true;
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rect(0, 0, w, h, 128, 128, 128);
		texture.text(4,15,name,0,0,0);
	}

	@Override
	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTick(InputManeger input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void collided(GameObject object) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public String onSave() {
		String out = "";
		
		out += "\t\tsize:[" + w + "," + h + "];" + "\n";
		out += "\t\tpressed:" + pressed + ";" + "\n";
		out += onSaved();
		
		return out;
	}
	
	public  String onSaved() {
		return "\t\tnull;" + "\n";
	}

}
