package com.dyh.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dyh.bean.BaseType;
import com.dyh.utils.LogUtil;

import android.text.TextUtils;


public class JsonUtils {

	/**
	 * 将字符串转为json
	 * 
	 * @param str
	 * @return
	 */
	public static JSONObject stringToJson(String str) {
		JSONObject mJSONObject = null;
		try {
			mJSONObject = new JSONObject(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mJSONObject;
	}

	/**
	 * 将json转为Map
	 * 
	 * @param str
	 * @return
	 */
	public static Map<String, String> stringToMap(String json) {
		JSONObject jsonObject = null;
		Map<String, String> result = new HashMap<String, String>();
		try {
			if(!TextUtils.isEmpty(json)){
				jsonObject = new JSONObject(json);
				Iterator<String> iterator = jsonObject.keys();
				String key = null;
				String value = null;
				while (iterator.hasNext()) {
					key = iterator.next();
					value = jsonObject.getString(key);
					result.put(key, value);
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将字符串转为List
	 * 
	 * @param str
	 * @return
	 */
	public static List<Map<String, String>> stringToList(String str) {
		List<Map<String, String>> list = null;
		try {
			if(!TextUtils.isEmpty(str)){
				JSONArray jsonArray = new JSONArray(str);
				JSONObject jsonObject;
				list = new ArrayList<Map<String, String>>();
				for (int i = 0,j = jsonArray.length(); i < j; i++) {
					jsonObject = jsonArray.getJSONObject(i);
					list.add(stringToMap(jsonObject.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.ExceptionLog("List<Map<String, String>> stringToList(String str)");
		}
		return list;
	}
	
	/**将json数据转换成为对应的bean list
	 * @param str
	 * @param parser
	 * @return
	 */
	public static List<? extends BaseType> parserJsonArray(String str,Parser<? extends BaseType> parser){
		List<BaseType> list = null;
		try {
			list = new ArrayList<BaseType>();
			if(!TextUtils.isEmpty(str)){
				JSONArray jsonArray = new JSONArray(str);
				JSONObject jsonObject;
				for (int i = 0,j = jsonArray.length(); i < j; i++) {
					jsonObject = jsonArray.getJSONObject(i);
					BaseType mBean = parser.parse(jsonObject);
					LogUtil.DefalutLog("SafelotteryType:"+mBean.toString());
					list.add( mBean );
				}
			}
		} catch (Exception e) {
			BaseType mBean = parserJsonBean(str,parser);
			list.add( mBean );
			LogUtil.ExceptionLog(mBean.toString());
			e.printStackTrace();
			LogUtil.ExceptionLog("List<? extends SafelotteryType> parserJsonArray");
		}
		return list;
	}
	
	/**将json数据转换成为对应的bean
	 * @param str
	 * @param parser
	 * @return
	 */
	public static BaseType parserJsonBean(String str,Parser<? extends BaseType> parser){
		try {
			JSONObject jsonObject = new JSONObject(str);
			return parser.parse(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.ExceptionLog("SafelotteryType parserJsonBean");
		}
		return null;
	}

	/**
	 * 把数据转换成Json结构
	 * 
	 * @param mType
	 *            Object的数据，支持所有类型
	 * @return
	 */
//	public static String toJsonStr(Object mType) {
//		Gson mGson = new Gson();
//		String mResult = mGson.toJson(mType);
//		return mResult;
//	}
}
