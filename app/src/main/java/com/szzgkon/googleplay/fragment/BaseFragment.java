package com.szzgkon.googleplay.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szzgkon.googleplay.tools.ViewUtils;
import com.szzgkon.googleplay.view.LoadingPage;


public abstract class BaseFragment extends Fragment {



    private LoadingPage loadingPage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (loadingPage == null) {
            loadingPage = new LoadingPage(getActivity()) {
                @Override
                public LoadingPage.LoadResult load() {
                    return BaseFragment.this.load();
                }

                @Override
                public View creatSuccessView() {
                    return BaseFragment.this.creatSuccessView();
                }
            };
        } else {
            ViewUtils.removeParent(loadingPage);//先移除frameLayout之前的父view

        }


        return loadingPage;
    }

    /**
     * 请求服务器
     * @return
     */
    public abstract LoadingPage.LoadResult load();

    /**
     * 创建一个成功的界面
     * @return
     */
    public abstract View creatSuccessView();

    public void show(){
        if(loadingPage != null){

            loadingPage.show();
        }
    }

}
