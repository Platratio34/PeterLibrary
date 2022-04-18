package peterGames.objects;

import java.util.List;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGames.util.Key;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

/**
 * Basic player charecter. Uses inputs: "up"/"forward", "down"/"backword", "left", and "right". Tag: "player"
 * @author Peter Crall
 *
 */
public class GamePlayer extends GameObject {
	
	/**
	 * list of control ids
	 */
	private int[] controls = new int[4];
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
	 * @param Cfg : the curretn {@code Config} from game
	 * @param Speed : the speed of the player
	 */
	public GamePlayer(GameController game, Config Cfg, int Speed) {
		super(game, Cfg);
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
	public void preInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postInit() {
		controls[0] = cfg.getKeyE("up");
		controls[1] = cfg.getKeyE("down");
		controls[2] = cfg.getKeyE("left");
		controls[3] = cfg.getKeyE("right");
	}

	@Override
	public void onTick(InputManeger input) {
		if(input.wasKeyPressedI(controls[0])) {
			up = true;
		}
		if(input.wasKeyReleasedI(controls[0])) {
			up = false;
		}
		
		if(input.wasKeyPressedI(controls[1])) {
			down = true;
		}
		if(input.wasKeyReleasedI(controls[1])) {
			down = false;
		}
		
		if(input.wasKeyPressedI(controls[2])) {
			left = true;
		}
		if(input.wasKeyReleasedI(controls[2])) {
			left = false;
		}
		
		if(input.wasKeyPressedI(controls[3])) {
			right = true;
		}
		if(input.wasKeyReleasedI(controls[3])) {
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
	protected void collided(GameObject object) {
		// TODO Auto-generated method stub
		
	}

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
		GamePlayer nP = new GamePlayer(parentGame, cfg, 0);
		nP.setDefParm(file);
		nP.speed = Integer.parseInt(file[6].substring(8,file[6].length()-1));
		return nP;
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		GamePlayer nP = new GamePlayer(parentGame, cfg, 0);
		nP.setDefParm(obj);
		nP.speed = obj.getKey("speed").integer();
		return nP;
	}

}
