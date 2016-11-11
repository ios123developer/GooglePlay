package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {



    public HomeFragment() {
        // Required empty public constructor
    }


    public View creatSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("加载成功了......");
        tv.setTextSize(20);

        return tv;
    }

    public LoadResult load() {
        return LoadResult.success;
    }








}
