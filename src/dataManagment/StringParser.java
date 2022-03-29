package dataManagment;

import java.util.ArrayList;

/**
 * StringParser is a library of functions for parsing Json type string
 * @author peter
 *
 */
public class StringParser {
    
    /**
     *  <summary><c>Parse For String</c> pareses a Json type string for a string</summary>
     * @param str The string to parse
     * @return A StringParse object of type <c>String</c>, use <c>".data"</c> for the string
     */
    public static StringParse<String> parseForString(String str) {
        String o = "";
        int i = 0;
        int sb = 0;
        int cb = 0;
        boolean q = false;
        boolean q2 = false;
        for(i = 0; i < str.length(); i++) {
            if(!q) {
                if(str.charAt(i) == '}') {
                    cb--;
                } else if(str.charAt(i) == '{') {
                    cb++;
                } else if(str.charAt(i) == ']') {
                    sb--;
                } else if(str.charAt(i) == '[') {
                    sb++;
                }
            }
            if(str.charAt(i) == '"') {
                if(!(i > 0 && str.charAt(i-1) == '\\')) {
                    q = !q;
                    if(cb == 0 && sb == 0) {
                        q2 = true;
                    }
                }
            }
            if(!q2)
                if(str.charAt(i) == ',' && cb == 0 && sb == 0 && !q) {
                    return new StringParse<String>(o, i);
                } else {
                    if(!q) {
                        if(!(str.charAt(i) == ' ' || str.charAt(i) == '\t' || str.charAt(i) == '\n' || str.charAt(i) == '\r') ) {
                            o += str.charAt(i);
                        }
                    } else {
                        o += str.charAt(i);
                    }
                }
            q2 = false;
        }
        return new StringParse<String>(o, i);
    }

    /**
     *  <summary><c>Parse For KV</c> pareses a Json type key value par (ex. "key":"value" )</summary>
     * @param str The string to parse
     * @return A <c>StringParse</c> object of type <c>String[]</c>, use <c>".data"</c> for the string array. Index 0 is the key and index 1 is the value
     */
    public static StringParse<String[]> parseForKV(String str) {
        String[] o = new String[] {"",""};
        boolean k = true;
        boolean v = false;
        int i = 0;
        int sb = 0;
        int cb = 0;
        boolean q = false;
        boolean q2 = false;
        for(i = 0; i < str.length(); i++) {
            if(!q) {
                if(str.charAt(i) == '}') {
                    cb--;
                } else if(str.charAt(i) == '{') {
                    cb++;
                } else if(str.charAt(i) == ']') {
                    sb--;
                } else if(str.charAt(i) == '[') {
                    sb++;
                }
            }
            if(str.charAt(i) == '"') {
                if(!(i > 0 && str.charAt(i-1) == '\\')) {
                    q = !q;
                    if(cb == 0 && sb == 0) {
                        q2 = true;
                    }
                }
            }
            if(!q2) {
                if((str.charAt(i) == '=' || str.charAt(i) == ':') && k && !q) {
                    k = false;
                    v = true;
                } else if(str.charAt(i) == ',' && cb == 0 && sb == 0 && !q) {
//                    Debug.Log(o[0] + ":" + o[1]);
                    return new StringParse<String[]>(o, i);
                } else if(k && !(str.charAt(i) == '{' && !q) ) {
                    if(!q && (cb == 0 && sb == 0)) {
                        if(!(str.charAt(i) == ' ' || str.charAt(i) == '\t' || str.charAt(i) == '\n' || str.charAt(i) == '\r') ) {
                            o[0] += str.charAt(i);
                        }
                    } else {
                        o[0] += str.charAt(i);
                    }
                } else if(v) {
                    if(!q && (cb == 0 && sb == 0)) {
                        if(!(str.charAt(i) == ' ' || str.charAt(i) == '\t' || str.charAt(i) == '\n' || str.charAt(i) == '\r') ) {
                            o[1] += str.charAt(i);
                        }
                    } else {
                        o[1] += str.charAt(i);
                    }
                }
            }
            q2 = false;
        }
        return new StringParse<String[]>(o, i);
    }

    /**
     *  <summary><c>Parse For Array</c> pareses a Json type key array (ex. ["value","value",...] )</summary>
     * @param str The string to parse
     * @return A <c>StringParse</c> object of type <c>JsonObject</c>, use <c>".data"</c> for the Json Object, and <c>JsonObj.array</c> for the array
     */
    public static StringParse<JsonObj> parseForArray(String str) {
        ArrayList<JsonObj> o = new ArrayList<JsonObj>();
        String s = "";
        int i = 0;
        int sb = 0;
        int cb = 0;
        boolean q = false;
        boolean q2 = false;
        for(i = 0; i < str.length(); i++) {
            if(!q) {
                if(str.charAt(i) == '}') {
                    cb--;
                } else if(str.charAt(i) == '{') {
                    cb++;
                } if(str.charAt(i) == ']') {
                    sb--;
                    if(sb <= 0 && cb == 0) {
                        o.add(new JsonObj(s, true));
                        s = "";
                        return new StringParse<JsonObj>(new JsonObj(o), i);
                    }
                } else if(str.charAt(i) == '[') {
                    sb++;
                }
            }
            if(str.charAt(i) == '"') {
                if(!(i > 0 && str.charAt(i-1) == '\\')) {
                    q = !q;
                    if(cb == 0 && sb == 1) {
                        q2 = true;
                    }
                }
            }
            if(!q2)
                if(str.charAt(i) == ',' && sb == 1 && cb == 0 && !q) {
                    o.add(new JsonObj(s, true));
                    s = "";
                } else if(i > 0) {
                    if(!q && (cb == 0 && sb == 1)) {
                        if(!(str.charAt(i) == ' ' || str.charAt(i) == '\t' || str.charAt(i) == '\n' || str.charAt(i) == '\r') ) {
                            s += str.charAt(i);
                        }
                    } else {
                        s += str.charAt(i);
                    }
                }
            q2 = false;
        }
        return new StringParse<JsonObj>(new JsonObj(o), i);
    }

    /**
     * <summary><c>Parse Object</c> pareses a Json type key Object (ex. {"key":"value","key":"value",...} )</summary>
     * @param str The string to parse
     * @return A <c>StringParse</c> object of type <c>JsonObj</c>, use <c>".data"</c> for the Json Object, and <c>JsonObj.objs</c> to acces the KV pairs
     */
    public static StringParse<JsonObj> parseObject(String str) {
        JsonObj obj = new JsonObj();
        String s = "";
        int i = 0;
        int sb = 0;
        int cb = 0;
        boolean q = false;
        // boolean q2 = false;
        for(i = 0; i < str.length(); i++) {
            if(!q) {
                if(str.charAt(i) == '}') {
                    cb--;
                    if(cb <= 0 && sb <= 0) {
                        obj.add(s);
                        s = "";
                        return new StringParse<JsonObj>(obj, i);
                    }
                } else if(str.charAt(i) == '{') {
                    cb++;
                } if(str.charAt(i) == ']') {
                    sb--;
                } else if(str.charAt(i) == '[') {
                    sb++;
                }
            }
            if(str.charAt(i) == '"') {
                if(!(i > 0 && str.charAt(i-1) == '\\')) {
                    q = !q;
                    // if(cb == 1 && sb == 0) {
                    //     q2 = true;
                    // }
                }
            }
            if(str.charAt(i) == ',' && cb == 1 && sb == 0 && !q) {
                obj.add(s);
                s = "";
            } else if(i > 0) {
                if(!q && (cb == 1 && sb == 0)) {
                    if(!(str.charAt(i) == ' ' || str.charAt(i) == '\t' || str.charAt(i) == '\n' || str.charAt(i) == '\r') ) {
                        s += str.charAt(i);
                    }
                } else {
                    s += str.charAt(i);
                }
            }
            // q2 = false;
        }
        return new StringParse<JsonObj>(obj, i);
    }
}
