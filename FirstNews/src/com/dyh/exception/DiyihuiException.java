package com.dyh.exception;

import com.dyh.broadcast.BroadcastConstant;
import com.dyh.utils.LogUtil;

import android.content.Context;
import android.content.Intent;

/**异常信息提示
 * @author Messi
 *
 */
public class DiyihuiException extends Exception {

	private static final long serialVersionUID = 1L;
	
    public DiyihuiException(Context mContext, int code) {
        LogUtil.ExceptionLog("DiyihuiException:"+code);
        Intent intent = new Intent();
        intent.setAction(BroadcastConstant.INTENT_ACTION_EXCEPTION);
        intent.putExtra("type", "error");
        intent.putExtra("error_code", code);
        mContext.sendBroadcast(intent);
    }
    
    public DiyihuiException(Context mContext, String message) {
        super(message);
        LogUtil.ExceptionLog("DiyihuiException:"+message);
        Intent intent = new Intent();
        intent.setAction(BroadcastConstant.INTENT_ACTION_EXCEPTION);
        intent.putExtra("type", "exception");
        intent.putExtra("error_info", message);
        mContext.sendBroadcast(intent);
    }
    
}
