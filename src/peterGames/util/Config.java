package peterGames.util;

import java.util.HashMap;
import java.util.Scanner;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

/**
 * Configuration file for a game. Has tps, fps, size, debug mode, and key maping
 * @author peter
 *
 */
public class Config implements JsonSerializable {
	
	/**
	 * Frames per second
	 */
	public int fps;
	/**
	 * Ticks per second
	 */
	public int tps;
	/**
	 * Map of key associations
	 */
	public HashMap<String, Key> keys;
	/**
	 * Width of the window
	 */
	public int width;
	/**
	 * Height of the window
	 */
	public int hight;
	/**
	 * Debuge mode
	 */
	public int debug;
	
	/**
	 * Returns a copy of this config object
	 * @return Copy of this config
	 */
	public Config copy() {
		HashMap<String, Key> Keys = new HashMap<String, Key> ();
		for(Key k : keys.values()) {
			Keys.put(k.name, k);
		}
		return new Config(fps,tps,Keys,width,hight,debug);
	}
	
	/**
	 * Constructor for {@code Config}
	 */
	public Config() {
		fps = 1;
		tps = 1;
		keys = new HashMap<String, Key> ();
		width = 1;
		hight = 1;
		debug = 0;
	}
	/**
	 * Constructor for {@code Config}
	 * @param Fps : the frames per second
	 * @param Tps : the ticks per second
	 * @param Keys : the list of key assosiations
	 * @param Width : the width of the window
	 * @param Hight : the height of the window
	 * @param Debug : the debug mode
	 */
	public Config(int Fps, int Tps, HashMap<String, Key> Keys, int Width, int Hight, int Debug) {
		fps = Fps;
		tps = Tps;
		keys = new HashMap<String, Key> ();
		for(Key k : Keys.values()) {
			keys.put(k.name, k);
		}
		width = Width;
		hight = Hight;
		debug = Debug;
	}
	/**
	 * Constructor for {@code Config}
	 * @param obj : JsonObj representation of the config
	 */
	public Config(JsonObj obj) {
		deserialize(obj);
	}
	
	/**
	 * Prints the config to {@code System.out}.<br>
	 * Prints the output of {@code save()}
	 */
	public void print() {
		System.out.println(save());
	}
	
	/**
	 * Returns a string representing the config
	 * @return String representation of the config
	 */
	public String save() {
		String out = "Config {" + "\n";
		out += "\tfps: " + fps + " , tps: " + tps + " , width: " + width + " , hight: " + hight + "\n";
		out += "\tdebug mode: " + debug + "\n";
		out += "\tKeys: " + keys.size() + " {\n";
		for(Key k : keys.values()) {
			out += "\t\tid: " + k.id + " , key: " + k.key + " , name: " + k.name +"\n";
		}
		out += "\t}" + "\n";
		out += "}" + "\n";
		return out;
	}
	
	/**
	 * Loads the config from a string
	 * @param str : the file to load from
	 */
	public void load(String str) {
		Scanner scan = new Scanner(str);
		int Ifps = 0;
		int Itps = 0;
		int Iwidth = 0;
		int Ihight = 0;
		int Idebug = 0;
		HashMap<String, Key> keys2 =  new HashMap<String, Key> ();
		if(scan.next().equals("Config")) {
			scan.nextLine();
			scan.next();
			if(scan.hasNextInt()) {
				Ifps = scan.nextInt();
				scan.next();
			}
			
			scan.next();
			if(scan.hasNextInt()) {
				Itps = scan.nextInt();
				scan.next();
			}
			
			scan.next();
			if(scan.hasNextInt()) {
				Iwidth = scan.nextInt();
				scan.next();
			}
			
			scan.next();
			if(scan.hasNextInt()) {
				Ihight = scan.nextInt();
				scan.next();
			}
			
			scan.next();
			if(scan.hasNextInt()) {
				Idebug = scan.nextInt();
				scan.next();
			}
			
			int j = scan.nextInt();
			scan.next();
			for(int i = 0; i < j; i++) {
				int a = 0;
				int b = 0;
				String c = "";
				scan.next();
				if(scan.hasNextInt()) {
					a = scan.nextInt();
					scan.next();
				}
				
				scan.next();
				if(scan.hasNextInt()) {
					b = scan.nextInt();
					scan.next();
				}
				
				scan.next();
				if(scan.hasNext()) {
					c = scan.next();
				}
				keys2.put(c, new Key(a,c,b));
			}
			
		}
		fps = Ifps;
		tps = Itps;
		width = Iwidth;
		hight = Ihight;
		debug = Idebug;
		keys = keys2;
		scan.close();
	}
	
	/**
	 * Adds a key to the list of keys
	 * @param id : the internal id of the key
	 * @param name : the name of action
	 * @param key : the external id of the key (from {@code KeyEvent})
	 */
	public void addKey(int id, String name, int key) {
		keys.put(name, new Key(id, name, key));
	}
	/**
	 * Adds a key to list of keys
	 * @param key : the key to add
	 */
	public void addKey(Key key) {
		keys.put(key.name, key.clone());
	}

	@Override
	public JsonObj serialize() {
		JsonObj obj = new JsonObj();
		obj.setKey("fps", fps);
		obj.setKey("tps", tps);
		obj.setKey("width", width);
		obj.setKey("hight", hight);
		obj.setKey("debug", debug);
		JsonObj kObj = new JsonObj();
		obj.setKey("keys", kObj);
		for(Key k : keys.values()) {
			kObj.addArray(k);
		}
		return obj;
	}

	@Override
	public void deserialize(JsonObj obj) {
		if(obj.hasKey("fps")) {
			fps = obj.getKey("fps").integer();
		}
		if(obj.hasKey("tps")) {
			tps = obj.getKey("tps").integer();
		}
		if(obj.hasKey("width")) {
			width = obj.getKey("width").integer();
		}
		if(obj.hasKey("hight")) {
			hight = obj.getKey("hight").integer();
		}
		if(obj.hasKey("debug")) {
			debug = obj.getKey("debug").integer();
		}
		if(obj.hasKey("keys")) {
			JsonObj[] arr = obj.getKey("keys").getArr();
			for(int i = 0; i < arr.length; i++) {
				Key k = new Key(arr[i]);
				addKey(k);
			}
		}
		
	}
}
