package com.szzgkon.googleplay.fragment;


import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.adapter.ListBaseAdapter;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.protocol.AppProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.BaseListView;
import com.szzgkon.googleplay.view.LoadingPage;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppFragment extends BaseFragment {


    private List<AppInfo> datas;

    /**
     * 当加载成功的时候，显示的界面
     * @return
     */
    @Override
    public View createSuccessView() {

        BaseListView listView = new BaseListView(UIUtils.getContext());
        listView.setAdapter(new ListBaseAdapter(datas));
        return listView;
    }




    /**
     * 请求服务器 获取服务器的数据
     * @return
     */
    @Override
    protected LoadingPage.LoadResult load() {

        AppProtocol protocol = new AppProtocol();
        datas = protocol.load(0);


        return checkData(datas);
    }
}
