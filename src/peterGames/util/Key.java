package peterGames.util;

import dataManagment.JsonObj;
import dataManagment.JsonSerializable;

/**
 * Key for custom key maping, links internal and external id to a name
 * @author peter
 *
 */
public class Key implements JsonSerializable {
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
	 * Constructor for {@code Key}
	 * @param obj JsonObj to deserilize from
	 */
	public Key(JsonObj obj) {
		deserilize(obj);
	}
	
	/**
	 * Returns a copy of the key
	 * @return a copy of the key
	 */
	public Key clone() {
		return new Key(id, name, key);
	}

	@Override
	public JsonObj serilize() {
		JsonObj obj = new JsonObj();
		obj.setKey("id", id);
		obj.setKey("name", name);
		obj.setKey("key", key);
		return obj;
	}

	@Override
	public void deserilize(JsonObj obj) {
		if(obj.hasKey("id")) {
			id = obj.getKey("id").integer();
		}
		if(obj.hasKey("name")) {
			name = obj.getKey("name").string();
		}
		if(obj.hasKey("key")) {
			id = obj.getKey("key").integer();
		}
	}
}
