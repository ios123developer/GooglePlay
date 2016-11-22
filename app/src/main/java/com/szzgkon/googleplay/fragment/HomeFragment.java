package com.szzgkon.googleplay.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.szzgkon.googleplay.adapter.ListBaseAdapter;
import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.holder.HomePictureHolder;
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
    private List<String> pictures;//顶部viewPager 显示界面的资源
    private HomeProtocol protocol;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        show();
    }

    public View createSuccessView() {
        pictures = protocol.getPictures();


        BaseListView listView = new BaseListView(UIUtils.getContext());

        HomePictureHolder holder = new HomePictureHolder();
        holder.setData(pictures);
        View contentView = holder.getContentView();//得到holder里面管理的view对象

//        contentView.setLayoutParams(new AbsListView.LayoutParams(ViewPager.LayoutParams.MATCH_PARENT,
//                ViewPager.LayoutParams.WRAP_CONTENT));
        listView.addHeaderView(contentView);//把holder里的view对象添加到listview的上面


        listView.setAdapter(new ListBaseAdapter(datas) {
            @Override
            protected List<AppInfo> onload() {

                HomeProtocol protocol = new HomeProtocol();
                List<AppInfo> newData = protocol.load(datas.size());
                datas.addAll(newData);
                return newData;
            }
        });


        return listView;
    }



    public LoadingPage.LoadResult load() {

        protocol = new HomeProtocol();


        datas = protocol.load(0);


        return checkData(datas);
    }


}
