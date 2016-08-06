package com.example.seaice.googleplay.googleplay.fragment;

import android.view.View;

import com.example.seaice.googleplay.R;

/**
 * Created by seaice on 2016/7/27.
 */
public class GameFragment extends BaseFragment {


    @Override
    public LoadingView.LoadResult loadData() {
        return LoadingView.LoadResult.RESULT_ERROR;
    }

    @Override
    public View createSuccessView() {
        View view = View.inflate(getActivity(), R.layout.success_pager, null);
        return view;
    }
}
