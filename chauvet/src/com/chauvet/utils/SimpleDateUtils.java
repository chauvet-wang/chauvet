package com.chauvet.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.StringUtils;


/***
 * 时间
 * @author WXW
 *
 */
public class SimpleDateUtils {

	private static SimpleDateFormat sdf = null;
	private static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfShort = new SimpleDateFormat("yyyy-MM-dd");
	
	/***
	 * 指定时间格式
	 * @param dateType<br />
	 * 		要获取的时间格式，该格式直接传入字符串即可。<br />
	 * 		例如：<font color='red'>getDateByType("G yyyy-MM-dd HH:mm:ss E")</font>得到“公元 2016-09-14 16:56:58 星期三”<br />
	 * 		G(纪元),y(年),M(月),d(日),h(时-12小时制),H(时-24小时制),m(分),s(秒)<br />
	 * 		S(毫秒),E(周几),D(本年中的第几天),F(本月当前周的周几),w(本年的的几周)<br />
	 *		W(本月的的几周),a(A.M/P.M标记),k(当天的第几个小时-24小时制),z(时区)<br />
	 * @return
	 * 		得到的日期字符串
	 */
	public static String getDateByType(String dateType){
		if(StringUtils.isNotBlank(dateType)){
			try {
				sdf = new SimpleDateFormat(dateType); 
				return sdf.format(new Date());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
	
	/***
	 * 获取日期<br />
	 * 		
	 * @return
	 * 		yyyy-MM-dd HH:mm:ss
	 */
	public static String getLongDate(){
		return sdfLong.format(new Date());
	}
	
	/***
	 * 获取日期<br />
	 * 		
	 * @return
	 * 		yyyy-MM-dd
	 */
	public static String getShortDate(){
		return sdfShort.format(new Date());
	}
	
	public static void main(String[] args) {
//		System.out.println(getDateByType("G yyyy-MM-dd HH:mm:ss E"));
//		System.out.println(getDateByType("mm"));
//		System.out.println(getLongDate());
//		System.out.println(getShortDate());
		
		
	}
}
