package converters;

import peterGraphics.Console;

public class Tester {
	public static void main(String[] args) {
		Console con = new Console();
		boolean cont = true;
		while(cont) {
			con.appendln("Starting format");
			boolean legal = false;
			String type = "";
			while(!legal) {
				String in = con.scan();
				if(in.equals("Decimal")) {
					legal = true;
					type = "D";
				} else if(in.equals("Binary")) {
					legal = true;
					type = "B";
				} else if(in.equals("Hex")) {
					legal = true;
					type = "H";
				}
				if(!legal) con.appendln("invalid format");
			}
			legal = false;
			con.appendln("Convert to");
			while(!legal) {
				String in = con.scan();
				if(in.equals("Decimal")) {
					legal = true;
					type += "D";
				} else if(in.equals("Binary")) {
					legal = true;
					type += "B";
				} else if(in.equals("Hex")) {
					legal = true;
					type += "H";
				}
				if(!legal) con.appendln("invalid format");
			}
			
			if(type.equals("DB")) {
				con.appendln("Decimal value");
				int a =  Integer.parseInt(con.scan());
				con.appendln("Binary " + numberConverters.toBinary(a));
			} else if(type.equals("DH")) {
				con.appendln("Decimal value");
				int a =  Integer.parseInt(con.scan());
				con.appendln("Hex " + numberConverters.toHex(a));
			} else if(type.equals("BD")) {
				con.appendln("Binary value");
				String a =  con.scan();
				con.appendln("Decimal " + numberConverters.toDecimalB(a));
			} else if(type.equals("BH")) {
				con.appendln("Binary value");
				String a =  con.scan();
				con.appendln("Hex " + numberConverters.toHex(a));
			} else if(type.equals("HD")) {
				con.appendln("Hex value");
				String a =  con.scan();
				con.appendln("Decimal " + numberConverters.toDecimalH(a));
			} else if(type.equals("HB")) {
				con.appendln("Hex value");
				String a =  con.scan();
				con.appendln("Binary " + numberConverters.toBinary(a));
			}
			con.appendln("");
		}
	}
}
