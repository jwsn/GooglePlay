package com.example.seaice.googleplay.googleplay.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seaice.googleplay.R;
import com.example.seaice.googleplay.googleplay.utils.ThreadManager;
import com.example.seaice.googleplay.googleplay.utils.ViewUtils;

import java.security.UnrecoverableKeyException;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by seaice on 2016/7/27.
 */
public abstract class BaseFragment extends Fragment {


    private LoadingView loadingView;


    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle saveInstanceState) {

        if (loadingView == null) {
            loadingView = new LoadingView(this.getActivity()) {
                @Override
                public View createSuccessView() {
                    return BaseFragment.this.createSuccessView();
                }

                @Override
                protected LoadResult loadData() {
                    return BaseFragment.this.loadData();
                }
            };
        } else {
            ViewUtils.removeParent(loadingView);
        }
        show();

        return loadingView;
    }

    public void show() {
        if (loadingView != null) {
            loadingView.show();
        }
    }

    /**
     * 访问服务器
     *
     * @return
     */
    public abstract LoadingView.LoadResult loadData();

    /**
     * 创建成功的画面
     *
     * @return
     */
    public abstract View createSuccessView();


}
