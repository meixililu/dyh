package com.dyh.http;

import java.util.HashMap;

public class HttpUtil {
	
	public static HashMap<String,Object> dataMap = new HashMap<String,Object>();
	
	public static final String succeeCode = "200";
	
	public static final String url = "http://192.168.60.205:8080/ckiasrv/";
	
	public static final String news = url + "getnewslist";
	
	public static final String topic = url + "gettopiclist";
	/**第一汇**/
	public static final String phvalue = url + "getphvaluelist";

	public static final String notice = url + "getnoticelist";
}
