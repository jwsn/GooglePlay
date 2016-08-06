package com.example.seaice.googleplay.googleplay.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.example.seaice.googleplay.R;
import com.example.seaice.googleplay.googleplay.utils.BaseApplication;
import com.example.seaice.googleplay.googleplay.utils.ThreadManager;
import com.example.seaice.googleplay.googleplay.utils.UiTools;

import butterknife.ButterKnife;

/**
 * Created by seaice on 2016/7/29.
 */
public abstract class LoadingView extends FrameLayout {

    private static final int STATE_UNKONE = 1;
    private static final int STATE_LOADING = 2;
    private static final int STATE_ERROR = 3;
    private static final int STATE_SUCCESS = 4;
    private static final int STATE_EMPTY = 5;

    private int mState = STATE_UNKONE;
    private View loadingView;
    private View errorView;
    private View emptyView;
    private View successView;

    public enum LoadResult {
        RESULT_ERROR(3),
        RESULT_SUCCESS(4),
        RESULT_EMPTY(5);
        int result;

        LoadResult(int result) {
            this.result = result;
        }

        public int getLoadResult() {
            return result;
        }
    }

    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        loadingView = createLoadingView();
        errorView = createErrorView();
        emptyView = createEmptyView();
        successView = createSuccessView();
        this.addView(loadingView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(errorView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(emptyView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.addView(successView, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    abstract public View createSuccessView();


    private void showPage() {
        loadingView.setVisibility((mState == STATE_UNKONE || mState == STATE_LOADING) ? View.VISIBLE : View.INVISIBLE);
        errorView.setVisibility(mState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        emptyView.setVisibility(mState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        successView.setVisibility(mState == STATE_SUCCESS ? View.VISIBLE : View.INVISIBLE);
    }

    private View createEmptyView() {
        emptyView = View.inflate(BaseApplication.getApplication(), R.layout.error_pager, null);
        return emptyView;
    }

    private View createErrorView() {
        errorView = View.inflate(BaseApplication.getApplication(), R.layout.error_pager, null);
        Button btn = ButterKnife.findById(errorView, R.id.btn_refresh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return errorView;
    }

    public void show() {
        mState = STATE_UNKONE;
        showPage();
        Runnable r = new Runnable() {
            @Override
            public void run() {
//                try {
                    //Thread.sleep(2000);
                    final LoadResult loadResult = loadData();
                    UiTools.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (loadResult != null) {
                                mState = loadResult.getLoadResult();
                                showPage();
                            }
                        }
                    });
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
        };
        ThreadManager.getThreadPool().execute(r);
    }

    protected abstract LoadResult loadData();

    private View createLoadingView() {
        loadingView = View.inflate(BaseApplication.getApplication(), R.layout.loading_pager, null);
        return loadingView;
    }

}
