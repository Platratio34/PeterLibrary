package converters;

public class numberConverters {
	public static String toHex(int decimal) {
		String out = "";
		if(decimal >= 16) {
			int d = decimal/16;
			out += toHex(d);
			decimal -= d  * 16;
		}
		if(decimal < 10) {
			out += decimal + "";
			decimal = 0;
		} else if(decimal < 16) {
			if(decimal == 10) {
				out += "A";
			} else if(decimal == 11) {
				out += "B";
			} else if(decimal == 12) {
				out += "C";
			} else if(decimal == 13) {
				out += "D";
			} else if(decimal == 14) {
				out += "E";
			} else {
				out += "F";
			}
				decimal = 0;
		} 
		return out;
	}
	public static String toHex(String binary) {
		return toHex(toDecimalB(binary));
	}
	
	public static String toBinary(int decimal) {
		String out = "";
		if(decimal > 1) {
			int d = decimal / 2;
			out += toBinary(d);
			decimal -= d * 2;
		}
		if(decimal < 2) {
			out += decimal;
		}
		return out;
	}
	public static String toBinary(String hex) {
		return toBinary(toDecimalH(hex));
	}
	
	public static int toDecimalH(String hex) {
		hex = hex.toUpperCase();
		int out = 0;
		for(int i = 0; i < hex.length(); i++) {
			char c = hex.charAt(hex.length()-i-1);
			if(c == 'A') {
				out += 10 * Math.pow(16,i);
			} else if(c == 'B') {
				out += 11 * Math.pow(16,i);
			} else if(c == 'C') {
				out += 12 * Math.pow(16,i);
			} else if(c == 'D') {
				out += 13 * Math.pow(16,i);
			} else if(c == 'E') {
				out += 14 * Math.pow(16,i);
			} else if(c == 'F') {
				out += 15 * Math.pow(16,i);
			} else {
				out += Character.getNumericValue(c) * Math.pow(16,i);
			}
//			System.out.println(c);
//			System.out.println(out);
		}
		return out;
	}
	public static int toDecimalB(String binary) {
		int out = 0;
		for(int i = 0; i < binary.length(); i++) {
			double a = Character.getNumericValue(binary.charAt(binary.length()-i-1));
			out += a * Math.pow(2,(double)i);
		}
		return out;
	}
}
