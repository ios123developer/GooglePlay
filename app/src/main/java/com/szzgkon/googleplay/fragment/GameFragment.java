package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.adapter.ListBaseAdapter;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.protocol.GameProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.BaseListView;
import com.szzgkon.googleplay.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends BaseFragment {

    private List<AppInfo> datas;

    @Override
    public View createSuccessView() {
        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas,listView) {
            @Override
            protected List<AppInfo> onload() {
                GameProtocol protocol = new GameProtocol();
                List<AppInfo> newData = protocol.load(datas.size());
                datas.addAll(newData);
                return newData;
            }
        });;
        return listView;
    }


    @Override
    public LoadingPage.LoadResult load() {

        GameProtocol protocol = new GameProtocol();
        datas = protocol.load(0);
        return checkData(datas);
    }
}
