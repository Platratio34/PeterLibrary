package peterGames;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import dataManagment.JsonObj;
import inputs.Files;

/**
 * Manages saving and loading worlds
 * @author Peter Crall
 *
 */
public class WorldControler {
	
	private String[] worldFile;
	private int[] sectorIndex;
	private int[][] chunkIndex;
	private GameController game;
	private Map<String, GameObject> objs;
	
	public SaveType saveType = SaveType.PGS;
	
	/**
	 * Constructor for WorldController. Loads runs {@code loadWorld(String)} passing in {@code filename}
	 * @param filename : the file to load
	 * @param Game : a reference to the associated {@code GameController}
	 */
	@Deprecated
	public WorldControler(String filename, GameController Game) {
		game = Game;
		objs = new HashMap<String, GameObject>();
		loadWorld(filename);
	}
	/**
	 * Constructor for WorldController
	 * @param game : a reference to the associated {@code GameController}
	 */
	public WorldControler(GameController game) {
		this.game = game;
		objs = new HashMap<String, GameObject>();
	}
	
	/**
	 * Loads "default.txt" using {@code getClass().getResourceAsStream(String)}
	 */
	public void loadDefault() {
		try {
			String f = "default";
			if(saveType == SaveType.PGS) {
				f += ".txt";
			} else if(saveType == SaveType.JSON) {
				f += ".json";
			}
			InputStream in = getClass().getResourceAsStream(f);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			worldFile = reader.lines().toArray(String[]::new);
			
			loadWorld();
		} catch (Exception e) {
			
		}
//		System.out.println("loading default");
	}
	/**
	 * Loads a world from a {@code String[]}
	 * @param lines : the array of strings representing the lines of the world file
	 */
	public void loadWorld(String[] lines) {
		System.out.println("Starting world load from lines . . .");
//		System.out.println(lines.length+"");
		worldFile = lines;
		saveType = SaveType.PGS;
		loadWorld();
	}
	/**
	 * Loads a world using a file path.
	 * @param filename : the path of the file to load
	 */
	public void loadWorld(String filename) {
		if(filename.contains(".txt")) {
			saveType = SaveType.PGS;
		}
		if(filename.contains(".json")) {
			saveType = SaveType.JSON;
		}
		System.out.println("Starting world load: \"" + filename + "\" . . .");
		worldFile = Files.fileAsArray(filename);
		loadWorld();
	}
	/**
	 * Loads the world from {@code worldFile}
	 */
	private void loadWorld() {
		if(saveType == SaveType.PGS) {
			if(worldFile.length > 1) {
				int size = 1;
				for(int i = 0; i < worldFile[1].length(); i++) {
					if(worldFile[1].charAt(i) == ':') {
						for(int j = i + 1; j < worldFile[1].length(); j++) {
							if(worldFile[1].charAt(j) == ';') {
								String t = worldFile[1].substring(i+1,j);
								size = Integer.valueOf(t);
	//							System.out.println(size);
							}
						}
					}
				}
				
				sectorIndex = new int[size];
				for(int i = 0; i < worldFile.length; i++) {
					if(worldFile[i].charAt(0) != '\t' && worldFile[i].charAt(0) != '}' && worldFile[i].length() > 0) {
	//					System.out.println(worldFile[i]);
	//					System.out.println(worldFile[i].substring(0,7));
						if(worldFile[i].substring(0,7).equals("sector<")) {
							for(int j = 7; j < worldFile[i].length(); j++) {
	//							System.out.println(worldFile[i].charAt(j) + ":" + j);
								if(worldFile[i].charAt(j) == '>') {
									String t = worldFile[i].substring(7,j);
									int sector = Integer.valueOf(t); 
									sectorIndex[sector] = i;
									j = worldFile[i].length();
								}
							}
						}
					}
				}
				for(int i = 0; i < sectorIndex.length; i++) {
	//				System.out.println(sectorIndex[i]);
					if(i == 1) {
						System.out.println("Loading Objects . . .");
						int mLine = (sectorIndex.length>2?sectorIndex[2]-1:worldFile.length-1);
						int cLine = sectorIndex[1] + 1;
						while(cLine < mLine) {
							if(worldFile[cLine].equals("\tobject: {")) {
	//							System.out.println("Found object");
								String typ = worldFile[cLine+1].substring(7);
								for(int c = 0; c < typ.length(); c++) {
									if(typ.charAt(c)==';') {
										typ = typ.substring(0,c);
									}
								}
								ArrayList<String> lines = new ArrayList<String>();
								for(int l = cLine+1; l < mLine; l++) {
									if(worldFile[l].equals("\t}")) {
										cLine=l;
										l=mLine;
									} else {
										lines.add(worldFile[l]);
									}
								}
	//							System.out.println(typ);
								if(objs.containsKey(typ)) {
									String[] ls = lines.toArray(new String[0]);
									game.addObject(objs.get(typ).newObj(ls));
								}
							}
							
							
							cLine++;
						}
					}
				}
			} else {
				System.out.println("invalid world");
			}
		} else if(saveType == SaveType.JSON) {
			JsonObj obj = JsonObj.parseD(worldFile);
			if(obj.hasKey("objects")) {
				JsonObj[] arr = obj.getKey("objects").getArr();
				for(int i = 0; i < arr.length; i++) {
					JsonObj o = arr[i];
					String typ = o.getKey("type").string();
					if(objs.containsKey(typ)) {
						game.addObject(objs.get(typ).newObj(o));
					}
				}
			}
		}
	}
	
	/**
	 * Loads the chunk at {@code (x,y)}
	 * @param x : the x position of the chunk
	 * @param y : the y position of the chunk
	 */
	public void loadChunk(int x, int y) {
		
	}
	
	/**
	 * Saves the chunk at {@code (x,y)}
	 * @param x : the x position of the chunk
	 * @param y : the y position of the chunk
	 */
	public void saveChunk(int x, int y) {
		
	}
	
	/**
	 * Saves a {@code GameObject[]} to a file
	 * @param filename : the path of the file to save into
	 * @param objects : the array of {@code GameObejcts} to save
	 */
	public void saveWorld(String filename, GameObject[] objects) {
		if(filename.contains(".txt")) {
			saveType = SaveType.PGS;
		}
		if(filename.contains(".json")) {
			saveType = SaveType.JSON;
		}
		if(saveType == SaveType.PGS) {
			if(!filename.contains(".txt")) {
				filename += ".txt";
			}
			try {
				PrintStream stream = new PrintStream(new File(filename));
				stream.print("sector<0>: {" + "\n");
				stream.print("\tsectors:" + 2 +";\n");
				stream.print("\trefrences:;" + "\n");
				stream.print("}" + "\n");
				stream.print("sector<1>: {" + "\n");
				for(int i = 0; i < objects.length; i++) {
					stream.print(objects[i].save());
				}
				stream.print("}");
				System.out.println("---Save done---");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else if(saveType == SaveType.JSON) {
			if(!filename.contains(".json")) {
				filename += ".json";
			}
			JsonObj obj = new JsonObj();
			JsonObj objArr = new JsonObj();
			obj.setKey("objects", objArr);
			for(int i = 0; i < objects.length; i++) {
				objArr.addArray(objects[i]);
			}
			try {
				PrintStream stream = new PrintStream(new File(filename));
				stream.print(obj);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Adds a default object for world loading
	 * @param obj : the {@code GameObject} to add to the list of default objects
	 */
	public void addDefObj(GameObject obj) {
		objs.put(obj.getType(), obj);
	}
	
	public enum SaveType {
		PGS,
		JSON
	}
}
