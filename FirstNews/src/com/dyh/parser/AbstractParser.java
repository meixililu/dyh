package com.dyh.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.dyh.bean.BaseType;


public abstract class AbstractParser<T extends BaseType> implements Parser<T> {

	public abstract T parse(JSONObject json) throws JSONException;

}
