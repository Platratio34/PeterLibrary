package dataManagment;

public interface JsonSerializable<T> {
	
	public JsonObj serilize();
	public void deserilize(JsonObj obj);
}
