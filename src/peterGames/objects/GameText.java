package peterGames.objects;

import java.awt.Font;

import dataManagment.JsonObj;
import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;

/**
 * Simple in game text. Tag: "text". Uses it's name as the text
 * @author peter
 *
 */
public class GameText extends GameObject {

	protected Font font;
	
	/**
	 * Constructor for {@code GameText}
	 * @param game : the current {@GameController}
	 * @param Cfg : the curretn {@code Config} from game
	 * @param Name : the text to display
	 */
	public GameText(GameController game, Config Cfg, String Name) {
		super(game, Cfg);
		name = Name;
		font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		tag = "text";
	}
	/**
	 * Constructor for {@code GameText}
	 * @param game : the current {@GameController}
	 * @param Cfg : the curretn {@code Config} from game
	 * @param Name : the text to display
	 * @param font_ : the font to use
	 */
	public GameText(GameController game, Config Cfg, String Name, Font font_) {
		super(game, Cfg);
		name = Name;
		font = font_;
		tag = "text";
	}

	@Override
	protected void setCollisionMask(CollisionMask mask) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void setDraw(Graphic texture) {
		texture.text(0, 0, name, 0, 0, 0, font);
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
	
	/**
	 * Sets the text to something
	 * @param text : the new text
	 */
	public void setText(String text) {
		name = text;
		texture.clear();
		texture.setPoint(point);
		setDraw(texture);
	}
	/**
	 * Sets the text to something, and changes the font
	 * @param text : the new text
	 * @param font_ : the new font
	 */
	public void setText(String text, Font font_) {
		name = text;
		font = font_;
		texture.clear();
		texture.setPoint(point);
		setDraw(texture);
	}
	
	@Override
	public String getType() {
		return "GameText";
	}
	
	@Override
	public String onSave() {
		String out = "";
		out += "\t\tfont:[" + font.getFontName() + "," + font.getStyle() + "," + font.getSize() + "];" + "\n";
		
		return out;
	}
	@Override
	public void onSave(JsonObj obj) {
		obj.setKey("font", new Object[] {font.getFontName(), font.getStyle(), font.getSize()});
	}
	
	@Override
	public GameObject newObj(String[] file) {
		GameText nT = new GameText(parentGame,cfg,"");
		nT.setDefParm(file);
		String[] fPrms = file[6].substring(8,file[6].length()-2).split(",");
		setText(nT.name, new Font( fPrms[0], Integer.parseInt(fPrms[1]), Integer.parseInt(fPrms[2]) ) );
		return nT;
	}
	@Override
	public GameObject newObj(JsonObj obj) {
		GameText nT = new GameText(parentGame,cfg,"");
		nT.setDefParm(obj);
		JsonObj[] fnt = obj.getKey("font").getArr();
		setText(nT.name, new Font( fnt[0].string(), fnt[0].integer(), fnt[0].integer() ) );
		return nT;
	}

}
