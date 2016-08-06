package com.example.seaice.googleplay.googleplay.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by seaice on 2016/7/27.
 */
public abstract class BaseActivity extends ActionBarActivity {

    static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        synchronized (mActivities){
            mActivities.add(this);
        }
    }

    abstract protected void initData();

    abstract protected void initView();

    public void killAll(){
        List<BaseActivity> copy;
        synchronized (mActivities){
            copy = new LinkedList<BaseActivity>(mActivities);
        }
        for(BaseActivity activity : copy){
            activity.finish();
        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
