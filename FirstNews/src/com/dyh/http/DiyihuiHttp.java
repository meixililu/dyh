package com.dyh.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.dyh.bean.BaseType;
import com.dyh.bean.ResultBean;
import com.dyh.exception.DiyihuiException;
import com.dyh.parser.JsonUtils;
import com.dyh.parser.Parser;
import com.dyh.parser.ResultParser;
import com.dyh.utils.HttpCheck;
import com.dyh.utils.LogUtil;

import android.content.Context;
import android.text.TextUtils;

public class DiyihuiHttp extends AbstractHttpApi {
	
	private DefaultHttpClient httpClient;

	public DiyihuiHttp() {
		httpClient = AbstractHttpApi.createHttpClient();
	}

	/**
	 * 直接返回相应的bean数据
	 * @param mContext
	 * @param cmd 操作码
	 * @param func 功能名称
	 * @param mJson 具体的业务参数(json格式)
	 * @param isHandleResult 是否手动处理结果,一般使用false
	 * @return
	 * @throws Exception
	 */
	public List<? extends BaseType> post(Context mContext, String path, Map<String, String> params, Parser<? extends BaseType> parser) throws Exception {
		String contentTemp = doPost(path, params, mContext, true);
		LogUtil.DefalutLog("return result : " + contentTemp);
		ResultBean mResult = new ResultParser().parse(JsonUtils.stringToJson(contentTemp));
		List<? extends BaseType> beans = null;
		if (!mResult.getCode().equals(HttpUtil.succeeCode)) {
			if (!TextUtils.isEmpty(mResult.getMsg())) {
				error(mContext, mResult.getMsg());
			}
			return null;
		} else {
			String resultStr = mResult.getList();
			if(!TextUtils.isEmpty(resultStr)){
				beans = JsonUtils.parserJsonArray(resultStr, parser);
			}
		}

		return beans;
	}

	/**
	 * 内容以map格式返回
	 * @param mContext
	 * @param cmd 操作码
	 * @param func 功能名称
	 * @param mJson 具体的业务参数(json格式)
	 * @param isHandleResult 是否手动处理结果,一般使用false
	 * @return
	 * @throws Exception
	 */
//	public Map post(Context mContext, String cmd, String func, String msg) throws Exception {
//		Map<String, String> map = getHashMap(mContext, cmd, func, msg);
//		String contentTemp = doPost(GetString.SERVERURL, map, "UTF-8", mContext, true);
//		LogUtil.DefalutLog("return result : " + contentTemp);
//		Result mResult = new ResultParser().parse(JsonUtils.stringToJson(contentTemp));
//		Map base = null;
//
//		if (!mResult.getCode().equals(SystemInfo.succeeCode)) {
//			if (!TextUtils.isEmpty(mResult.getMsg())) {
//				error(mContext, mResult.getMsg());
//			}
//			return null;
//		} else {
//			String resultStr = mResult.getResult();
//			if(!TextUtils.isEmpty(resultStr)){
//				base = JsonUtils.stringToMap(resultStr);
//			}
//		}
//
//		return base;
//	}

	/**
	 * @param mContext
	 * @param cmd 操作码
	 * @param func 功能名称
	 * @param mJson 具体的业务参数(json格式)
	 * @param isHandleResult 是否手动处理结果,一般使用false
	 * @return
	 * @throws Exception
	 */
	public ResultBean post(Context mContext, String path, Map<String, String> params, boolean isHandleResult) throws Exception {
		String contentTemp = doPost(path, params, mContext, true);
		LogUtil.DefalutLog("return result : " + contentTemp);
		ResultBean mResult = new ResultParser().parse(JsonUtils.stringToJson(contentTemp));
		if (isHandleResult) {
			return mResult;
		} else {
			if (!mResult.getCode().equals(HttpUtil.succeeCode)) {
				if (!TextUtils.isEmpty(mResult.getMsg())) {
					error(mContext, mResult.getMsg());
				}
				return null;
			}
		}
		return mResult;
	}

	/**
	 * MD5加密
	 * 
	 * @param cmd
	 * @param func
	 * @param msg
	 * @return
	 */
	public String doPost(String path, Map<String, String> params, Context mContext, boolean isReportError) throws UnsupportedEncodingException, IOException, DiyihuiException {
		LogUtil.DefalutLog("doPost");
		List<NameValuePair> param = paramNameValuePair(params);
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(param, HTTP.UTF_8);
		HttpPost post = new HttpPost(path);
		post.setEntity(entity);
		HttpResponse response = executeHttpRequest(mContext, post, isReportError);
		int code = response.getStatusLine().getStatusCode();
		if (code == 200) {
			String content = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
			LogUtil.DefalutLog("content---"+content);
			return content;
		} else {
			try {
				if(isReportError){
					error(mContext, code);
				}
			} catch (DiyihuiException e) {
				e.printStackTrace();
			}
			return null;
		}
	}

	private String doGet(Context mContext, String path, Map<String, String> params, String encoding, boolean isReportError) throws UnsupportedEncodingException, IOException, DiyihuiException {
		LogUtil.DefalutLog("doGet");
		List<NameValuePair> param = paramNameValuePair(params);
		String query = URLEncodedUtils.format(param, HTTP.UTF_8);
		HttpGet get = new HttpGet(path + "?" + query);
		HttpResponse response = executeHttpRequest(mContext, get, isReportError);
		if (response.getStatusLine().getStatusCode() == 200) {
			String content = EntityUtils.toString(response.getEntity(), encoding);
			return content;
		} else {
			return null;
		}
	}

	/**
	 * change Map into NameValuePair.
	 * 
	 * @param List
	 *            <NameValuePair>
	 */
	public List<NameValuePair> paramNameValuePair(Map<String, String> params) {
		List<NameValuePair> param = new ArrayList<NameValuePair>();
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				param.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		return param;
	}

	/**
	 * execute() an httpRequest catching exceptions and returning null instead.
	 * 
	 * @param httpRequest
	 * @throws IOException
	 * @throws DiyihuiException 
	 */
	public HttpResponse executeHttpRequest(Context mContext, HttpRequestBase httpRequest, boolean isReportError) throws IOException, DiyihuiException {
		LogUtil.DefalutLog("executing HttpRequest for: " + httpRequest.getURI().toString());
		try {
			httpClient.getConnectionManager().closeExpiredConnections();
			return httpClient.execute(httpRequest);
		} catch (IOException e) {
			e.printStackTrace();
			httpRequest.abort();
			if(isReportError){
				error(mContext, HttpCheck.checkNetwork(mContext, 1));
			}
			throw e;
		}
	}

	private void error(Context mContext, int code) throws DiyihuiException {
		throw new DiyihuiException(mContext, code);
	}

	private void error(Context mContext, String msg) throws DiyihuiException {
		throw new DiyihuiException(mContext, msg);
	}
}
