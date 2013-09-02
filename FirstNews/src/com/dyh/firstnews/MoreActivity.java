package com.dyh.firstnews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.dyh.utils.LogUtil;

public class MoreActivity extends Activity {
	
	private LinearLayout login_layout,boung_user_layout;
	private LinearLayout check_layout,history_layout;
	private LinearLayout collect_layout,about_layout;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_more);
        initViews();
        
    }
	
	private void initViews(){
		login_layout = (LinearLayout)findViewById(R.id.more_login_layout);
		boung_user_layout = (LinearLayout)findViewById(R.id.more_boung_user_layout);
		check_layout = (LinearLayout)findViewById(R.id.more_check_layout);
		history_layout = (LinearLayout)findViewById(R.id.more_history_layout);
		collect_layout = (LinearLayout)findViewById(R.id.more_collect_layout);
		about_layout = (LinearLayout)findViewById(R.id.more_about_layout);
		
		login_layout.setOnClickListener(mClickListener);
		boung_user_layout.setOnClickListener(mClickListener);
		check_layout.setOnClickListener(mClickListener);
		history_layout.setOnClickListener(mClickListener);
		collect_layout.setOnClickListener(mClickListener);
		about_layout.setOnClickListener(mClickListener);
	}

	private View.OnClickListener mClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.more_login_layout){
				Intent intent = new Intent(MoreActivity.this,LoginActivity.class);
				MoreActivity.this.startActivity(intent);
			}
		}
	};
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
