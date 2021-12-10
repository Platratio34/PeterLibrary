package peterGames.objects;

import java.awt.Font;

import peterGames.CollisionMask;
import peterGames.GameController;
import peterGames.GameObject;
import peterGames.InputManeger;
import peterGames.util.Config;
import peterGraphics.util.Graphic;

public class GameText extends GameObject {

	Font font;
	
	/**
	 * default constructor
	 * @param game : GameController
	 * @param Cfg : configuration
	 * @param Name : text to display
	 */
	public GameText(GameController game, Config Cfg, String Name) {
		super(game, Cfg);
		name = Name;
		font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
		// TODO Auto-generated constructor stub
	}
	/**
	 * constructor
	 * @param game : GameController
	 * @param Cfg : configuration
	 * @param Name : text to display
	 * @param font_ : font to use
	 */
	public GameText(GameController game, Config Cfg, String Name, Font font_) {
		super(game, Cfg);
		name = Name;
		font = font_;
		// TODO Auto-generated constructor stub
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
	 * set the text to something
	 * @param text : String to set the text to
	 */
	public void setText(String text) {
		name = text;
		texture.clear();
		texture.setPoint(point);
		setDraw(texture);
	}
	/**
	 * set the text to something with a font
	 * @param text : String to set the text to
	 * @param font_ : font to use
	 */
	public void setText(String text, Font font_) {
		name = text;
		texture.clear();
		texture.setPoint(point);
		setDraw(texture);
		font = font_;
	}
	
	@Override
	public String getType() {
		return "GameText";
	}
	
	@Override
	public String onSave() {
		String out = "";
		
		out += "\t\tfont:[" + font.getFontName() + "," + font.getSize() + "];" + "\n";
		
		return out;
	}

}
