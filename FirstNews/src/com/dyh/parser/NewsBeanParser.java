package com.dyh.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.dyh.bean.NewsBean;

public class NewsBeanParser extends AbstractParser<NewsBean> {

	@Override
	public NewsBean parse(JSONObject json) throws JSONException {
		NewsBean mResult = new NewsBean();
		if (json.has("id")) {
			mResult.setId(json.getString("id"));
		}
		if (json.has("content")) {
			mResult.setContent(json.getString("content"));
		}
		if (json.has("icon")) {
			mResult.setIcon(json.getString("icon"));
		}
		if (json.has("title")) {
			mResult.setTitle(json.getString("title"));
		}
		if (json.has("time")) {
			mResult.setTime(json.getString("time"));
		}
		if (json.has("source")) {
			mResult.setSource(json.getString("source"));
		}
		return mResult;
	}
}
