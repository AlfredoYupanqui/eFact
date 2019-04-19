package com.efact.util;

import java.sql.Date;
import java.text.DecimalFormat;

public class Util {

	public static int strToInt(String s) {
        try {
        	return Integer.parseInt(s);
         } catch (NumberFormatException e) {
        	 return -1;
         }
	}
	
	public static String intToStr(int s) {
        try {
        	return String.valueOf(s); 
         } catch (Exception e) {
        	 return "";
         }
	}
	
	public static float strToFloat(String s) {
        try {
        	return Float.parseFloat(s);
         } catch (NumberFormatException e) {
        	 return -1;
         }
	}
	
	public static Date strToDate(String s) {
		try {
			return Date.valueOf(s);
		}catch (IllegalArgumentException e) {
			return null;
		}
	}
	
	public static boolean floatToBool(float s) {
		try {
			return s > 0 ? true : false;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static boolean intToBool(int s) {
		try {
			return s > 0 ? true : false;
		}catch (Exception e) {
			return false;
		}
	}
	
	public static int strDateToInt(String s) {
		try {
			return Integer.valueOf(s.replace("-", ""));
		}catch (NumberFormatException e) {
			return -1;
		}
	}

	public static int intDateToInt(int s) {
		try {
			String ss = String.valueOf(s);
			return Integer.valueOf(ss.replace("-", ""));
		}catch (NumberFormatException e) {
			return -1;
		}
	}

	public static String amountFormat(String s) {
		DecimalFormat decimalFormat = new DecimalFormat("###,###.##");
		return decimalFormat.format(s);
	}
	
	public static String amountFormat(double s, int digits) {
		DecimalFormat format = new DecimalFormat("#");
		format.setMinimumFractionDigits(digits);
		return format.format(s);
	}
	

	
}
