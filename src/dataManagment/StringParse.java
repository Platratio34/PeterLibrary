package dataManagment;

/**
 * StringParse of type <T> is the return type for all of StringParser
 * @author Peter Crall
 * @param <T> The type of data returned
 */
public class StringParse<T> {
  
	/**
	 * The number of charecters that were parsed through
	 */
	public int l;
	/**
	 * The return data
	 */
	public T data;

	/** 
	 * Constructor for StringParse
	 * @param data The return data
	 * @param l The number of charecters that were parsed through
	**/ 
	public StringParse(T data, int l) {
		this.data = data;
		this.l = l;
	}
}
