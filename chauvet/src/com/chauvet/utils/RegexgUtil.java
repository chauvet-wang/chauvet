package com.chauvet.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则匹配工具类
 * @author WXW
 *
 */
public class RegexgUtil {
	//true或者false
	public final static String TRUEORFALSE = "^true|false$";
	//性别
	public final static String SEX = "^M|F|S$";
	//订单付款类型
	public final static String ORDER_PAY_TYPE = "^1|2$";
	//会员验证码类型
	public final static String MEM_VERY_CODE_TYPE = "^1|2$";
	//日期
	public final static String DATE = "^\\d{4}-(0{0,1}[1-9]|1[1-2])-(0{0,1}[1-9]|[1-2]\\d|3[0-1])$";
	//日期时间
	public final static String DATE_TIME = "^\\d{4}-(0[1-9]|1[1-2])-(0[1-9]|[1-2]\\d|3[0-1]) ([0-1]\\d|2[0-4]\\d):[0-6]\\d:[0-6]\\d$";
	//手机
	public final static String MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
	//电子邮件
	public final static String EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}{1}";
	//电话
	public final static String PHONE = "^((0[1,2]{1}\\d{1}-?\\d{8})|(0[3-9]{1}\\d{2}-?\\d{7,8}))$";
	//邮编
	public final static String ZIPCODE = "^[0-9]{6}$";
	//护照
	public final static String PASSPORT =  "^(((14|15)\\d{7})|(G\\d{8})|(P.\\d{7})|(S.\\d{7})|(S\\d{8})|D*)$";
	//位置(经纬度 如：38.76623,116.4321)
	public final static String LOCATION = "^(0.[0-9]*[1-9]{1,}|[1-9]{1}[0-9]*|[1-9]{1}[0-9]*.[0-9]+),(0.[0-9]*[1-9]{1,}|[1-9]{1}[0-9]*|[1-9]{1}[0-9]*.[0-9]+)$";
	//qq
	public final static String QQ =  "^[1-9]\\d{4,11}$";
	//msn
	public final static String MSN = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
	//匹配英文和数字
	public final static String EN_NUM = "^[A-Za-z0-9]+$";
	//匹配(英文、数字和"_")
	public final static String EN_NUM_UNDERLINE = "^[A-Za-z0-9_]+$";
	//匹配(英文、数字、汉字、和"_")
	public final static String EN_NUM_CN_UNDERLINE = "^[A-Za-z0-9\u4e00-\u9fa5_]+$";
	//匹配双字节字符(包括标点符号)
	public final static String DOUBLEBYTE = "^[^\\x00-\\xff]+$";
	//正整数(大于0)
	public final static String POSITIVE_INTEGER = "^[1-9]{1}[0-9]*$";
	//正数(大于等于0的正数或者浮点数)
	public final static String POSITIVE_NUMBER = "^0|0.[0-9]+|[1-9]{1}[0-9]*|[1-9]{1}[0-9]*.[0-9]+$";
	//大于0的数(整数或浮点)
	public final static String GREATE_THAN_ZERO_NUMBER = "^0.[0-9]*[1-9]{1,}+|[1-9]{1}[0-9]*|[1-9]{1}[0-9]*.[0-9]+$";
	//设备类型
	public final static String TERMINAL_TYPE = "^1|2|3$";
	//商户状态
	public final static String SHOP_STATUS = "^1|2$";
	//资质类型
	public final static String QUALIFICATION_TYPE = "^1|2$";
	//微信
	public final static String WX = "^[(A-Za-z0-9)|_]+$";
	
	public static void main(String[] args) {
//		"^\\d{4}-(0[1-9]|1[1-2])-(0[1-9]|[1-2]\\d|3[0-1]) ([0-1]\\d|2[0-4]\\d):[0-6]\\d:[0-6]\\d$";
		System.out.println(isMatch("18201608268", MOBILE));
	}
	
	/**
	 * 正则匹配方法
	 * @param str
	 * @param reg
	 * @return
	 */
	public static boolean isMatch(Object str, String reg){
		boolean flag = false;
		try {
			Pattern p = Pattern.compile(reg);
			Matcher m = p.matcher(str.toString());
			flag = m.matches();
		} catch ( Exception e ) {
			flag = false;
		}
		return flag;
	}
}