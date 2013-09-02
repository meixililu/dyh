package com.dyh.firstnews;

import android.app.TabActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TabHost;
import android.widget.TextView;

import com.dyh.broadcast.BroadcastConstant;
import com.dyh.utils.LogUtil;
import com.dyh.utils.TabsUtil;

public class MainActivity extends TabActivity  {
	
	private TabHost mTabHost;
	public static TextView unread_msg_topic,unread_msg_notice;
	
	private BroadcastReceiver mLoggedOutReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			LogUtil.DefalutLog("BroadcastReceiver---onReceive");
			String type = intent.getStringExtra(BroadcastConstant.BROADCAST_TYPE);
			if(type.equals(BroadcastConstant.BROADCAST_TYPE_MSG)){
				setUnreadMsgTopic("10");
				setUnreadMsgNotice("20");
			}else if(type.equals(BroadcastConstant.BROADCAST_TYPE_CLOSE)){
				
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.DefalutLog(this.getClass().getName()+"---onCreate");
		registerReceiver(mLoggedOutReceiver, new IntentFilter(BroadcastConstant.INTENT_ACTION_ALLACTIVITY));
		setContentView(R.layout.activity_main);

		mTabHost = getTabHost();  
		
		TabsUtil.addTab(mTabHost, getString(R.string.tab_main_nav_news),
				R.drawable.tab_nav_news_selector, 0, new Intent(this, NewsActivity.class));
		unread_msg_topic = TabsUtil.addTab(mTabHost, getString(R.string.tab_main_nav_topic),
				R.drawable.tab_nav_topic_selector, 1,  new Intent(this, TopicActivity.class));
		TabsUtil.addTab(mTabHost, getString(R.string.tab_main_nav_diyihui),
				R.drawable.tab_nav_diyihui_selector, 2,  new Intent(this, DiyihuiActivity.class));
		unread_msg_notice = TabsUtil.addTab(mTabHost, getString(R.string.tab_main_nav_notice),
				R.drawable.tab_nav_notice_selector, 3,  new Intent(this, NoticeActivity.class));
		TabsUtil.addTab(mTabHost, getString(R.string.tab_main_nav_more),
				R.drawable.tab_nav_more_selector, 4,  new Intent(this, MoreActivity.class));
		
		mTabHost.getTabWidget().getChildAt(1).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hideUnreadMsgTopic();
				mTabHost.setCurrentTab(1);
			}
		});
		mTabHost.getTabWidget().getChildAt(3).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				hideUnreadMsgNotice();
				mTabHost.setCurrentTab(3);
			}
		});
		setUnreadMsgTopic("3");
		setUnreadMsgNotice("5");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.DefalutLog(this.getClass().getName()+"---onDestroy");
		unregisterReceiver(mLoggedOutReceiver);
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.DefalutLog(this.getClass().getName()+"---onPause");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.DefalutLog(this.getClass().getName()+"---onResume");
		
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.DefalutLog(this.getClass().getName()+"---onStart");
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.DefalutLog(this.getClass().getName()+"---onStop");
	}
	
	public static void setUnreadMsgTopic(String number){
		unread_msg_topic.setVisibility(View.VISIBLE);
		unread_msg_topic.setText(number);
	}
	public static void setUnreadMsgNotice(String number){
		unread_msg_notice.setVisibility(View.VISIBLE);
		unread_msg_notice.setText(number);
	}
	public static void hideUnreadMsgTopic(){
		unread_msg_topic.setVisibility(View.GONE);
		unread_msg_topic.setText("");
	}
	public static void hideUnreadMsgNotice(){
		unread_msg_notice.setVisibility(View.GONE);
		unread_msg_notice.setText("");
	}
	
	

}
