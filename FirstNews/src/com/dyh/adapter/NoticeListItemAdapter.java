package com.dyh.adapter;

import java.util.ArrayList;
import com.dyh.bean.NewsBean;
import com.dyh.firstnews.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NoticeListItemAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<NewsBean> beans;
	private Context context;

	public NoticeListItemAdapter(Context mContext,ArrayList<NewsBean> mBeans) {
		context = mContext;
		beans = mBeans;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return beans.size();
	}

	public Object getItem(int position) {
		return beans.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.listview_item_notice, null);
			holder = new ViewHolder();
			holder.news_title = (TextView) convertView.findViewById(R.id.news_title);
			holder.news_content = (TextView) convertView.findViewById(R.id.news_content);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewsBean mBean = beans.get(position);
//		holder.news_title.setText(mBean.getTitle());
		holder.news_title.setText("参加第四十一界俄联邦轻工纺织以及设备展的通知");
		holder.news_content.setText(mBean.getContent());
//		holder.news_img.setImageResource(resId);
		return convertView;
	}

	static class ViewHolder {
		TextView news_title;
		TextView news_content;
	}
}
