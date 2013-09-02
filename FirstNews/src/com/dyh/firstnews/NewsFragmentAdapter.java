package com.dyh.firstnews;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class NewsFragmentAdapter extends FragmentPagerAdapter {

	public static final String[] CONTENT = new String[] { "行业动态", "领袖观点", "消费调查", "技术前沿", "经济平飞", "企业如何" };

    public NewsFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return NewsItemFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return CONTENT.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length].toUpperCase();
    }
}