package peterGames.test;

import java.awt.event.KeyEvent;

import peterGames.GameController;
import peterGames.objects.GamePlayer;
import peterGraphics.util.Camera;
import petetUtil.Config;

public class Main {
	
	private static GameController game;
	
	public static void main(String[] args) {
		game = new GameController();
		Config cfg = game.getconfig();
		cfg.addKey(0,"up",KeyEvent.VK_W);
		cfg.addKey(1,"down",KeyEvent.VK_S);
		cfg.addKey(2,"left",KeyEvent.VK_A);
		cfg.addKey(3,"right",KeyEvent.VK_D);
		Player player = new Player(game, cfg);
		player.move(10,10);
		game.addObject(player);
		game.addObject(new GamePlayer(game,cfg,2));
		game.run();
		Camera camera = game.getdraw().getCamera();
		camera.point.x = -100;
		camera.point.y = -100;
		game.saveWorld("save.txt");
	}
	
}
