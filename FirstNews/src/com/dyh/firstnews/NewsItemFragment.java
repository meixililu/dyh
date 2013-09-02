package com.dyh.firstnews;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dyh.adapter.NewsListItemAdapter;
import com.dyh.bean.NewsBean;
import com.dyh.http.DiyihuiHttp;
import com.dyh.http.HttpUtil;
import com.dyh.parser.NewsBeanParser;
import com.dyh.utils.LogUtil;
import com.dyh.views.MyListView;

public final class NewsItemFragment extends Fragment {
	
    private static final String KEY_CONTENT = "TestFragment:Content";
    private ProgressBar mProgressBar;
    private TextView load_fail_tv;
    private MyListView listview;
    private ArrayList<NewsBean> beans;
    private NewsListItemAdapter adapter;

    public static NewsItemFragment newInstance(String content) {
        NewsItemFragment fragment = new NewsItemFragment();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            builder.append(content).append(" ");
        }
        builder.deleteCharAt(builder.length() - 1);
        fragment.mContent = builder.toString();

        return fragment;
    }

    private String mContent = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent = savedInstanceState.getString(KEY_CONTENT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	LogUtil.DefalutLog(this.getClass().getName()+"---onCreateView");
    	View view = (View)inflater.inflate(R.layout.fragment_news_item, container, false);
    	mProgressBar = (ProgressBar)view.findViewById(R.id.loading_progressbar);
    	load_fail_tv = (TextView)view.findViewById(R.id.load_fail_tv);
    	listview = (MyListView)view.findViewById(R.id.listview);
    	
    	beans = new ArrayList<NewsBean>();
    	adapter = new NewsListItemAdapter(getActivity(),beans);
    	listview.setAdapter(adapter);
    	
    	listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(getActivity(),NewsDetailActivity.class);
				getActivity().startActivity(intent);
			}
		});
    	listview.setonRefreshListener(new MyListView.OnRefreshListener() {
            public void onRefresh() {
            	beans.clear();
            	adapter.notifyDataSetChanged();
            	new RequsetDataTask().execute();
            }
        });
    	new RequsetDataTask().execute();
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent);
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
    			map.put("typeid", "1");
    			List<NewsBean> resultList = (List<NewsBean>) new DiyihuiHttp().post(getActivity(),HttpUtil.news,map,new NewsBeanParser() );
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
	public void onDestroyView() {
		super.onDestroyView();
		LogUtil.DefalutLog(this.getClass().getName()+"---onDestroyView");
	}
}
