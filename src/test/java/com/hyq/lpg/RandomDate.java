package com.hyq.lpg;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RandomDate {
	/** 
	* 生成一个在开始日期和结束日期之间的随机日期 
	* @param beginDate 开始日期
	* @param endDate 结束日期
	* @return 返回一个在beginDate与endDate之间的随机日期
	*/
	 
	
	    public static Date randomDate(String beginDate,String endDate,String parnt){
	        try {
	            //建立一个SimpleDateFormat对象，指定好时间格式
	            SimpleDateFormat format = new SimpleDateFormat(parnt);

	            //把传进来的String类型的时间转化为Date类型 
	            Date start = format.parse(beginDate);
	            Date end = format.parse(endDate);

	            //如果开始时间大于等于结束时间，啥也不干了，返回null
	            if(start.getTime() >= end.getTime()){
	                return null;
	            }

	            //调用random函数，生成代表特定日期的long类型的随机数
	            //getTime函数得到的是long类型的数
	            long date = random(start.getTime(),end.getTime());

	            //根据这个随机数，实例化一个日期对象，也就是生成了一个随机日期
	            return new Date(date);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return null;
	    }

	    
	    public static String randomStringDate(String beginDate,String endDate,String parnt){
           
	        
	        return new SimpleDateFormat(parnt).format(randomDate(beginDate,endDate,parnt));
	        
	    }
	/**
	* 生成一个long类型的随机数 
	* @param begin 代表开始日期的long类型数
	* @param end 代表结束日期的long类型数
	* @return 返回long类型的随机数
	*/
	    private static long random(long begin,long end){
	        //Math.random()生成0到1之间的一个随机数
	        //随机数接近0时，生成的日期接近开始日期，随机数接近1时，生成的日期接近结束日期
	        long rtn = begin + (long)(Math.random() * (end - begin));
	        if(rtn == begin || rtn == end){
	            return random(begin,end);
	        }
	        return rtn;
	    }
	    
	    
	    public static void main(String[] args){
	    	String d = RandomDate.randomStringDate("2014-01-11 00:00:00", "2014-03-11 00:00:00","yyyy-MM-dd HH:mm:ss");
      //      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	    	System.out.println(d);
	    }
	}
