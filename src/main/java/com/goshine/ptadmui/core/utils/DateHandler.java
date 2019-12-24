package com.goshine.ptadmui.core.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具类
 * @author goshine
 */
public class DateHandler{
    public final static String Date_Fomat_Str="yyyy-MM-dd";
    public final static String Time_Format_Str="yyyy-MM-dd HH:mm:ss";
    
    /**
     * 获取某时间格式化后的时间字符串
     * @return
     */
    public static Date formatDateFromStr(String pattern,String dateStr){
    	try{
    		if(StringHandler.isNullOrEmpty(pattern)){
    			pattern=Time_Format_Str;
    		}
	    	DateFormat format=new SimpleDateFormat(pattern);
	    	format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    	return format.parse(dateStr);
    	}catch(Exception e){}
    	return null;
    }
    
    /**
     * 获取某时间格式化后的时间字符串
     * @return
     */
    public static String formatDate(Date date){
    	try{
	    	DateFormat format=new SimpleDateFormat(Date_Fomat_Str);
	    	format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    	return format.format(date);
    	}catch(Exception e){}
    	return null;
    }
    
    /**
     * 获取某时间格式化后的时间字符串
     * @return
     */
    public static String formatDate(String pattern,Date date){
    	try{
    		if(StringHandler.isNullOrEmpty(pattern)){
    			pattern=Time_Format_Str;
    		}
	    	DateFormat format=new SimpleDateFormat(pattern);
	    	format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	    	return format.format(date);
    	}catch(Exception e){}
    	return null;
    }
    
    
    /**
     * 获取当前时间字符串
     * @return
     */
    public static String getCurrentTimeString(){
    	DateFormat format=new SimpleDateFormat(Time_Format_Str);
    	format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    	return format.format(new Date());
    }
    /**
     * 获取当前日期字符串
     * @return
     */
    public static String getCurrentDateString(){
    	DateFormat format=new SimpleDateFormat(Date_Fomat_Str);
    	format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    	return format.format(new Date());
    }
    /**
     * 比较两个日期大小
     * date1大于date2 返回1
     * date1小于date2返回-1
     * 相同返回0
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTime(Date date1,Date date2){
        try {
            if (date1.getTime()>date2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (date1.getTime()<date2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 比较两个日期大小
     * date1大于date2 返回1
     * date1小于date2返回-1
     * 相同返回0
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDateTime(String date1,String date2) {
        DateFormat df = new SimpleDateFormat(Time_Format_Str);
        df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime()>dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime()<dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 获取两个时间直接的差值
     * SEC:秒数，MIN:分钟数，HOUR:小时数，DAY:天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getDateDiff(Date beginDate,Date endDate,String type){ 
	    long between=(endDate.getTime()-beginDate.getTime());
	    if("SEC".equals(type)){
	        return between/1000;
	    }else if("MIN".equals(type)){
	    	return between/(60*1000);
	    }else if("HOUR".equals(type)){
	    	return between/(60*60*1000);
	    }else if("DAY".equals(type)){
	    	return between/(60*60*24*1000);
	    }
	    return between;
    }
    /**
     * 获取两个时间直接的差值
     * SEC:秒数，MIN:分钟数，HOUR:小时数，DAY:天数
     * @param date1
     * @param date2
     * @return
     */
    public static long getDateDiff(String beginDateStr,String endDateStr,String type){ 
    	DateFormat df = new SimpleDateFormat(Time_Format_Str);
    	df.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    	try{
	    	Date beginDate=df.parse(beginDateStr);
	    	Date endDate=df.parse(endDateStr);
		    long between=(endDate.getTime()-beginDate.getTime());
		    if("SEC".equals(type)){
		        return between/1000;
		    }else if("MIN".equals(type)){
		    	return between/(60*1000);
		    }else if("HOUR".equals(type)){
		    	return between/(60*60*1000);
		    }else if("DAY".equals(type)){
		    	return between/(60*60*24*1000);
		    }
		    return between;
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return 0;
    }
    /**
     * 日期加减几天
     * 获取某日期前后的日期
     * @param date
     * @param offset
     * @return
     */
    public static String addDate(Date date,int offset,String pattern) {  
        if( date==null) {  
            return null;  
        }  
        DateFormat format=new SimpleDateFormat(pattern);
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar c = Calendar.getInstance();  
        c.setTime(date);//设置日期  
        c.add(Calendar.DATE,offset); //日期加减若干天  
        date = c.getTime();  
        return format.format(date);  
    }
    
    /**
     * 根据条件获取最后一天的日期
     * 当月不为空，则获取当月最后一天
     * 当月为空，则获取当年最后一天
     * @return
     */
    public static String getLastDayByMonth(String year,String month){
    	// 获取当月第一天
    	if(StringHandler.isNullOrEmpty(month)){
    		month="12";
    	}
        //获取当月最后一天  
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        Calendar cal=Calendar.getInstance();
        cal.set(Integer.parseInt(year),Integer.parseInt(month),1);
        // 获取前月的最后一天  
        cal.set(Calendar.DAY_OF_MONTH, 0);  
        return format.format(cal.getTime());
    }
    
    public static void main(String[] args){
		String dateStr=DateHandler.formatDate("yyyy-MM-dd",new Date());
		String month=dateStr.substring(5,7);
        System.out.println(dateStr+"=======================surplusSecond:"+String.valueOf(Integer.parseInt(month)));
    }
}
