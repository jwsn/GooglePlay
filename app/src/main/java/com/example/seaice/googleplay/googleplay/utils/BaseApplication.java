package com.example.seaice.googleplay.googleplay.utils;

import android.app.Application;
import android.content.pm.ApplicationInfo;
import android.os.Handler;

/**
 * 获取上下文环境，同时要在清单文件中配置name,
 * 应用在启动的时候，必定会跑这个Application
 * Created by seaice on 2016/7/27.
 */
public class BaseApplication extends Application {
    private static BaseApplication application;

    private static int mainThreadTid;
    private static Handler handler;
    @Override
    public void onCreate(){
        super.onCreate();
        application = this;
        mainThreadTid = android.os.Process.myTid();
        handler = new Handler();
    }

    public static BaseApplication getApplication(){
        return application;
    }

    public static int getMainThreadId(){
        return mainThreadTid;
    }

    public static Handler getHandler(){
        return handler;
    }
}
