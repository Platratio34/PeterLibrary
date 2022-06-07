package commandLine;

import java.util.HashMap;

public class CmdFormater {
	
	private HashMap<String, CmdOption> options;
	
	/**
	 * Creates a new command formated for interpreting command line arguments
	 */
	public CmdFormater() {
		options = new HashMap<String, CmdOption>();
	}
	
	/**
	 * Registers an option
	 * @param option : the option to register
	 */
	public void setOption(CmdOption option) {
		options.put(option.name, option);
	}
	/**
	 * Resisters an option<br>
	 * Non-argument version of an option
	 * @param name : the name of the option
	 */
	public void setOption(String name) {
		options.put(name, new CmdOption(name, 0));
	}
	
	/**
	 * Interprets command line arguments from String[]
	 * @param args : the arguments from the command line, space seperated excluding in quotes
	 * @return An AdvArguments object of the interpreted data from the arguments
	 */
	public AdvArguments interpret(String[] args) {
//		ArrayList<String> argList = new ArrayList<String>();
		AdvArguments advArgs = new AdvArguments();
		for(int i = 0; i < args.length; i++) {
			if(args[i].indexOf("-") == 0) {
				String optN = args[i].substring(1);
				if(options.containsKey(optN)) {
					CmdOption opt = options.get(optN);
					if(opt.numArgs > 0) {
						int nA = opt.numArgs;
						if(nA <= 0) {
							advArgs.addOption(optN, new String[0]);
							continue;
						}
						if(i+nA > args.length) {
							nA = (args.length-i-1);
							System.err.println("Not enought arguments for option: \""+args[i]+"\", expecting "+opt.numArgs+" arguments, only "+nA+" remaning");
						}
						String[] argArr = new String[opt.numArgs];
						for(int j = 0; j < nA; j++) {
							argArr[j] = args[i+j+1];
						}
						i += nA;
						advArgs.addOption(optN, argArr);
					}
				} else {
					System.err.println("Invalid option: \""+args[i]+"\"");
				}
			} else {
				advArgs.addArg(args[i]);
			}
		}
		return advArgs;
	}
	
}
