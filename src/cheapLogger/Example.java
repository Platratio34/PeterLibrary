package cheapLogger;

// Import the package
import cheapLogger.*;

public class Example {
	
	static CheapLogger logger;
	private static int t = 0;
	public static int t2 = 0;
	
	public static void main(String[] args) {
		// Creat a new instace of the CheapLogger creating a new custom LogEvent
		// The Log event will be called in a seprate thead so you do not need to wory about it holding the main thread
		logger = new CheapLogger(new LogEvent() {
			public String onLog() {
				// Create and return a string that will be logged
				// When creating the string you can use instance and static varibles from the object that the LogEvent is created in
				// Public, Private, and protected varibels can all be read
				// For instnace I am using t and t2 in creating the string
				return "Log:"+t+", "+t2;
			}
		});
		// Start the rest of your program here
		while(true) {
			t++;
			if(t<0) {
				t = 0;
				t2++;
			}
		}
	}
}
