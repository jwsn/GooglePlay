package com.example.seaice.googleplay.googleplay.utils;

import android.content.Context;
import android.content.res.Resources;

import com.example.seaice.googleplay.googleplay.fragment.BaseFragment;

/**
 * Created by seaice on 2016/7/27.
 */
public class UiTools {

    /**
     * 获取字符串数组
     *
     * @param tabNames
     * @return
     */
    public static String[] getStringArray(int tabNames) {
        return getResource().getStringArray(tabNames);
    }

    public static Resources getResource() {
        return BaseApplication.getApplication().getResources();
    }

    /**
     * dip转px
     *
     * @param dip
     * @return
     */
    public static int dip2px(int dip) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * px转dip
     *
     * @param px
     * @return
     */
    public static int px2dip(int px) {
        final float scale = getResource().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 把runnable的方法提交到主线程去运行
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        //证明在主线程运行
        if (android.os.Process.myTid() == BaseApplication.getMainThreadId()) {
            runnable.run();
        } else {
            //用主线程的handler
            BaseApplication.getHandler().post(runnable);
        }
    }

}
