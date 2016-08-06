package com.example.seaice.googleplay.googleplay.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by seaice on 2016/7/27.
 */
public class FragmentFactory {
    private static Map<Integer, BaseFragment> mFragents = new HashMap<Integer, BaseFragment>();

    private static final int HOME = 0;
    private static final int APP = 1;
    private static final int GAME = 2;
    private static final int SUBJECT = 3;
    private static final int CAT = 4;
    private static final int TOP = 5;


    public static BaseFragment createFragment(int pos) {
        BaseFragment fragment = null;
        fragment = mFragents.get(pos);
        if (fragment == null) {
            if (pos == HOME) {
                fragment = new HomeFragment();
            } else if (pos == APP) {
                fragment = new AppFragment();
            } else if (pos == GAME) {
                fragment = new GameFragment();
            } else if (pos == SUBJECT) {
                fragment = new SubjectFragment();
            } else if (pos == CAT) {
                fragment = new CatFragment();
            } else if (pos == TOP) {
                fragment = new TopFragment();
            }
            mFragents.put(pos, fragment);
        }
        return fragment;
    }
}
