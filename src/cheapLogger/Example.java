package cheapLogger;

//import cheapLogger.CheapLogger.LogEvent;

public class Example {
		
	static CheapLogger logger;
	static int t = 0;
	
	public static void main(String[] args) {
		logger = new CheapLogger(new LogEvent() {
			public String onLog() {
				t++;
				return "Log:"+t;
			}
		});
	}
}
