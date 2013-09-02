package com.dyh.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.dyh.bean.ResultBean;

public class ResultParser extends AbstractParser<ResultBean> {

	@Override
	public ResultBean parse(JSONObject json) throws JSONException {
		ResultBean mResult = new ResultBean();
		if (json.has("header")) {
			String header = json.getString("header");
			mResult.setHeader(header);
			JSONObject jobject = JsonUtils.stringToJson(header);
			if(jobject.has("code")){
				mResult.setCode(jobject.getString("code"));
			}
			if(jobject.has("msg")){
				mResult.setMsg(jobject.getString("msg"));
			}
		}
		if (json.has("list")) {
			mResult.setList(json.getString("list"));
		}
		return mResult;
	}
}
