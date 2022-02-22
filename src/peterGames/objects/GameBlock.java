package peterGames.objects;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;
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

	@Override
	public GameObject newObj(String[] file) {
		GameBlock nB = new GameBlock(parentGame, cfg, 0,0,0,0,0);
		nB.setDefParm(file);
		String sizeS = file[6].substring(8,file[6].length()-2);
		String[] sizeA = sizeS.split(",");
		nB.w = Integer.parseInt(sizeA[0]);
		nB.h = Integer.parseInt(sizeA[1]);
		String colorS = file[7].substring(9,file[7].length()-2);
		String[] colorA = colorS.split(",");
		nB.r = Integer.parseInt(colorA[0]);
		nB.g = Integer.parseInt(colorA[1]);
		nB.b = Integer.parseInt(colorA[2]);
		return nB;
	}

}
