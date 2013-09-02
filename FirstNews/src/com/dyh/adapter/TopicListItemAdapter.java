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

public class TopicListItemAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<NewsBean> beans;
	private Context context;

	public TopicListItemAdapter(Context mContext,ArrayList<NewsBean> mBeans) {
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
			convertView = mInflater.inflate(R.layout.listview_item_topic, null);
			holder = new ViewHolder();
			holder.topic_img = (ImageView) convertView.findViewById(R.id.topic_img);
			holder.topic_title = (TextView) convertView.findViewById(R.id.topic_title);
			holder.topic_content = (TextView) convertView.findViewById(R.id.topic_content);
			holder.topic_comment = (TextView) convertView.findViewById(R.id.topic_comment);
			holder.topic_open = (TextView) convertView.findViewById(R.id.topic_open);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		NewsBean mBean = beans.get(position);
		holder.topic_title.setText(mBean.getTitle());
		holder.topic_content.setText(mBean.getContent());
//		holder.news_img.setImageResource(resId);
		return convertView;
	}

	static class ViewHolder {
		ImageView topic_img;
		TextView topic_title;
		TextView topic_content;
		TextView topic_comment;
		TextView topic_open;
	}
}
