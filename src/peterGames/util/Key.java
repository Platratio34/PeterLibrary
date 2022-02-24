package peterGames.util;

public class Key {
	public int id;
	public String name;
	public int key;
	
	/**
	 * Constructor for {@code Key}
	 * @param ID : the internal id of the key
	 * @param Name : the name of action
	 * @param Key : the external id of the key (from {@code KeyEvent})
	 */
	public Key(int ID, String Name, int Key) {
		id = ID;
		name = Name;
		key = Key;
	}
	
	/**
	 * Returns a copy of the key
	 * @return a copy of the key
	 */
	public Key clone() {
		return new Key(id, name, key);
	}
}
