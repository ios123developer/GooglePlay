package com.szzgkon.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.adapter.ListBaseAdapter;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.protocol.HomeProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.BaseListView;
import com.szzgkon.googleplay.view.LoadingPage;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment {


    private List<AppInfo> datas;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    public View createSuccessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas));


        return listView;
    }



    public LoadingPage.LoadResult load() {

        HomeProtocol protocol = new HomeProtocol();


        datas = protocol.load(0);


        return checkData(datas);
    }


}
