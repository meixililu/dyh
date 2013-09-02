package com.dyh.utils;

import android.util.Log;

public class LogUtil {
	
	public static boolean DEBUG = true;
	public static final String Tag = "firstNews";
	
	/**默认log，TAG�?SafeLottery
	 * @param logContent
	 */
	public static void DefalutLog(String logContent){
		if(DEBUG) Log.d(Tag, logContent);
	}
	
	/**
	 * @param logContent
	 */
	public static void SystemLog(String logContent){
		if(DEBUG) Log.d("System.out", logContent);
	}
	
	/**
	 * @param TAG
	 * @param logContent
	 */
	public static void CustomLog(String TAG, String logContent){
		if(DEBUG) Log.d(TAG, logContent);
	}

	/**
	 * @param logContent
	 */
	public static void ErrorLog(String logContent){
		if(DEBUG) Log.d(Tag,"Error---"+logContent);
	}
	
	/**
	 * @param logContent
	 */
	public static void ExceptionLog(String logContent){
		if(DEBUG) Log.e(Tag,"Exception---"+logContent);
	}
}
