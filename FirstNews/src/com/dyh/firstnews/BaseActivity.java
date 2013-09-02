package com.dyh.firstnews;

import com.dyh.broadcast.BroadcastConstant;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class BaseActivity extends Activity {

	private BroadcastReceiver mLoggedOutReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		registerReceiver(mLoggedOutReceiver, new IntentFilter(BroadcastConstant.INTENT_ACTION_ALLACTIVITY));
	}

	@Override
	public void onPause() {
		super.onPause();
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(mLoggedOutReceiver);
	}
	
	/**处理特殊广播需求，子类@Override此方法进行处理操作
	 * @param ationCode
	 */
	protected void isNeedClose(int ationCode){
	}
	

}
