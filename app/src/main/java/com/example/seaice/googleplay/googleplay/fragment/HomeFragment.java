package com.example.seaice.googleplay.googleplay.fragment;

import android.view.View;

import com.example.seaice.googleplay.R;
import com.example.seaice.googleplay.googleplay.bean.AppInfo;
import com.example.seaice.googleplay.googleplay.protocol.HomeProtocol;

import java.util.List;

/**
 * Created by seaice on 2016/7/27.
 */
public class HomeFragment extends BaseFragment {


    @Override
    public LoadingView.LoadResult loadData() {

        HomeProtocol protocol = new HomeProtocol();
        protocol.load(0);
        return checkDatas(null);
    }

    @Override
    public View createSuccessView() {
        View view = View.inflate(getActivity(), R.layout.success_pager, null);
        return view;
    }

    private LoadingView.LoadResult checkDatas(List<AppInfo> datas){
        if(datas == null){
            return LoadingView.LoadResult.RESULT_ERROR;
        }else {
            if(datas.size() == 0){
                return LoadingView.LoadResult.RESULT_EMPTY;
            }else{
                return LoadingView.LoadResult.RESULT_SUCCESS;
            }
        }
    }
}
