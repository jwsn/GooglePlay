package com.example.seaice.googleplay.googleplay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.seaice.googleplay.R;
import com.example.seaice.googleplay.googleplay.fragment.FragmentFactory;
import com.example.seaice.googleplay.googleplay.utils.UiTools;

/**
 * Created by seaice on 2016/7/27.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    protected static final String[] CONTENT= UiTools.getStringArray(R.array.tabNames);

    private int mCount = CONTENT.length;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.createFragment(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return CONTENT[position % CONTENT.length];
    }

}
