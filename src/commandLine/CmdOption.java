package commandLine;

public class CmdOption {
	
	/**
	 * The name of the option<br>
	 * Does not include the '-'
	 */
	public String name;
	/**
	 * The number of arguments that folow the option<br>
	 * Use 0 for options with no arguments
	 */
	public int numArgs;
	
	/**
	 * Creats a new CmdOption for use in CmdFormater
	 * @param name : the name of the option (excluding the '-' at the begening)
	 * @param numArgs : the number of arguemnts that folow the option, use 0 for not arguments
	 */
	public CmdOption(String name, int numArgs) {
		this.name = name;
		this.numArgs = numArgs;
	}
}
