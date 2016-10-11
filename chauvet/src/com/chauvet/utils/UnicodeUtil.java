package com.chauvet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 * Unicode
 * @author wxw
 *
 */
public class UnicodeUtil {
	/***
	 * Unicode字符转换成字符串
	 * @param str
	 * 			Unicode字符
	 * @return
	 * 			String
	 * 
	 * @author WXW
	 */
	public static String Unicode2String(String str){
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(str);
		char ch;
		while(matcher.find()){
			ch = (char)Integer.parseInt(matcher.group(2),16);
			str = str.replace(matcher.group(1), ch+"");
		}
		return str;
	}
	
	public static void main(String[] args) {
		
		String str = "activityContent\u6d88\u8d391-20\u625310\u6298,\u4f4e\u4e8e1\u6216\u9ad8\u4e8e20\u90e8\u5206\u4e0d\u4eab\u53d7\u6298\u6263\u4f18\u60e0!";
    	System.out.println(UnicodeUtil.Unicode2String(str));
    	
	}
}
