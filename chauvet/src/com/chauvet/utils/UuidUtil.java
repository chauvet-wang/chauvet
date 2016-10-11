package com.chauvet.utils;

import java.util.UUID;

/***
 * UUID
 * @author WXW
 *
 */
public class UuidUtil {
	
	/***
	 * 获取制定长度的UUID
	 * @param length
	 *  			 长度
	 * @param isNumber
	 * 				是否纯数字
	 * @return
	 */
	public static String getUUID(int length, boolean isNumber){
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");//replaceAll()之后返回的是一个由十六进制形式组成的且长度为32的字符串
		if(uuid.length() > length){
		    uuid = uuid.substring(0, length);
		}else if(uuid.length() < length){
		    for(int i=0; i<length-uuid.length(); i++){
		        uuid = uuid + Math.round(Math.random()*9);
		    }
		}
		if(isNumber){
		    return uuid.replaceAll("a", "1").replaceAll("b", "2").replaceAll("c", "3").replaceAll("d", "4").replaceAll("e", "5").replaceAll("f", "6");
		}else{
		    return uuid;
		}
	}
	
	public static void main(String[] args) {
		
		System.out.println(getUUID(32, true));
		System.out.println(getUUID(64, false));
	}

}
