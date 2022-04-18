package peterGames.objects;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;
import vectorLibrary.LineSegment;

/**
 * Simple block GameObject. has a matching collision mask. Tag: "block"
 * @author Peter Crall
 *
 */
public class GameBlock extends GameObject {
	protected int w;
	protected int h;
	protected int r;
	protected int g;
	protected int b;
	
	/**
	 * Constructor for {@code GameBlock}
	 * @param game : the current {@GameController} object
	 * @param Cfg : the current {@code Config} from the game
	 * @param W : the width of the block
	 * @param H : the height of the block
	 * @param R : the red value of the color
	 * @param G : the green value of the color
	 * @param B : the blue value of the color
	 * @deprecated Use {@code GameBlock(GameController,int,int,int,int,int,int,int) instead
	 */
	@Deprecated
	public GameBlock(GameController game, Config Cfg, int W, int H, int R ,int B, int G) {
		super(game, Cfg);
		w = W;
		h = H;
		r = R;
		g = G;
		b = B;
		tag = "block";
	}
	/**
	 * Constructor for {@code GameBlock}
	 * @param game : the current {@GameController}
	 * @param Cfg : the current {@code Config} from game
	 * @param x : the starting x coordinate
	 * @param y : the starting y coordinate
	 * @param W : the width of the block
	 * @param H : the height of the block
	 * @param R : the red value of the color
	 * @param G : the green value of the color
	 * @param B : the blue value of the color
	 * @deprecated Use {@code GameBlock(GameController,int,int,int,int,int,int,int) instead
	 */
	@Deprecated
	public GameBlock(GameController game, Config Cfg, int x, int y, int W, int H, int R ,int G, int B) {
		super(game);
		moveA(x,y);
		w = W;
		h = H;
		r = R;
		g = G;
		b = B;
		tag = "block";
	}
	/**
	 * Constructor for {@code GameBlock}
	 * @param game : the current {@GameController}
	 * @param x : the starting x coordinate
	 * @param y : the starting y coordinate
	 * @param W : the width of the block
	 * @param H : the height of the block
	 * @param R : the red value of the color
	 * @param G : the green value of the color
	 * @param B : the blue value of the color
	 */
	public GameBlock(GameController game, int x, int y, int W, int H, int R ,int G, int B) {
		super(game);
		moveA(x,y);
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
	public void onSave(JsonObj obj) {
		obj.setKey("size", new Object[] {w,h});
		obj.setKey("color", new Object[] {r,g,b});
	}

	@Override
	public String getType() {
		return "GameBlock";
	}

	@Override
	public GameObject newObj(String[] file) {
		GameBlock nB = new GameBlock(parentGame,0,0,0,0,0,0,0);
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
	@Override
	public GameObject newObj(JsonObj obj) {
		GameBlock nB = new GameBlock(parentGame,0,0,0,0,0,0,0);
		nB.setDefParm(obj);
		JsonObj[] size = obj.getKey("size").getArr();
		nB.w = size[0].integer();
		nB.h = size[1].integer();
		JsonObj[] color = obj.getKey("color").getArr();
		nB.r = color[0].integer();
		nB.g = color[1].integer();
		nB.b = color[2].integer();
		return nB;
	}

}
