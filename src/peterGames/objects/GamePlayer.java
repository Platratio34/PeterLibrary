package peterGames.objects;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

/**
 * Basic player character. Uses inputs: "up", "down", "left", and "right". Tag: "player"
 * @author Peter Crall
 *
 */
public class GamePlayer extends GameObject {
	
	/**
	 * The speed of the player
	 */
	protected int speed;
	private boolean up;
	private boolean down;
	private boolean left;
	private boolean right;
	
	/**
	 * Constructor for {@code GamePlayer}
	 * @param game : the current {@GameController}
	 * @param Cfg : the current {@code Config} from game
	 * @param Speed : the speed of the player
	 * @deprecated Use GamePlayer(GameControllre, int)
	 */
	@Deprecated
	public GamePlayer(GameController game, Config Cfg, int Speed) {
		super(game);
		speed = Speed;
		up = false;
		down = false;
		left = false;
		right = false;
		tag = "player";
	}
	/**
	 * Constructor for {@code GamePlayer}
	 * @param game : the current {@GameController}
	 * @param Speed : the speed of the player
	 */
	public GamePlayer(GameController game, int Speed) {
		super(game);
		speed = Speed;
		up = false;
		down = false;
		left = false;
		right = false;
		tag = "player";
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addShape(Shape.Rect(0,0,25,25));
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(0,0,25,25,10,10,200);
	}

	@Override
	public void preInit() {}

	@Override
	public void postInit() {}

	@Override
	public void onTick(InputManeger input) {
		if(input.wasKeyPressed("up")) {
			up = true;
		}
		if(input.wasKeyReleased("up")) {
			up = false;
		}
		
		if(input.wasKeyPressed("down")) {
			down = true;
		}
		if(input.wasKeyReleased("down")) {
			down = false;
		}
		
		if(input.wasKeyPressed("left")) {
			left = true;
		}
		if(input.wasKeyReleased("left")) {
			left = false;
		}
		
		if(input.wasKeyPressed("right")) {
			right = true;
		}
		if(input.wasKeyReleased("right")) {
			right = false;
		}
		
		if(up) {
			move(0,-speed);
		}
		if(down) {
			move(0,speed);
		}
		if(left) {
			move(-speed,0);
		}
		if(right) {
			move(speed,0);
		}
	}

	@Override
	protected void collided(GameObject object) {}

	@Override
	public String getType() {
		return "GamePlayer";
	}
	
	@Override
	public String onSave() {
		return "\t\tspeed:" + speed + ";\n";
	}
	@Override
	public void onSave(JsonObj obj) {
		obj.setKey("speed", speed);
	}
	
	@Override
	public GameObject newObj(String[] file) {
		GamePlayer nP = new GamePlayer(parentGame, 0);
		nP.setDefParm(file);
		nP.speed = Integer.parseInt(file[6].substring(8,file[6].length()-1));
		return nP;
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		GamePlayer nP = new GamePlayer(parentGame, 0);
		nP.setDefParm(obj);
		nP.speed = obj.getKey("speed").integer();
		return nP;
	}

}
