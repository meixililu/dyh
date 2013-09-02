package com.dyh.firstnews;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import com.dyh.utils.LogUtil;
import com.dyh.views.PageIndicator;
import com.dyh.views.TabPageIndicator;

public class NewsActivity extends FragmentActivity {
	
	ViewPager mPager;
    PageIndicator mIndicator;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_news);
    	mPager = (ViewPager)findViewById(R.id.pager);
    	NewsFragmentAdapter mAdapter = new NewsFragmentAdapter(this.getSupportFragmentManager());
        mPager.setAdapter(mAdapter);

        mPager.setOffscreenPageLimit(5);
        mIndicator = (TabPageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }


	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.DefalutLog(this.getClass().getName()+"---onDestroy");
	}

	@Override
	public void onPause() {
		super.onPause();
		LogUtil.DefalutLog(this.getClass().getName()+"---onPause");
	}

	@Override
	public void onResume() {
		super.onResume();
		LogUtil.DefalutLog(this.getClass().getName()+"---onResume");
	}

	@Override
	public void onStart() {
		super.onStart();
		LogUtil.DefalutLog(this.getClass().getName()+"---onStart");
	}

	@Override
	public void onStop() {
		super.onStop();
		LogUtil.DefalutLog(this.getClass().getName()+"---onStop");
	}
    
    

}
