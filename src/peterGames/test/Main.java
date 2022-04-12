package peterGames.test;

import java.awt.event.KeyEvent;

import peterGames.GameController;
import peterGames.WorldControler.SaveType;
import peterGames.objects.GamePlayer;
import peterGames.util.Config;
import peterGraphics.util.Camera;

public class Main {
	
	private static GameController game;
	
	public static void main(String[] args) {
		
		// Create a new game controller
		game = new GameController();
		
		// Set the save type to JSON
		game.setSaveType(SaveType.JSON);
		
		// Set up the config
		Config cfg = game.getconfig();
		cfg.addKey(0,"up",KeyEvent.VK_W);
		cfg.addKey(1,"down",KeyEvent.VK_S);
		cfg.addKey(2,"left",KeyEvent.VK_A);
		cfg.addKey(3,"right",KeyEvent.VK_D);
		
		// Add custom GameObjects for save file loading
		game.addDefObj(new Player(game, cfg));
		
		// Add objects to the game via code
		Player player = new Player(game, cfg);
		player.move(10,10);
		game.addObject(player);
		Player player2 = new Player(game, cfg);
		player2.move(10,50);
		game.addObject(player2);
		game.addObject(new GamePlayer(game,cfg,2));
		
		// Load a save file
//		game.loadWorld("save.json");
		
		// Move the camera to -100, -100
		Camera camera = game.getdraw().getCamera();
		camera.point.x = -100;
		camera.point.y = -100;
		
		// Start the game
		game.run();
		
		// Saves the game
//		game.saveWorld("save.json");
	}
	
}
