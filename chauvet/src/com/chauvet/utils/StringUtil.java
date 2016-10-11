package com.chauvet.utils;

import org.apache.commons.lang.StringUtils;

/***
 * 字符串处理类
 * @author WXW
 *
 */
public class StringUtil {
	
	/**
     * 获取一个字符串的缩略信息
     * @return String 
     * 				返回的字符串（“这是一个***字符串”）
     */
    public static String getStringSimple(String str){
    	if(StringUtils.isBlank(str)){
    		return str;
    	}
        return str.substring(0,4) + "***" + str.substring(str.length()-4);
    }
    
    /** 
     *将半角的符号转换成全角符号.(即英文字符转中文字符) 
     *  
     * @param str 
     *            源字符串 
     * @return String 
     */  
    public static String changeToFull(String str) {  
        String source = "1234567890!@#$%^&*()abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_=+\\|[];:'\",<.>/?";  
        String[] decode = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０",  
                "！", "＠", "＃", "＄", "％", "︿", "＆", "＊", "（", "）", "ａ", "ｂ",  
                "ｃ", "ｄ", "ｅ", "ｆ", "ｇ", "ｈ", "ｉ", "ｊ", "ｋ", "ｌ", "ｍ", "ｎ",  
                "ｏ", "ｐ", "ｑ", "ｒ", "ｓ", "ｔ", "ｕ", "ｖ", "ｗ", "ｘ", "ｙ", "ｚ",  
                "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ", "Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ",  
                "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ", "Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ",  
                "Ｙ", "Ｚ", "－", "＿", "＝", "＋", "＼", "｜", "【", "】", "；", "：",  
                "'", "\"", "，", "〈", "。", "〉", "／", "？" };  
        String result = "";  
        for (int i = 0; i < str.length(); i++) {  
            int pos = source.indexOf(str.charAt(i));  
            if (pos != -1) {  
                result += decode[pos];  
            } else {  
                result += str.charAt(i);  
            }  
        }  
        return result;  
    }  
    
    
    /***
     * 反转字符串
     * @param str
     * @return
     */
    public static String reverse(String str) {  
        if (StringUtils.isBlank(str)) {  
            return null;  
        }  
        return new StringBuilder(str).reverse().toString();  
    }  
   
    public static void main(String[] args) {
		
    	System.out.println(StringUtil.reverse("asfwew221scx11"));
	}
}
