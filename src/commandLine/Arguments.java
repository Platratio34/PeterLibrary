package commandLine;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Command line argument parser
 * @author Peter Crall
 *
 */
public class Arguments {
	
	private ArrayList<String> options;
	private ArrayList<String> params;
	private HashMap<String, String> optionVars;
	
	/**
	 * Creates a new Arguments object from the command line arguments
	 * @param args : the String array from main(String[] args)
	 */
	public Arguments(String[] args) {
		options = new ArrayList<String>();
		params = new ArrayList<String>();
		optionVars = new HashMap<String, String>();
		for(int i = 0; i < args.length; i++) {
//			System.out.println(args[i]);
			if(args[i].indexOf("--") == 0) {
				String str = args[i].substring(2);
				String[] opts = str.split("=");
				if(opts.length != 2) {
					try {
						throw new Exception("Invalid command line option: " + args[i]);
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				optionVars.put(opts[0],opts[1]);
			} else if(args[i].indexOf("-") == 0) {
				String str = args[i].substring(1);
				options.add(str);
			} else {
				params.add(args[i]);
			}
			
		}
	}
	
	/**
	 * Returns the vale of the option
	 * @param option : the name of the option
	 * @return If the option was present
	 */
	public boolean option(String option) {
		if(options.contains(option)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the value of an optionVar
	 * @param option : the name of the option
	 * @return The value, if it was present, otherwise an empty string
	 */
	public String optionVar(String option) {
		if(optionVars.containsKey(option)) {
			return optionVars.get(option);
		}
		return "";
	}
	/**
	 * If an optionVar was present
	 * @param option : the name of the option
	 * @return If the optionVar was present
	 */
	public boolean hasOptionVar(String option) {
		return optionVars.containsKey(option);
	}
	
	/**
	 * Returns the parameter at i
	 * @param i : the index of the parameter
	 * @return The paramter at index i
	 */
	public String get(int i) {
		return params.get(i);
	}
	
	/**
	 * Returns how many parameters were persent
	 * @return The number of parameters
	 */
	public int numParams() {
		return params.size();
	}
}
