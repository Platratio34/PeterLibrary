package commandLine;

import java.util.ArrayList;
import java.util.HashMap;

public class AdvArguments {

	private ArrayList<String> args;
	private HashMap<String, String[]> options;
	
	/**
	 * Creates a new command data object<br>
	 * Mainly used from a CmdFormater via CmdFormater.interpret()
	 */
	public AdvArguments() {
		args = new ArrayList<String>();
		options = new HashMap<String, String[]>();
	}
	
	/**
	 * Adds a string argument<br>
	 * Used from CmdFormater
	 * @param arg : the argument to add
	 */
	public void addArg(String arg) {
		args.add(arg);
	}
	
	/**
	 * Adds an option<br>
	 * Used from CmdFormater
	 * @param option : then name of the option
	 * @param args : the arguments (if any)
	 */
	public void addOption(String option, String[] args) {
		options.put(option, args);
	}
	
	/**
	 * Returns the number of string arguments
	 * @return Number of string argumenst to associated with options
	 */
	public int numArgs() {
		return args.size();
	}
	
	/**
	 * Checks if an option was in the command
	 * @param option : the name of the option
	 * @return If the option was present in the command
	 */
	public boolean hasOption(String option) {
		return options.containsKey(option);
	}
	/**
	 * Returns the arguments (if any) of an option<br>
	 * Returns null if the option was not present
	 * @param option : the name of the argument
	 * @return The arguments (if any) of the option
	 */
	public String[] option(String option) {
		if(!hasOption(option)) return null;
		return options.get(option);
	}
	
	/**
	 * Gets the argument at index
	 * @param i : the index of the arguemnt
	 * @return the string value of the argument
	 */
	public String arg(int i) {
		return args.get(i);
	}
}
