package peterGames.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config {
	
	/**
	 * Frames per second
	 */
	public int fps;
	/**
	 * Ticks per second
	 */
	public int tps;
	/**
	 * List of key associations
	 */
	public List<Key> keys;
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
		List<Key> Keys = new ArrayList<Key> (keys.size());
		for(int i = 0; i < keys.size(); i++) {
			Keys.add(keys.get(i));
		}
		return new Config(fps,tps,Keys,width,hight,debug);
	}
	
	/**
	 * Constructor for {@code Config}
	 */
	public Config() {
		fps = 1;
		tps = 1;
		keys = new ArrayList<Key> ();
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
	public Config(int Fps, int Tps, List<Key> Keys, int Width, int Hight, int Debug) {
		fps = Fps;
		tps = Tps;
		keys = new ArrayList<Key> ();
		for(int i = 0; i < Keys.size(); i++) {
			keys.add(Keys.get(i));
		}
		width = Width;
		hight = Hight;
		debug = Debug;
	}
	
	/**
	 * Prints the config to {@code System.out}.
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
		for(int i = 0; i< keys.size(); i++) {
			out += "\t\tid: " + keys.get(i).id + " , key: " + keys.get(i).key + " , name: " + keys.get(i).name +"\n";
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
		List<Key> keys2 =  new ArrayList<Key> ();
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
				keys2.add(new Key(a,c,b));
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
		keys.add(new Key(id, name, key));
	}
	/**
	 * Adds a key to list of keys
	 * @param key : the key to add
	 */
	public void addKey(Key key) {
		keys.add(key.clone());
	}
}
