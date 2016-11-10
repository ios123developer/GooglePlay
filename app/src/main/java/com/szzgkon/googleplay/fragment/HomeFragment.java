package com.szzgkon.googleplay.fragment;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.tools.ViewUtils;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;


    public static int state = STATE_UNKOWN;

    private FrameLayout frameLayout;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(frameLayout == null){
            frameLayout = new FrameLayout(getActivity());
            init();//在FrameLayout中添加4种不同的加载的界面
        }else {
            ViewUtils.removeParent(frameLayout);//先移除frameLayout之前的父view

        }


        show();//根据服务器的数据，切换状态

        return frameLayout;
    }

    //四种界面 ：加载中界面，错误界面，为空的界面，成功的界面

    private View loadingView;//加载中的界面
    private View errorView;//错误界面
    private View emptyView;//空界面
    private View successView;//加载成功的界面



    //在FrameLayout中添加4种不同的加载的界面
    private void init() {

        loadingView = creatLoadingView();
        if(loadingView != null){
            frameLayout.addView(loadingView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                              ViewGroup.LayoutParams.MATCH_PARENT));
        }

        errorView = creatErrorView();
        if(errorView != null){
            frameLayout.addView(errorView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

        }

        emptyView = creatEmptyView();
        if(emptyView != null){
            frameLayout.addView(emptyView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        }




    }

    private View creatSuccessView() {
        TextView tv = new TextView(getActivity());
        tv.setText("加载成功了......");
        tv.setTextSize(20);

        return tv;
    }

    private View creatEmptyView() {

        View view = View.inflate(getActivity(), R.layout.loadpage_empty, null);

        return view;
    }

    private View creatErrorView() {
        View view = View.inflate(getActivity(),R.layout.loadpage_error, null);

        Button page_bt = (Button) view.findViewById(R.id.page_bt);
        page_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //重新向服务器请求
                show();
            }
        });

        return view;

    }

    private View creatLoadingView() {

        View view = View.inflate(getActivity(), R.layout.loadpage_loading, null);

        return view;

    }

    //根据同的状态显示不同的界面
    private void showPage() {
        if(loadingView != null){
            loadingView.setVisibility(state==STATE_UNKOWN||state==STATE_LOADING?View.VISIBLE:View.INVISIBLE);

        }
        if(errorView != null){

            errorView.setVisibility(state == STATE_ERROR?View.VISIBLE:View.INVISIBLE);
        }
        if(emptyView != null){
         emptyView.setVisibility(state == STATE_EMPTY?View.VISIBLE:View.INVISIBLE);
        }

        if(state == STATE_SUCCESS){
            successView = creatSuccessView();
            if(successView != null){
                frameLayout.addView(successView,
                        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

                successView.setVisibility(View.VISIBLE);
            }

        }

    }

    //枚举
    enum LoadResult{
        error(2),empty(3),success(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //根据服务器的数据，切换状态
    private void show() {


        if(state == STATE_ERROR||state==STATE_EMPTY){
         state = STATE_LOADING;
        }

        //请求服务器 获取服务器上的数据 进行判断
        //请求服务器 返回一个结果

        new Thread(){
            @Override
            public void run() {
                super.run();
                SystemClock.sleep(2000);
              final  LoadResult result = load();

                if(getActivity()!=null){
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(result != null){
                                state = result.getValue();
                                showPage();//状态改变了，重新判断当前应该显示哪个界面
                            }
                        }
                    });
                }
            }
        }.start();
        showPage();


    }

    private LoadResult load() {
        return LoadResult.success;
    }
}
