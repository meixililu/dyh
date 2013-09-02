package com.dyh.utils;

import com.dyh.firstnews.R;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

public abstract class TabsUtil {

	public static TextView addTab(TabHost host, String title, int drawable, int index, Intent intent) {
        View view = LayoutInflater.from(host.getContext()).inflate(R.layout.tab_main_nav, null);
        TextView unread_msg_tv = (TextView) view.findViewById(R.id.tab_unread_msg);
        TextView tv = (TextView) view.findViewById(R.id.tvTitle);
        ImageView iv = (ImageView) view.findViewById(R.id.ivIcon);
        tv.setText(title);
        iv.setBackgroundResource(drawable);
        
        TabHost.TabSpec spec = host.newTabSpec("tab" + index);
        spec.setContent(intent);
        spec.setIndicator(view);
        host.addTab(spec);
        return unread_msg_tv;
    }
}
