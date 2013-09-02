package com.dyh.parser;

import org.json.JSONException;
import org.json.JSONObject;

import com.dyh.bean.BaseType;

/**
 * @author Messi
 *
 * @param <T>
 */
public interface Parser<T extends BaseType> {

	public abstract T parse(JSONObject json) throws JSONException;
}
