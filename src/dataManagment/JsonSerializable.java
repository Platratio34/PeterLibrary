package dataManagment;

/**
 * JSON Serialization interface<br>
 * Implementing classes should have a constructor that takes in a JsonObj and runs deserialize with with it
 * @author Peter Crall
 *
 */
public interface JsonSerializable {
	
	/**
	 * Serialize the object as JSON
	 * @return a new JsonObj representing the object
	 */
	public JsonObj serialize();
	/**
	 * Deserialize from a JsonObj
	 * @param obj : The object to deserialize from
	 */
	public void deserialize(JsonObj obj);
}
