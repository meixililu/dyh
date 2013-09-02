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

public class NewsListItemAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<NewsBean> beans;
	private Context context;

	public NewsListItemAdapter(Context mContext,ArrayList<NewsBean> mBeans) {
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
			convertView = mInflater.inflate(R.layout.listview_item_news, null);
			holder = new ViewHolder();
			holder.news_title = (TextView) convertView.findViewById(R.id.news_title);
			holder.news_content = (TextView) convertView.findViewById(R.id.news_content);
			holder.news_img = (ImageView) convertView.findViewById(R.id.news_img);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewsBean mBean = beans.get(position);
		holder.news_title.setText(mBean.getTitle());
		holder.news_content.setText(mBean.getContent());
//		holder.news_img.setImageResource(resId);
		return convertView;
	}

	static class ViewHolder {
		TextView news_title;
		TextView news_content;
		ImageView news_img;
	}
}
