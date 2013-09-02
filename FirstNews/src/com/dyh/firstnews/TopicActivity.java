package com.dyh.firstnews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dyh.adapter.TopicListItemAdapter;
import com.dyh.bean.NewsBean;
import com.dyh.http.DiyihuiHttp;
import com.dyh.http.HttpUtil;
import com.dyh.parser.NewsBeanParser;
import com.dyh.utils.LogUtil;
import com.dyh.views.MyListView;

public class TopicActivity extends Activity {
	
	private ProgressBar mProgressBar;
    private TextView load_fail_tv;
    private MyListView listview;
    private ArrayList<NewsBean> beans;
    private TopicListItemAdapter adapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);
        LogUtil.DefalutLog(this.getClass().getName()+"---onCreate");
        mProgressBar = (ProgressBar)findViewById(R.id.loading_progressbar);
        load_fail_tv = (TextView)findViewById(R.id.load_fail_tv);
        listview = (MyListView)findViewById(R.id.listview);
        
        beans = new ArrayList<NewsBean>();
        adapter = new TopicListItemAdapter(this,beans);
        listview.setAdapter(adapter);
        
//        listview.setOnItemClickListener(new OnItemClickListener() {
//        	@Override
//        	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//        		Intent intent = new Intent(TopicActivity.this,NewsDetailActivity.class);
//        		TopicActivity.this.startActivity(intent);
//        	}
//        });
        listview.setonRefreshListener(new MyListView.OnRefreshListener() {
        	public void onRefresh() {
        		beans.clear();
        		adapter.notifyDataSetChanged();
        		new RequsetDataTask().execute();
        	}
        });
        new RequsetDataTask().execute();
        
    }

    class RequsetDataTask extends AsyncTask<Void, Void, Void> {

    	@Override
    	protected void onPreExecute() {
    		super.onPreExecute();
    		mProgressBar.setVisibility(View.VISIBLE);
    	}

    	@Override
    	protected Void doInBackground(Void... params) {
    		try {
    			HashMap<String, String> map = new HashMap<String, String>();
    			map.put("id", "0");
    			List<NewsBean> resultList = (List<NewsBean>) new DiyihuiHttp().post(TopicActivity.this,HttpUtil.topic,map,new NewsBeanParser() );
				if(resultList != null){
					beans.addAll(resultList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		super.onPostExecute(result);
    		mProgressBar.setVisibility(View.GONE);
    		listview.onRefreshComplete();
    		adapter.notifyDataSetChanged();
    	}
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
