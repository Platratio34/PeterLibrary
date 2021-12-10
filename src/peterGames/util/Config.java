package peterGames.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Config {
	public int fps;
	public int tps;
	public List<Key> keys;
	public int width;
	public int hight;
	public int debug;
	
	/**
	 * returns a copy of this config object
	 * @return : copy of this config
	 */
	public Config copy() {
		List<Key> Keys = new ArrayList<Key> (keys.size());
		for(int i = 0; i < keys.size(); i++) {
			Keys.add(keys.get(i));
		}
		return new Config(fps,tps,Keys,width,hight,debug);
	}
	
	/**
	 * default constructor
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
	 * constructor
	 * @param Fps : target fps
	 * @param Tps : target tps
	 * @param Keys : key list
	 * @param Width : width
	 * @param Hight : height
	 * @param Debug : debug level
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
	 * prints the config to the console
	 */
	public void print() {
		System.out.println(save());
	}
	
	/**
	 * returns a string that represents the config
	 * @return String : same as printed in print()
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
	 * adds a key to the list of keys
	 * @param id : internal id
	 * @param name : name of action
	 * @param key : external key id
	 */
	public void addKey(int id, String name, int key) {
		keys.add(new Key(id, name, key));
	}
	/**
	 * adds key to list of keys
	 * @param key : key to add
	 */
	public void addKey(Key key) {
		keys.add(key.clone());
	}
}
