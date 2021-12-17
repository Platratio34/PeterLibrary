package peterGames;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;

import errorHandler.ErrorLogger;
import peterGames.timers.TickEvent;
import peterGames.timers.TickTimer;
import peterGames.util.Config;
import peterGraphics.util.Drawing;
import peterGraphics.util.GText;
import peterGraphics.util.Graphic;
import peterLibrary.PeterMath;

public class GameController {
	protected int Afps;
	protected int Atps;
	protected Config config;
	protected GameObject[] objects;
	protected Drawing draw;
	protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	protected boolean go;
	protected int nextFrame;
	protected int frameTime;
	protected int nextTick;
	protected int tickTime;
	protected int frames;
	protected int ticks;
	protected String name;
	protected TickTimer tickTimer;
	protected TickTimer frameTimer;
	protected TickTimer Timer;
	protected int stage;
	
	protected JFrame frame;
	public ErrorLogger eLogger;
	protected InputManeger inputManeger;
	protected List<GameObject> mouseUsers;
	protected List<GText> debugText;
	protected List<GText> infoText;
	public List<Boolean> flags;
	public List<GameObject> deadTick;
	public List<GameObject> gTick;
	public WorldControler world;
	
	public Point worldSize;
	public Point worldOffset;
	
	/** 
	 * Resets all variables
	*/
	protected void reset() {
		flags = new ArrayList<Boolean>();
		mouseUsers = new ArrayList<GameObject>();
		deadTick = new ArrayList<GameObject>();
		gTick = new ArrayList<GameObject>();
		debugText = new ArrayList<GText>();
		infoText = new ArrayList<GText>();
		stage = 0;
		frames = 0;
		frame = new JFrame(name);
		eLogger = new ErrorLogger();
		Afps = 0;
		Atps = 0;
		config = new Config();
		config.width = 600;
		config.hight = 600;
		config.tps = 30;
		config.fps = 30;
		config.debug = 0;
		objects = new GameObject[1];
		objects[0] = new GameObject(this, config) {
			
			@Override
			protected void setCollisionMask(CollisionMask mask) {
				// TODO Auto-generated method stub
			}

			@Override
			protected void setDraw(Graphic texture) {
//				texture.circle(-5, -5, 10, 10, 0, 0, 0);
			}

			@Override
			public void preInit() {
				// TODO Auto-generated method stub
				name = "game";
				parentGame.addGTick(this);
			}

			@Override
			public void postInit() {
				// TODO Auto-generated method stub
				
			}

			@Override
			protected void collided(GameObject object) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onGTick() {
				if(config.debug > 0) {
					texture.clear();
					texture.setDepth(29);
					texture.rectF(0, 0, config.width, 30, 255, 255, 255);
					texture.text(2, 12, getTime(), 0, 0, 0);
					texture.text(2, 24, "fps:" + Afps, 0, 0, 0);
					texture.text(47, 24, "tps:" + Atps, 0, 0, 0);
				}
			}
			
			@Override
			public void onTick(InputManeger input) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public String getType() {
				return "null";
			}
			
		};
		draw = new Drawing(eLogger);
		go = true;
		nextFrame = 0;
		nextTick = 0;
		frameTime = 1000/config.fps;
		tickTime = 1000/config.tps;
		eLogger.getFrame().setLocation(600, 0);
		tickTimer = new TickTimer(1,eLogger,"null");
		frameTimer = new TickTimer(1,eLogger,"null");
		Timer = new TickTimer(1,eLogger,"null");
		inputManeger = new InputManeger(config.keys);
		worldSize = new Point(config.width, config.hight);
		worldOffset = new Point();
		world = new WorldControler("default.txt", this);
	}
	
	//getters and setters
	/**
	 * Sets the frames per second of the game
	 * @param x : target FPS
	 */
	public void setfps(int x) {
		config.fps = x;
		frameTime = 1000/config.fps;
		update();
	}
	/**
	 * Sets the ticks per second of the game
	 * @param x : target TPS
	 */
	public void settps(int x) {
		config.tps = x;
		tickTime = 1000/config.tps;
		update();
	}
	/**
	 * Sets config of the game
	 * @param x : configuration
	 */
	public void setconfig(Config x) {
		config = x.copy();
		frameTime = 1000/config.fps;
		tickTime = 1000/config.tps;
		update();
	}
	/**
	 * Sets debug mode
	 * @param x : debug mode
	 * 		-0 no debug
	 * 		-1 fps,tps,time
	 * 		-2 fps,tps,time,collisions(dep)
	 */
	public void setdebugMode(int x) {
		config.debug = x;
	}
	/**
	 * gets curent time in format HH:mm:ss.SSS
	 * @return String time
	 */
	public String getTime() {
		LocalDateTime now = LocalDateTime.now();  
		return dtf.format(now);
	}
	/**
	 * gets the current target fps
	 * @return int fps
	 */
	public int getfps() {
		return Afps;
	}
	/**
	 * gets current target tps
	 * @return int tps
	 */
	public int gettps() {
		return Atps;
	}
	/**
	 * gets a refrence to the current config
	 * @return Config cfg
	 */
	public Config getconfig() {
		return config;
	}
	/**
	 * gets the array of objects
	 * @return GameObject[] objects
	 */
	public GameObject[] getobjects() {
		return objects;
	}
	/**
	 * gets the drawing object
	 * @return Drawing draw
	 */
	public Drawing getdraw() {
		return draw;
	}
	
	/**
	 * adds debug text to the screen at debug level 1
	 * @param text : GText object to display
	 */
	public void addDebugText(GText text) {
		debugText.add(text);
	}
	/**
	 * removes debug text from the screen
	 * @param text : GText to remove
	 */
	public void removeDebugText(GText text) {
		debugText.remove(text);
	}
	
	/**
	 * adds info text to screen
	 * @param text : GText to add
	 */
	public void addInfoText(GText text) {
		infoText.add(text);
	}
	/**
	 * removes info text from screen
	 * @param text : GText to remove
	 */
	public void removeInfoText(GText text) {
		infoText.remove(text);
	}
	
	/**
	 * stops the game
	 */
	public void stop() {
		tickTimer.end();
		frameTimer.end();
		Timer.end();
		frame.dispose();
	}
	
	/**
	 * runs the game
	 *  -starts tick, frame, and other timers
	 *  -runs the init cycle
	 */
	public void run() {

		System.out.println("Start Time: " + getTime());
		preinit();
		init();
		postinit();

		int time = 0;
		time = Integer.parseInt(getTime().substring(9));
		nextFrame = (time + (frameTime)) % 1000;
		nextTick = (time + (tickTime)) % 1000;
		
		tickTimer = new TickTimer(config.tps,eLogger,"tick");
		tickTimer.addToDo(new TickEvent() {

			@Override
			public void tick(long frame) {
				phisicsTick();
				ticks++;
				for(int i = 0; i < objects.length; i++) {
					if(!objects[i].destroyed) {
						objects[i].onTick(inputManeger);
					}
				}
				for(int i = 0; i < deadTick.size(); i++) {
					deadTick.get(i).deadTick(inputManeger);
				}
//				if(inputManeger.wasKeyPressedI(0)) {
//					System.out.println("Stoping");
//					stop();
//				}
				inputManeger.reset();
			}
			
		}, UUID.randomUUID());
		
		frameTimer = new TickTimer(config.fps,eLogger,"frame");
		frameTimer.addToDo(new TickEvent() {

			@Override
			public void tick(long frame2) {
				frames++;
				draw();
				frame.pack();
				frame.repaint();
			}
			
		}, UUID.randomUUID());
		
		Timer = new TickTimer(1,eLogger,"count");
		Timer.addToDo(new TickEvent() {

			@Override
			public void tick(long frame) {
				Afps = frames;
				frames = 0;
				Atps = ticks;
				ticks = 0;
			}
			
		}, UUID.randomUUID());
		
		tickTimer.start();
		frameTimer.start();
		Timer.start();
	}
	
	//constructors
	/**
	 * Generic GameController constructor
	 */
	public GameController() {
		name = "Game";
		reset();
	}
	/**
	 * GameController constructor with name
	 * @param Name : name to be displayed
	 */
	public GameController(String Name) {
		name = Name;
		reset();
	}
	/**
	 * GameController constructor with name, tps, and fps
	 * @param fps : target fps
	 * @param tps : target tps
	 * @param Name : name to be displayed
	 */
	public GameController(int fps, int tps, String Name) {
		name = Name;
		reset();
		config.fps = fps;
		config.tps = tps;
	}
	/**
	 *  GameController constructor with config and name
	 * @param x : Config to be used
	 * @param Name : name to be displayed
	 */
	public GameController(Config x, String Name) {
		name = Name;
		reset();
		config = x.copy();
	}
	
	/**
	 * phisics Tick prossesing
	 */
	public void phisicsTick() {
//		Graphic g = new Graphic();
		for(int i = 0; i < objects.length; i++) {
			if(!objects[i].destroyed) {
				for(int k = i+1; k < objects.length; k++) {
					if(!objects[k].destroyed) {
//						System.out.println("check " + objects[i].getName() + " and  " + objects[k].getName());
//						g.text(objects[i].getX(), objects[i].getY(), "1", 0, 0, 0);
//						g.text(objects[k].getX() + 6, objects[k].getY(), "2", 0, 0, 0);
						if(objects[i].checkcollide(objects[k])) {
//							eLogger.logError("collsision", "", 0, objects[i].getName()/* + " at " + objects[i].point*/ + " and " + objects[k].getName()/* + " at " + objects[k].point*/);
//							System.out.println("collision; " + objects[i].getName()/* + " at " + objects[i].point*/ + " and " + objects[k].getName()/* + " at " + objects[k].point*/);
						}
					}
				}
			}
		}
//		objects[0].setTexture(g);
	}
	
	/**
	 * Beginning of initilazation cycle
	 *  -runs preInit on all objects
	 *  -add mouse listener
	 */
	protected void preinit() {
		System.out.println("-------- PreInit --------");
		
		config.print();
		frameTime = 1000/config.fps;
		tickTime = 1000/config.tps;
		for(int i = 0; i < objects.length; i++) {
			objects[i].preInit();
		}
		stage = 1;
		
		draw.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
//				System.out.println(e.getX() + "," + e.getY());
//				System.out.println(mouseUsers.size());
				onMousePressed(e.getX(), e.getY());
			}
		});
		
		
		System.out.println("-------- PreInit --------");
		System.out.println();
	}
	/**
	 * Middle of initilazation cycle
	 *  -runs init on all objects
	 */
	protected void init() {
		System.out.println("-------- Init --------");
		
		for(int i = 0; i < objects.length; i++) {
			objects[i].Init();
		}
		stage = 2;
		System.out.println("-------- Init --------");
		System.out.println();
	}
	/**
	 * End of initilazation cycle
	 *  -initlizes the window
	 *  -runs postInit on all objects
	 */
	protected void postinit() {
		System.out.println("-------- PostInit --------");
		stage = 3;
		frame.setPreferredSize(new Dimension(config.width,config.hight));
		frame.addKeyListener(inputManeger);
		inputManeger.keyPressed(48);
		frame.getContentPane().add(draw);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		for(int i = 0; i < objects.length; i++) {
			objects[i].postInit();
			draw.addGraphic(objects[i].texture);
		}
		stage = 3;
		System.out.println("-------- PostInit --------");
		System.out.println();
	}
	
	/**
	 * add object to the global list
	 * @param x : GameObject to be added to the list
	 */
	public void addObject(GameObject x) {
		GameObject[] objectsB = new GameObject[objects.length +1];
		for(int i = 0; i < objects.length; i ++) {
			objectsB[i] = objects[i];
		}
		objectsB[objects.length] = x;
		objects = objectsB;
		if(stage == 1) {
			x.preInit();
		} else if(stage == 2) {
			x.preInit();
			x.Init();
		} else if(stage == 3) {
			x.preInit();
			x.Init();
			x.postInit();
			draw.addGraphic(x.texture);
		}
	}
	
	/**
	 * runs gTick on all objects
	 */
	public void draw() {
		for(int i = 0; i < gTick.size(); i++) {
			if(!gTick.get(i).destroyed) {
//				gTick.get(i).draw(draw);
				if(config.debug==2) {
//					gTick.get(i).drawD(draw);
				}
				gTick.get(i).onGTick();
			}
		}
//		objects[0].draw(draw);
	}
	
	/**
	 * updates inputManeger and config
	 */
	public void update() {
		System.out.println("---- Updating ----");
		tickTimer.settps(config.tps);
		frameTimer.settps(config.fps);
		config.print();
		inputManeger.setKeys(config.keys);
	}
	
	/**
	 * Checks if a GameObject at position p would collide with anythigh else
	 * @param gO : game object to check
	 * @param p : position for gameobject
	 * @return if it was colliding with anything
	 */
	public boolean colliding(GameObject gO, Point p) {
		for(int k = 0; k < objects.length; k++) {
			if(!objects[k].destroyed) {
				if(gO.checkcollide(objects[k], p)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Clamps a point the the window
	 * @param a : Point to clamp
	 * @return : Point clamped to screen
	 */
	public Point clampToScreen(Point a) {
		a.x = PeterMath.clampI(a.x, config.width, 0);
		a.y = PeterMath.clampI(a.y, config.hight, 0);
		return a;
	}
	
	/**
	 * adds a object the mouse user list
	 * @param g : GameObject to add to the list
	 */
	public void addMouseUser(GameObject g) {
		mouseUsers.add(g);
	}
	/**
	 * removes a object the mouse user list
	 * @param g : GameObject to remove to the list
	 */
	public void removeMouseUser(GameObject g) {
		mouseUsers.remove(g);
	}
	
	public void onMousePressed(int x, int y) {
		for (int i = 0; i < mouseUsers.size(); i++) {
			mouseUsers.get(i).onMousePressed(x, y);
		};
	}
	
	/**
	 * Logs an error to the ErrorLogger
	 * @param text : text to display, should contain error type
	 * @param origin : class of origin
	 * @param line : line of origin
	 * @param info : other related information
	 */
	public void errorLog(String text, String origin, int line, String info) {
		eLogger.logError(text, origin, line, info);
	}
	
	/**
	 * adds a object the dead tick list
	 * @param g : GameObject to add to the list
	 */
	public void addDeadTick(GameObject g) {
		deadTick.add(g);
	}
	/**
	 * removes a object the dead tick list
	 * @param g : GameObject to remove to the list
	 */
	public void removeDeadTick(GameObject g) {
		deadTick.remove(g);
	}
	
	/**
	 * adds a object the graphic tick list
	 * @param g : GameObject to add to the list
	 */
	public void addGTick(GameObject g) {
		gTick.add(g);
	}
	/**
	 * removes a object the graphic tick list
	 * @param g : GameObject to remove to the list
	 */
	public void removeGTick(GameObject g) {
		gTick.remove(g);
	}
	
	public void clampToWorld(Point point) {
		if(point.x < worldOffset.x) {
			point.x = worldOffset.x;
		}
		if(point.y < worldOffset.y) {
			point.y = worldOffset.y;
		}
		
		if(point.x > worldOffset.x + worldSize.x) {
			point.x = worldOffset.x + worldSize.x;
		}
		if(point.y > worldOffset.y + worldSize.y) {
			point.y = worldOffset.y + worldSize.y;
		}
	}
	
	/**
	 * saves the current world to the file
	 * @param filename : file to save the world to
	 */
	public void saveWorld(String filename) {
		world.saveWorld(filename,objects);
	}
	
	public void loadWorld(String filename) {
		world.loadWorld(filename);
	}
}
