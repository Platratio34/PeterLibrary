package peterGames;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.awt.Point;

import peterGames.util.Key;

public class InputManeger implements KeyListener {
	protected boolean[] externalD;
	protected boolean[] externalU;
	protected boolean[] externalT;
	protected List<Key> keys;
	
	/**
	 * default constructor
	 * @param Keys : List of type Key to use
	 */
	public InputManeger(List<Key> Keys) {
		reset();
		keys = Keys;
	}
	
	/**
	 * sets the List of type Key
	 * @param Keys : list to set to
	 */
	public void setKeys(List<Key> Keys) {
		keys = Keys;
	}
	
	/**
	 * resets the InputManeger
	 */
	public void reset() {
		externalD = new boolean[KeyEvent.KEY_LAST];
		externalU = new boolean[KeyEvent.KEY_LAST];
		externalT = new boolean[KeyEvent.KEY_LAST];
	}
	
	/**
	 * key pressed event from key listener
	 * @param arg0 : key event
	 */
	public void keyPressed(KeyEvent arg0) {
		externalD[arg0.getKeyCode()] = true;
//		System.out.println("Key Pressed: " + arg0.getKeyCode());
	}
	/**
	 * key pressed event from key listener
	 * @param id : key id
	 */
	public void keyPressed(int id) {
		externalD[id] = true;
//		System.out.println("Key Pressed: " + id);
	}
	
	/**
	 * key released event from key listener
	 * @param arg0 : key event
	 */
	public void keyReleased(KeyEvent arg0) {
		externalU[arg0.getKeyCode()] = true;
//		System.out.println("Key Released: " + arg0.getKeyCode());
	}
	/**
	 * key released event from key listener
	 * @param id : key id
	 */
	public void keyReleased(int id) {
		externalU[id] = true;
//		System.out.println("Key Released: " + id);
	}
	
	/**
	 * key typed event from key listener
	 * @param arg0 : key event
	 */
	public void keyTyped(KeyEvent arg0) {
		externalT[arg0.getKeyCode()] = true;
//		System.out.println("Key Typed: " + arg0.getKeyCode());
	}
	/**
	 * key typed event from key listener
	 * @param id : key id
	 */
	public void keyTyped(int id) {
		externalT[id] = true;
//		System.out.println("Key Typed: " + id);
	}
	
	/**
	 * checks if key was pressed by external id
	 * @param id : external key id
	 * @return was pressed
	 */
	public boolean wasKeyPressed(int id) {
		return externalD[id];
	}
	/**
	 * checks if key was pressed by internal id
	 * @param id : internal key id
	 * @return was pressed
	 */
	public boolean wasKeyPressedI(int id) {
		for(int i = 0; i < keys.size(); i++) {
			if(keys.get(i).id==id) {
				return externalD[keys.get(i).key];
			}
		}
		return false;
	}
	
	/**
	 * checks if key was released by external id
	 * @param id : external key id
	 * @return was released
	 */
	public boolean wasKeyReleased(int id) {
		return externalD[id];
	}
	/**
	 * checks if key was released by internal id
	 * @param id : internal key id
	 * @return was released
	 */
	public boolean wasKeyReleasedI(int id) {
		for(int i = 0; i < keys.size(); i++) {
			if(keys.get(i).id==id) {
				return externalU[keys.get(i).key];
			}
		}
		return false;
	}
	
	/**
	 * checks if key was typed by external id
	 * @param id : external key id
	 * @return was typed
	 */
	public boolean wasKeyTyped(int id) {
		return externalD[id];
	}
	/**
	 * checks if key was typed by internal id
	 * @param id : internal key id
	 * @return was typed
	 */
	public boolean wasKeyTypedI(int id) {
		for(int i = 0; i < keys.size(); i++) {
			if(keys.get(i).id==id) {
				return externalT[keys.get(i).key];
			}
		}
		return false;
	}
}
