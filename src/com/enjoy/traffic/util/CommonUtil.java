package com.enjoy.traffic.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

	public static Date stringToDate(String time){
//		 String time="2010-11-20 11:10:10"; 
	  Date date=null; 
	  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	  try {
		date=formatter.parse(time);
		return date;
	  } catch (ParseException e) {
		e.printStackTrace();
		return null;
	  }
	}
	
	public static Date stringToDateMin(String time){
//		 String time="2010-11-20 11:10:10"; 
	  Date date=null; 
	  SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	  try {
		date=formatter.parse(time);
		return date;
	  } catch (ParseException e) {
		e.printStackTrace();
		return null;
	  }
	}
}
