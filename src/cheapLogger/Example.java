package cheapLogger;

// Import the package
import cheapLogger.*;

@SuppressWarnings("unused") // just to make it stop yelling at me
public class Example {
	
	static CheapLogger logger;
	private static int t = 0;
	public static int t2 = 0;
	
	public static void main(String[] args) {
		// Create a new instance of the CheapLogger creating a new custom LogEvent
		// The Log event will be called in a separate thread so you do not need to worry about it holding the main thread
		logger = new CheapLogger(new LogEvent() {
			public String onLog() {
				// Create and return a string that will be logged
				// When creating the string you can use instance and static variables from the object that the LogEvent is created in
				// Public, Private, and protected variables can all be read
				// For instance I am using t and t2 in creating the string
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
