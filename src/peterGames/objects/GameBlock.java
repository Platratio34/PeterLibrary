package peterGames.objects;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGraphics.util.Graphic;
import petetUtil.Config;
import vectorLibrary.LineSegment;

public class GameBlock extends GameObject {
	protected int w;
	protected int h;
	protected int r;
	protected int g;
	protected int b;
	
	public GameBlock(GameController game, Config Cfg, int W, int H, int R ,int B, int G) {
		super(game, Cfg);
		w = W;
		h = H;
		r = R;
		g = G;
		b = B;
		tag = "block";
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		mask.addLine(new LineSegment(0,0,0,h));
		mask.addLine(new LineSegment(0,0,w,0));
		mask.addLine(new LineSegment(w,0,w,h));
		mask.addLine(new LineSegment(0,h,w,h));
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.rectF(0, 0, w, h, r, g, b);
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
		out += "\t\tcolor:[" + r + "," + g + "," + b + "];" + "\n";
		
		return out;
	}

	@Override
	public String getType() {
		return "GameBlock";
	}

}
