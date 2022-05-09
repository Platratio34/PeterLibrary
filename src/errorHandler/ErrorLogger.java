package errorHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;

import peterGraphics.Console;

import java.awt.Color;

public class ErrorLogger {
	
	protected Console log;
	protected int nullPointers;
	protected int arrayIOBs;
	protected int stringIOBs;
	protected int stachOverflows;
	protected int SecurityExeptions;
	protected String originT;
	protected int lineT;
	protected ErrorType typeT;
	protected DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
	public static Color ErrorColor = new Color(255, 10, 10);
	public static Color WarnColor = new Color(255, 200, 0);
	
	public boolean showOnError = true;
	private boolean toFile = false;
	
	/**
	 * default constructor
	 */
	public ErrorLogger(String name) {
		log = new Console(false,false,"Error Logger:");
		log.setTitle(name);
		reset();
	}
	public ErrorLogger(String name, boolean toFile) {
		log = new Console(false,false,"Error Logger:", toFile);
		log.setTitle(name);
		reset();
	}
	
	/**
	 * gets the JFrame the error logger is using
	 * @return
	 */
	public JFrame getFrame() {
		return log.getFrame();
	}
	
	/**
	 * resets the error logger
	 */
	public void reset() {
		log.clearText();
		log.visible(false);
		nullPointers = 0;
		arrayIOBs = 0;
		stringIOBs = 0;
		stachOverflows = 0;
		SecurityExeptions = 0;
		
		originT = "";
		lineT = -1;
		typeT = ErrorType.NULL;
	}
	
	/**
	 * Logs a new error
	 * @param type : type of error
	 * @param origin : class of origin
	 * @param line : line of origin
	 */
	public void logError(ErrorType type, String origin, int line) {
		
		LocalDateTime now = LocalDateTime.now();
		
		if(typeT == type && originT == origin && lineT == line) {
			originT = origin;
			lineT = line;
			typeT = type;
			type = ErrorType.NULL;
		} else {
			originT = origin;
			lineT = line;
			typeT = type;
		}
		
		if(type == ErrorType.NullPointer) {
			nullPointers++;
			//System.out.println(nullPointers);
			log.appendln(dtf.format(now) + ": Error: Null Pointer; At: " + origin + "," + line, ErrorColor);
		} else if(type == ErrorType.ArrayIndexOutOfBounds) {
			arrayIOBs++;
			log.appendln(dtf.format(now) + ": Error: Array Index Out of Bounds; At: " + origin + "," + line, ErrorColor);
		} else if(type == ErrorType.StringIndexOutOfBounds) {
			stringIOBs++;
			log.appendln(dtf.format(now) + ": Error: String Index Out of Bounds; At: " + origin + "," + line, ErrorColor);
		} else if(type == ErrorType.StackOverflow) {
			stachOverflows++;
			log.appendln(dtf.format(now) + ": Error: Stack Overflow; At: " + origin + "," + line, ErrorColor);
		} else if(type == ErrorType.Security) {
			nullPointers++;
			log.appendln(dtf.format(now) + ": Error: Security Exeption; At: " + origin + "," + line, ErrorColor);
		}
		if(showOnError) log.visible(true);
	}
	/**
	 * Logs a new error
	 * @param type : type of error
	 * @param origin : class of origin
	 * @param line : line of origin
	 * @param info : extra information
	 */
	public void logError(ErrorType type, String origin, int line, String info) {
		
		LocalDateTime now = LocalDateTime.now();
		
		if(typeT == type && originT == origin && lineT == line) {
			originT = origin;
			lineT = line;
			typeT = type;
			type = ErrorType.NULL;
		} else {
			originT = origin;
			lineT = line;
			typeT = type;
		}
		
		if(type == ErrorType.NullPointer) {
			nullPointers++;
			//System.out.println(nullPointers);
			log.appendln(dtf.format(now) + ": Error: Null Pointer; At: " + origin + "," + line + "; " + info, ErrorColor);
		} else if(type == ErrorType.ArrayIndexOutOfBounds) {
			arrayIOBs++;
			log.appendln(dtf.format(now) + ": Error: Array Index Out of Bounds; At: " + origin + "," + line + "; " + info, ErrorColor);
		} else if(type == ErrorType.StringIndexOutOfBounds) {
			stringIOBs++;
			log.appendln(dtf.format(now) + ": Error: String Index Out of Bounds; At: " + origin + "," + line + "; " + info, ErrorColor);
		} else if(type == ErrorType.StackOverflow) {
			stachOverflows++;
			log.appendln(dtf.format(now) + ": Error: Stack Overflow; At: " + origin + "," + line + "; " + info, ErrorColor);
		} else if(type == ErrorType.Security) {
			SecurityExeptions++;
			log.appendln(dtf.format(now) + ": Error: Security Exeption; At: " + origin + "," + line + "; " + info, ErrorColor);
		}
		if(showOnError) log.visible(true);
	}
	/**
	 * Logs a new error
	 * @param e : error
	 * @param origin : class of origin
	 * @param line : line of origin
	 */
	public void logError(Exception e) {
		
		LocalDateTime now = LocalDateTime.now();
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		log.appendln(dtf.format(now) + ": Error: " + sw, ErrorColor);
		if(showOnError) log.visible(true);
	}
	/**
	 * Logs a new error
	 * @param origin : class of origin
	 * @param line : line of origin
	 * @param info : extra information
	 */
	public void logError(String error, String origin, int line, String info) {
		
		LocalDateTime now = LocalDateTime.now();
		log.appendln(dtf.format(now) + ": Error: " + error + "; At: " + origin + "," + line + "; " + info, ErrorColor);
		if(showOnError) log.visible(true);
	}
	
	public void logInfo(String info) {
		LocalDateTime now = LocalDateTime.now();
		log.appendln(dtf.format(now) + ": INFO: " + info);
		if(showOnError) log.visible(true);
	}
	
	public void logWarn(String info) {
		LocalDateTime now = LocalDateTime.now();
		log.appendln(dtf.format(now) + ": WARN: " + info, WarnColor);
		if(showOnError) log.visible(true);
	}
}
