package peterGames.test;

import java.awt.Point;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.objects.GamePlayer;
import peterGames.util.Config;
import peterGraphics.util.Graphic;
import peterGraphics.util.Shape;

public class Player extends GameObject {
	
	Point dir;
	private int hN = 0;
	
	public Player(GameController game, Config Cfg) {
		super(game, Cfg);
		dir = new Point(1,0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addShape(Shape.Rect(0,0,20,20));
		
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(0, 0, 20, 20, 0, 0, 0);
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
		if(point.x > 100) {
			dir.x = -1;
		}
		if(point.x < 10) {
			dir.x = 1;
		}
		move(dir);
	}

	@Override
	protected void collideEnter(GameObject object) {
		// TODO Auto-generated method stub
		if(object instanceof GamePlayer) {
			System.out.println("HIT! " + hN + " with " + object.getType());
			hN++;
		}
	}

	@Override
	public String getType() {
		return "Player";
	}

	@Override
	public GameObject newObj(String[] file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GameObject newObj(JsonObj obj) {
		Player np = new Player(parentGame, cfg);
		np.setDefParm(obj);
		JsonObj[] dirA = obj.getKey("dir").getArr();
		np.dir.x = dirA[0].integer();
		np.dir.y = dirA[1].integer();
		return np;
	}
	
	@Override
	public void onSave(JsonObj obj) {
		obj.setKey("dir", new Object[] {dir.x, dir.y});
	}

}
