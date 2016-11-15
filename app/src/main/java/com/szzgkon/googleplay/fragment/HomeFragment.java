package com.szzgkon.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.szzgkon.googleplay.protocol.HomeProtocol;
import com.szzgkon.googleplay.view.LoadingPage;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       show();
    }

    public View creatSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("加载成功了......");
        tv.setTextSize(20);

        return tv;
    }

    public LoadingPage.LoadResult load() {

        HomeProtocol protocol = new HomeProtocol();
        protocol.load(0);
        return LoadingPage.LoadResult.success;
    }




}
