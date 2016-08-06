package com.example.seaice.googleplay.googleplay.utils;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by seaice on 2016/7/27.
 */
public class ViewUtils {

    public static void removeParent(View view){

        ViewParent parent = view.getParent();
        if(parent instanceof ViewGroup){
            ((ViewGroup) parent).removeView(view);
        }
    }
}
