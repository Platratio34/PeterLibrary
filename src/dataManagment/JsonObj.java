package dataManagment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.nio.file.Files;

/**
 * A java representation of a JSON object
 * @author peter
 *
 */
public class JsonObj {

	private HashMap<String, JsonObj> objects;
	private ArrayList<JsonObj> array;
	private String value;
	private boolean lit;
	private boolean cmpt = false;
	
	/**
	 * Constructor for JsonObj
	 */
	public JsonObj() {
		clear();
	}
	/**
	 * Creates a new JsonObj with value
	 * @param value the value of the object
	 */
	public JsonObj(Object value) {
		clear();
		if(value instanceof Object[]) {
			Object[] arr = (Object[])value;
			for(int i = 0; i < arr.length; i++) {
				array.add(new JsonObj(arr[i]));
			}
		} else {
			setValue(value);
		}
	}
	/**
	 * Creates new JsonObj with a value or entries
	 * @param str The value or JSON entries
	 * @param parse If the string is the value or JSON entries
	 */
	public JsonObj(String str, boolean parse) {
        clear();
        if(!parse) {
            setValue(str);
        } else {
            if(str.length() > 0) {
                if(str.charAt(0) == '{') {
                    JsonObj obj = StringParser.parseObject(str).data;
                    objects = obj.objects;
                } else if(str.charAt(0) == '[') {
                    JsonObj obj = StringParser.parseForArray(str).data;
                    array = obj.array;
                } else {
                	setValue(str);
                }
            }
        }
    }
	/**
	 * Creates a new JsonObj as an JSON array
	 * @param arr The content of the array
	 */
	public JsonObj(JsonObj[] arr) {
		clear();
		for(int i = 0; i < arr.length; i++) {
			array.add(arr[i]);
		}
	}
	/**
	 * Creates a new JsonObj as an JSON array
	 * @param arr The content of the array
	 */
	public JsonObj(ArrayList<JsonObj> arr) {
		clear();
		array = arr;
	}
	
	/**
	 * Clears the JsonObj
	 */
	public void clear() {
		objects = new HashMap<String, JsonObj>();
		array = new ArrayList<JsonObj>();
		value = "";
		lit = false;
		cmpt = false;
	}
	
	/**
	 * Parses a JSON file and loads the data into this object
	 * @param file The file to parse
	 * @return if parsing was successful
	 */
	public boolean load(File file) {
		if(!file.exists()) {
			System.out.println("ERROR: JsonObj.load(); File did not exist: " + file.getPath());
			return false;
		}
		try {
			String f = Files.readString(file.toPath());
			return load(f);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Parses a JSON file and loads the data into this object
	 * @param file The file to parse
	 * @return if parsing was successful
	 */
	public boolean load(String file) {
		JsonObj o2 = StringParser.parseObject(file).data;
		objects = o2.objects;
		array = o2.array;
		value = o2.value;
		lit = o2.lit;
		return true;
	}
	
	/**
	 * Returns a string representation of the object in JSON format
	 */
	@Override
	public String toString() {
		return toString(0, false);
	}
	/**
	 * Returns a string representation of the object in JSON format
	 * @param ind Number of indents for each line to start with
	 * @param c If it is running in compact mode
	 * @return a string representation of the object
	 */
	public String toString(int ind, boolean c) {
		if(objects.size() == 0 && array.size() == 0) {
			if(lit) {
				return value;
			} else {
				return "\""+value+"\"";
			}
		}
		if(array.size() != 0) {
			String str = "[";
			if(array.size() <= 2 || cmpt) {
				for(int i = 0; i < array.size(); i++) {
					str += array.get(i).toString();
					if(i < array.size()-1) {
						str += ",";
						if(!c) str += " ";
					}
				}
				return str + "]";
			} else {
				if(!c && !cmpt) str += "\n";
				for(int i = 0; i < array.size(); i++) {
					if(!c && !cmpt) str += indent(ind+1);
					str += array.get(i).toString(ind+1, c);
					if(i < array.size()-1) {
						str += ",";
						if(!c && !cmpt) str += "\n";
					}
				}
				if(!c && !cmpt) return str + "\n" + indent(ind) + "]";
				return str + "]";
			}
		}
		String str = "{";
		boolean s = true;
		for (Map.Entry<String, JsonObj> ent : objects.entrySet()) {
			if(!s) {
				str += ",";
			}
			s = false;
			if(!c && !cmpt) str += "\n" + indent(ind+1);
			str += "\"" + ent.getKey() + "\":";
			if(!c && !cmpt) str += " ";
			str += ent.getValue().toString(ind+1, c);
		}
		if(!c && !cmpt) return str + "\n" + indent(ind) + "}";
		return str + "}";
	}
	/**
	 * Returns a compact string representation of the object in JSON format
	 * @return a compact string representation of the object
	 */
	public String toStringC() {
		return toString(0, true);
	}
	
	
	/**
	 * Sets entry key to a JSON object
	 * @deprecated Use {@code setKey(String, Object)}
	 * @param key The key to set
	 * @param section the JSON object value
	 */
	@Deprecated
	public void setObject(String key, JsonObj section) {
		objects.put(key, section);
	}
	
	/**
	 * Sets entry key to a value
	 * @param key The key to set
	 * @param value The value of the entry
	 */
	public void setKey(String key, Object value) {
		if(value instanceof JsonObj) {
			objects.put(key, (JsonObj)value);
			return;
		}
		if (value instanceof JsonSerializable) {
			objects.put(key, ((JsonSerializable) value).serialize());
			return;
		}
		objects.put(key, new JsonObj(value));
	}
	
	/**
	 * Sets the value of the JsonObj<br>
	 * If the value is a number, boolean, or null, also marks the object as a literal
	 * @param value The value of the object (.toSring() is used to convert to String)
	 */
	public void setValue(Object value) {
		if(value instanceof Integer) {
			lit = true;
		} else if(value instanceof Boolean) {
			lit = true;
		} else if(value instanceof Float) {
			lit = true;
		} else if(value instanceof Double) {
			lit = true;
		} else if(value instanceof Long) {
			lit = true;
		} else if(value == null) {
			lit = true;
			this.value = "null";
			return;
		}
		this.value = value.toString();
		this.value = this.value.replace("\\",  "\\\\");
		this.value = this.value.replace("\n",  "\\n");
		this.value = this.value.replace("\r",  "\\r");
		this.value = this.value.replace("\t",  "\\t");
		this.value = this.value.replace("\"",  "\\\"");
	}
	
	/**
	 * Adds a JsonObj to the array
	 * @param obj The JsonObj to add
	 */
	public void addArray(JsonObj obj) {
		array.add(obj);
	}
	/**
	 * Adds an Object to the array
	 * @param value The Object to add
	 */
	public void addArray(Object value) {
		if (value instanceof JsonSerializable) {
			array.add(((JsonSerializable) value).serialize());
			return;
		}
		array.add(new JsonObj(value));
	}
	
	private String indent(int ind) {
		String str = "";
		for(int i = 0; i < ind; i++) {
			str += "\t";
		}
		return str;
	}
	
	/**
	 * Sets the compact state of the JsonObj (for arrays)
	 * @param cmpt If the object should be compacted
	 */
	public void setC(boolean cmpt) {
		this.cmpt = cmpt;
	}
	
	/**
	 * Used in JSON parsing
	 * @param str
	 */
	public void add(String str) {
        String[] r = StringParser.parseForKV(str).data;
        if(r[1].length() == 0) {
            // Debug.LogError("Adding value of length 0. key: \"" + r[0] + "\"");
            objects.put(r[0], new JsonObj(r[1], false));
            return;
        } else if(r[1].charAt(0) == '{') {
            objects.put(r[0], new JsonObj(r[1], true));
            return;
        } else if(r[1].charAt(0) == '[') {
            objects.put(r[0], StringParser.parseForArray(r[1]).data);
            return;
        }
        objects.put(r[0], new JsonObj(r[1], false));
    }
	
	/**
	 * If the JsonObj has an entry with key
	 * @param key The key to check for
	 * @return if the object has the key
	 */
	public boolean hasKey(String key) {
		return objects.containsKey(key) && objects.get(key)!=null;
	}
	
	/**
	 * Returns the value of the key
	 * @param key The key to get
	 * @return the value of the key
	 */
	public JsonObj getKey(String key) {
		if(objects.containsKey(key)) {
			return objects.get(key);
		} else {
			return null;
		}
	}
	
	/**
	 * Returns a String from the value
	 * @return the value
	 */
	public String string() {
		String str = value;
		str = str.replace("\\\\",  "\\");
		str = str.replace("\\n",  "\n");
		str = str.replace("\\r",  "\r");
		str = str.replace("\\t",  "\t");
		str = str.replace("\\\"",  "\"");
		return str;
	}
	/**
	 * Returns a integer from the value
	 * @return the value
	 */
	public int integer() {
		return Integer.parseInt(value);
	}
	/**
	 * Returns a float from the value
	 * @return the value
	 */
	public float floating() {
		return Float.parseFloat(value);
	}
	/**
	 * Returns a long from the value
	 * @return the value
	 */
	public long longInt() {
		return Long.parseLong(value);
	}
	/**
	 * Returns a double from the value
	 * @return the value
	 */
	public double doubleP() {
		return Double.parseDouble(value);
	}
	/**
	 * Returns a boolean from the value
	 * @return the value
	 */
	public boolean bool() {
		return Boolean.parseBoolean(value);
	}
	
	/**
	 * Returns the element at index i of the array
	 * @param i The index
	 * @return element at i
	 */
	public JsonObj arr(int i) {
		if(i < array.size()) {
			return array.get(i);
		}
		return null;
	}
	
	/**
	 * Gets the JsonObj array
	 * @return the JsonObj array
	 */
	public JsonObj[] getArr() {
		return array.toArray(new JsonObj[0]);
	}
	
	public HashMap<String, JsonObj> getMap() {
		return objects;
	}
	
	public void removeKey(String key) {
		if(objects.containsKey(key)) {
			objects.remove(key);
		}
	}
	
	/**
	 * Returns a JsonObj parsed from a file at path
	 * @param path The path of the file to parse
	 * @return A new JsonObj from file
	 */
	public static JsonObj parseP(String path) {
		JsonObj obj = new JsonObj();
		obj.load(new File(path));
		return obj;
	}
	/**
	 * Returns a JsonObj parsed from a string
	 * @param data The string to parse
	 * @return A new JsonObj from string
	 */
	public static JsonObj parseD(String data) {
		JsonObj obj = new JsonObj();
		obj.load(data);
		return obj;
	}
	/**
	 * Returns a JsonObj parsed from a string array
	 * @param data The string array to parse
	 * @return A new JsonObj from string
	 */
	public static JsonObj parseD(String[] data) {
		String str = "";
		for(int i = 0; i < data.length; i++) {
			if(i>0) str += "\n";
			str += data[i];
		}
		return parseD(str);
	}
}
