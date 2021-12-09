package security.brickelEncoding;

import java.util.Scanner;

public class Codes {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String in = scan.nextLine();
		String key = goodString(scan);
		String b = encode(in,key);
		System.out.println(b);
		System.out.println(decode(b,key));
//		String a = encode("This, is a. message!","TESZ");
//		System.out.println(decode(a,"TESZ"));
	}
	
	public static String goodString(Scanner scan) {
		boolean bad = true;
		String out = "";
		while(bad) {
			System.out.println("give me a string with no repeated charecters");
			String test = scan.next();
			test = test.toUpperCase();
			int[] t = new int[26];
			boolean good = true;
			for(int i = 0; i < test.length(); i++) {
				t[test.charAt(i)-65]++;
				if(t[test.charAt(i)-65] > 1) {
					good = false;
				}
			}
			if(good) {
				bad = false;
				out = test;
			}
		}
		return out;
	}
	
	public static String encode(String input, String keyword) {
		char[][] arr1 = new char[][] {
			{'A','B','C','D','E'},
			{'F','G','H','I','J'},
			{'K','L','M','N','O'},
			{'P','Q','R','S','T'},
			{'U','V','W','X','Y'},
			{'Z',' ','.',',','!'}
		};
		input = input.toUpperCase();
		String out1 = "";
		for(int i = 0; i < input.length(); i++) {
			char ch = input.charAt(i);
    		for(int x = 0; x < 6; x++) {
    			for(int y = 0; y < 5; y++) {
    				if(ch == arr1[x][y]) {
    					out1 += "" + x + y;
    					x = 5;
    					y = 5;
                    }
                }
            }
		}
// 		System.out.println("out1 = " + out1);
		
		char[][] arr2 = new char[keyword.length()][];
		int l = 0;
		if(out1.length()%keyword.length() == 0) {
		    l = out1.length()/keyword.length();
		} else {
		    l = (out1.length()/keyword.length()) + 1;
		}
		for(int i = 0; i < arr2.length; i++) {
		    arr2[i] = new char[l+1];
		    arr2[i][0] = keyword.charAt(i);
		    for(int j = 1; j <= l; j++) {
		        if(((j-1)*keyword.length())+i < out1.length()) {
		            char a = out1.charAt(((j-1)*keyword.length())+i);
		          //  System.out.println(a);
		            arr2[i][j] = a;
		        } else {
		            arr2[i][j] = ' ';
		        }
		    }
		}
// 		System.out.println("arr2:");
// 		printArray(arr2);
		
		char[][] arr3 = new char[26][];
		
		for(int i = 0; i < arr2.length; i++) {
		  //  System.out.println(arr2[i][0]-65);
		    arr3[arr2[i][0]-65] = arr2[i];
		}
		
		char[][] arr4 = new char[arr2.length][];
		int it = 0;
		for(int i = 0; i < 26; i++) {
		    if(arr3[i] != null) {
		        arr4[it] = arr3[i];
		        it++;
		    }
		}
// 		System.out.println("arr4");
// 		printArray(arr4);
		
		String out = "";
		
		for(int i = 1; i < arr4[0].length; i++) {
		    for(int j = 0; j < arr4.length; j++) {
		        out += arr4[j][i];
		    }
		}
		
		return out;
	}
	
	public static String decode(String input, String keyword) {
	    
	    char[] arr5 = new char[26];
	    String key2 = "";
	    keyword = keyword.toUpperCase();
	    for(int i = 0; i < keyword.length(); i++) {
	        char ch = keyword.charAt(i);
	        arr5[ch-65] = ch;
	    }
	    for(int i = 0; i < 26; i++) {
	        if(arr5[i] != 0) {
	            key2 += arr5[i];
	        }
	    }
	    
	    
	    char[][] arr4 = new char[key2.length()][];
		int l = 0;
		if(input.length()%key2.length() == 0) {
		    l = input.length()/key2.length();
		} else {
		    l = (input.length()/key2.length()) + 1;
		}
		for(int i = 0; i < arr4.length; i++) {
		    arr4[i] = new char[l+1];
		    arr4[i][0] = key2.charAt(i);
		    for(int j = 1; j <= l; j++) {
		        if(((j-1)*key2.length())+i < input.length()) {
		            char a = input.charAt(((j-1)*key2.length())+i);
		          //  System.out.println(a);
		            arr4[i][j] = a;
		        } else {
		            arr4[i][j] = ' ';
		        }
		    }
		}
// 		System.out.println("arr4:");
// 		printArray(arr4);
	    
	    char[][] arr3 = new char[keyword.length()][];
	    
	    for(int i = 0; i < arr4.length; i++) {
	        for(int j = 0; j < keyword.length(); j++) {
	            if(arr4[i][0] == keyword.charAt(j)) {
	                arr3[j] = arr4[i];
	            }
	        }
	    }
	   // printArray(arr3);
	    
	    String out2 = "";
	    for(int i = 1; i < arr3[0].length; i++) {
	        for(int j = 0; j < arr3.length; j++) {
	            if(arr3[j][i] != ' ') {
	                out2 += arr3[j][i];
	            }
	        }
	    }
	   // System.out.println(out2);
	    
	    char[][] arr1 = new char[][] {
			{'A','B','C','D','E'},
			{'F','G','H','I','J'},
			{'K','L','M','N','O'},
			{'P','Q','R','S','T'},
			{'U','V','W','X','Y'},
			{'Z',' ','.',',','!'}
		};
	    
	    String out = "";
	    for(int i = 0; i < out2.length(); i += 2) {
	        //n-48
	        int x = out2.charAt(i) - 48;
	        int y = out2.charAt(i+1) - 48;
	        out += arr1[x][y];
	    }
	    
		return out;
    }
    
    public static void printArray(char[][] arr) {
        for(int i = 0; i < arr[0].length; i++) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[j][i]);
            }
            System.out.println();
        }
    }
	
}