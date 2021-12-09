package peterGames.objects;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Graphic;
import petetUtil.Config;

public abstract class GameButton extends GameObject {
	
	protected int w;
	protected int h;
	protected boolean pressed;
	
	/**
	 * constructor
	 * @param game : GameController object
	 * @param Cfg : configuration
	 * @param Name : name of button
	 * @param W : width of button
	 * @param H : height of button
	 */
	public GameButton(GameController game, Config Cfg, String Name, int W, int H) {
		super(game, Cfg);
		name = Name;
		game.addMouseUser(this);
		w = W;
		h = H;
		pressed = false;
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
	 * returns the current state of the button
	 * @return is button pressed
	 */
	public boolean isPressed() {
		return pressed;
	}
	
	/**
	 * set the pressed state
	 * @param Pressed : state to set to
	 */
	public void setPressed(boolean Pressed) {
		pressed = Pressed;
	}
	
	/**
	 * on press event
	 *  -called when button is pressed
	 * @param pressed : is button pressed
	 */
	protected abstract void onPressed(boolean pressed);
	
	/**
	 * waits for the next press of the button
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
