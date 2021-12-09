package peterLibrary;

public class PeterLogic {
	public static String[] VParse(String input, char cParse) {
		String[] output;
		int parses = -1;
		int par = 0;
		for(int i=0;i<input.length();i++) {
			if(input.charAt(i)==cParse) {
				par++;
			}
		}
		output = new String[par+1];
		for(int i=0;i<input.length();i++) {
			if(input.charAt(i)==cParse) {
				parses++;
				output[parses] = input.substring(0, i);
				input = input.substring(i+1);
				i=0;
			}
		}
		output[parses+1] = input.substring(0, input.length());
		return output;
	}
	
	public static boolean inRange(int number,int top, int bottem) {
		if(number<top && number>bottem) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int clampI(int i, int top,int  bottem) {
		if(i>top) {
			i=top;
		} else if (i<bottem) {
			i=bottem;
		}
		return i;
	}
	
	public static int clampE(int i, int top,int  bottem) {
		if(i>=top) {
			i=top-1;
		} else if (i<=bottem) {
			i=bottem+1;
		}
		return i;
	}
}
