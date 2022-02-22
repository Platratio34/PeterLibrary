package peterGames;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import inputs.Files;

public class WorldControler {
	
	String[] worldFile;
	int[] sectorIndex;
	int[][] chunkIndex;
	GameController game;
	Map<String, GameObject> objs;
	
	public WorldControler(String filename, GameController Game) {
		game = Game;
		objs = new HashMap<String, GameObject>();
//		loadWorld(filename);
	}
	
	public void loadWorld(String filename) {
		System.out.println("Starting world load: \"" + filename + "\" . . .");
		worldFile = Files.fileAsArray(filename);
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
	}
	
	public void loadChunk(int x, int y) {
		
	}
	
	public void saveChunk(int x, int y) {
		
	}
	
	public void saveWorld(String filename, GameObject[] objects) {
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
	}

	public void addDefObj(GameObject obj) {
//		System.out.println("adding " + obj.getType());
		objs.put(obj.getType(), obj);
	}
}
