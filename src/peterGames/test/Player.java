package peterGames.test;

import java.awt.Point;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Graphic;
import petetUtil.Config;

public class Player extends GameObject {
	
	Point dir;
	
	public Player(GameController game, Config Cfg) {
		super(game, Cfg);
		dir = new Point(1,0);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		// TODO Auto-generated method stub
		
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
	protected void collided(GameObject object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "Player";
	}

}
