package Util;

public class HexStr {
	
	public static void main(String[] args) {
		//System.out.println(toUnicode("中华人民共和国"));
		/*String ss = "\u4e2d\u534e\u4eba\u6c11\u5171\u548c\u56fd";
		 System.out.println(ss);*/
		 String ss = "4e2d534e4eba6c115171548c56fd";
		
		 String s = hexString2String(ss);
		 System.out.println(s);
	}
	
	//\u4e2d\u534e\u4eba\u6c11\u5171\u548c\u56fd
	
	public static String toChinese(String s ){
		StringBuffer s1=new StringBuffer(s);
		int index;
		for(index=0;index<s1.length();index+=6){
			
			s1.insert(index, "\\u");
		}
		return s1.toString();
	}
	 
	 public static String hexString2String(String src) {  
	        String temp = "";  
	        for (int i = 0; i < src.length() / 2; i++) {  
	            temp = temp  
	                    + (char) Integer.valueOf(src.substring(i * 2, i * 2 + 2),  
	                            16).byteValue();  
	        }  
	        return temp;  
	    } 
	
	
	public static String toUnicode(String s)
	 
	{
	 
	 String as[] = new String[s.length()];
	 
	String s1 = "";
	 
	for (int i = 0; i < s.length(); i++)
	 {
		as[i] = Integer.toHexString(s.charAt(i) & 0xffff);
		 s1 = s1 + as[i];
	 }
	 return s1/*.toUpperCase()*/;
	 
	}

}
