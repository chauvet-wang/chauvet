package com.chauvet.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;

/***
 * 时间/日期 
 * @author WXW
 *
 */
public class DateUtil {
	
	private static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat sdfshort = new SimpleDateFormat("yyyy-MM-dd"); // 或者可以直接用 now.toLocaleString();
	private static SimpleDateFormat sdftime = new SimpleDateFormat("HH:mm:ss");
	
	/*private static SimpleDateFormat second = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
    private static SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat detailDay = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat fileName = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private static SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");*/
    
    private static String[] s = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    

    /**
     * 获取  当前日期  的前一天日期yyyy-MM-dd
     * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @see calendar.set(Calendar.YEAR, 2013);
     * @see calendar.set(Calendar.MONTH, 0);
     * @see calendar.set(Calendar.DATE, 1);
     * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyy-MM-dd
     */
    public static String getYesterday(){
    	return getBeforeDay(sdfLong.format(new Date()), 1);
    }
    
    /***
     * 获取  指定日期  的前一天
     * @param dateStr
     * 			指定的日期 格式为yyyy-mm-dd HH:mm:ss
     * @return
     */
    public static String getYesterday(String dateStr){
    	return getBeforeDay(dateStr, 1);
    }
    
    /***
     * 获取指定日期的day天前
     * @param dateStr
     * 			当前日期
     * @param day
     * 			要减去的日期
     * @return
     */
    public static String getBeforeDay(String dateStr,int day){
    	try {
    		if(StringUtils.isBlank(dateStr)){
    			return "";
    		}
    		Calendar calendar = Calendar.getInstance();
    		Date date = null;
    		if(dateStr.length() == 19){
    			date = sdfLong.parse(dateStr);
    		}
    		if(dateStr.length() == 10){
    			date = sdfshort.parse(dateStr);
    		}
    		if(null == date){
    			return "";
    		}
    		calendar.setTime(date);
    		calendar.add(Calendar.DATE, -day);
    		return sdfLong.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }
    
    /**
     * 获取  明天
     * @see 经测试，针对闰年02月份或跨年等情况，该代码仍有效。测试代码如下
     * @see calendar.set(Calendar.YEAR, 2013);
     * @see calendar.set(Calendar.MONTH, 0);
     * @see calendar.set(Calendar.DATE, 1);
     * @see 测试时，将其放到<code>calendar.add(Calendar.DATE, -1);</code>前面即可
     * @return 返回的日期格式为yyyy-MM-dd
     */
    public static String getTomorrow(){
        return getNextDay(sdfLong.format(new Date()), 1);
    }
    
    /***
     * 获取  指定日期  的明天
     * @param dateStr
     * 			指定的日期 格式为yyyy-mm-dd HH:mm:ss
     * @return
     */
    public static String getTomorrow(String dateStr){
    	return getNextDay(dateStr, 1);
    }
    
    /***
     * 获取指定日期的day天后
     * @param dateStr
     * 				指定的日期
     * @param day
     * 			要加上的天数
     * @return
     */
    public static String getNextDay(String dateStr,int day){
    	try {
    		if(StringUtils.isBlank(dateStr)){
    			return "";
    		}
    		Calendar calendar = Calendar.getInstance();
    		Date date = null;
    		if(dateStr.length() == 19){
    			date = sdfLong.parse(dateStr);
    		}
    		if(dateStr.length() == 10){
    			date = sdfshort.parse(dateStr);
    		}
    		if(null == date){
    			return "";
    		}
    		calendar.setTime(date);
    		calendar.add(Calendar.DATE, day);
    		return sdfLong.format(calendar.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
    }
    
    /**
     * 获取当前的日期yyyy-MM-dd
     */
    public static String getCurrentDate(){
        return sdfshort.format(new Date());
    }
    
    
    /**
     * 获取当前的时间yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime(){
        return sdfLong.format(new Date());
    }
    
    /**
     * 获取当前的时间HH:mm:ss
     */
    public static String getCurrentTime(){
        return sdftime.format(new Date());
    }
    
    /***
     * 获取当前毫秒数
     * @return
     */
    public static long getMillisecond(){
    	return System.currentTimeMillis();
    	
    }
    
    
    /**
     * 计算两个日期之间相差的月数
     * @param date1
     * @param date2
     * @return
     */
     public static int getMonths(Date date1, Date date2) {
        int iMonth = 0;
         int flag = 0;
         try {
             Calendar objCalendarDate1 = Calendar.getInstance();
             objCalendarDate1.setTime(date1);
             Calendar objCalendarDate2 = Calendar.getInstance();
             objCalendarDate2.setTime(date2);
             if (objCalendarDate2.equals(objCalendarDate1)){
                 return iMonth; // 0
             }
             if (objCalendarDate1.after(objCalendarDate2)) {
                 Calendar temp = objCalendarDate1;
                 objCalendarDate1 = objCalendarDate2;
                 objCalendarDate2 = temp;
             }
             if (objCalendarDate2.get(Calendar.DAY_OF_MONTH) < objCalendarDate1.get(Calendar.DAY_OF_MONTH)){
                 flag = 1;
             }
             
             if (objCalendarDate2.get(Calendar.YEAR) > objCalendarDate1.get(Calendar.YEAR)){
                 return ((objCalendarDate2.get(Calendar.YEAR) - objCalendarDate1.get(Calendar.YEAR))* 12 + objCalendarDate2.get(Calendar.MONTH) - flag) - objCalendarDate1.get(Calendar.MONTH);
             }
             return iMonth = objCalendarDate2.get(Calendar.MONTH) - objCalendarDate1.get(Calendar.MONTH) - flag;
         } catch (Exception e) {
             e.printStackTrace();
         }
         return iMonth;
     }
     
     
     /***
      * 获取当前日期 是周几  
      * 汉字形式
      * @return
      */
     public static String chineseWeek() {
         Calendar calendar = GregorianCalendar.getInstance();
         calendar.setFirstDayOfWeek(Calendar.SUNDAY);
         return s[calendar.get(Calendar.DAY_OF_WEEK) - 1];
     }
     
     
     /***
      * 获取  指定日期  日期 是周几 
      * @param dateStr
      * 			指定的日期 格式为yyyy-mm-dd HH:mm:ss
      * @return   汉字形式
      */
     public static String chineseWeek(String dateStr){
     	try {
     		if(StringUtils.isBlank(dateStr)){
     			return "";
     		}
     		Calendar calendar = Calendar.getInstance();
     		Date date = null;
     		if(dateStr.length() == 19){
     			date = sdfLong.parse(dateStr);
     		}
     		if(dateStr.length() == 10){
     			date = sdfshort.parse(dateStr);
     		}
     		if(null == date){
     			return "";
     		}
     		calendar.setTime(date);
     		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
     		
     		return s[calendar.get(Calendar.DAY_OF_WEEK) - 1];
 		} catch (Exception e) {
 			e.printStackTrace();
 			return "";
 		}
     }
     

     /***
      * 获取当前月份的最后一天
      * @return
      */
	public static String getMonthLastDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);// 设为当前月的1号
		c.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		c.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		return sdfLong.format(c.getTime());
	}
	
	/***
     * 获取当前月份的第一天
     * @return
     */
	public static String getMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, 1);// 设为当前月的1号
		return sdfLong.format(c.getTime());
	}
     
	
	 /**
     * 获得开始时间和结束时间之间的时间列表
     * @param startDate
     * @param endDate
     * @return List<String>
     * @throws ParseException
     */
//    public static List getDateList(String startDate, String endDate){
//        try {
//        	List<String> dateList = new ArrayList<String>();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH); // 设定格式
//            Date start = dateFormat.parse(startDate);
//            Date end = dateFormat.parse(endDate);
//            long day = ((end.getTime() - start.getTime()) / 1000);// 秒数
//            day = day / (60 * 60 * 24); // 天数
//            for (int i = 0; i <= day; i++) {
//                String date = getAfterSomeDayYms(startDate, i);
//                dateList.add(date);
//            }
//            return dateList;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//    }    
    
    /**
     * 判断所给时间在月的第几周
     * @param date 输入时间
     * @return 第几周
     */
    public static int weekOfMonth(String date) {
        try {
        	Date d = sdfLong.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(d);
            return c.get(Calendar.WEEK_OF_MONTH);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
    }
    
    /**
     * 得到当月有多少天。
     * @return int
     */
    public static int daysNumOfMonth(){
        Calendar cal = Calendar.getInstance();
        return cal.getActualMaximum(Calendar.DATE);
    }
    
    /**
     * 得到今天的第一秒的时间。
     * @return Date
     */
    public static String dayStart() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return sdfLong.format(new Date(c.getTimeInMillis()));
    }
     
    /**
     * 得到今天的最后一秒的时间。
     * @return Date
     */
    public static String dayEnd() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        return sdfLong.format(new Date(c.getTimeInMillis()));
    }
    
    public static void main(String[] args) {
//		System.out.println(DateUtil.getCurrentTime());
//		System.out.println(DateUtil.getCurrentDateTime());
//		System.out.println(DateUtil.getCurrentDate());
//		System.out.println(DateUtil.getYesterday());
//    	String date = "2016-08-02 12:23:44";
//    	System.out.println(DateUtil.dayStart());
    	
	}

}
