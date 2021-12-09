package petetUtil;

public class Key {
	public int id;
	public String name;
	public int key;
	
	/**
	 * default constructor
	 * @param ID : internal id
	 * @param Name : name of action
	 * @param Key : external key id
	 */
	public Key(int ID, String Name, int Key) {
		id = ID;
		name = Name;
		key = Key;
	}
	
	/**
	 * returns a copy of the key
	 * @return : copy of key
	 */
	public Key clone() {
		return new Key(id, name, key);
	}
}
