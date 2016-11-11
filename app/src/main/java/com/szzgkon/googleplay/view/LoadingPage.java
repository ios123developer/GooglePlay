package com.szzgkon.googleplay.view;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.szzgkon.googleplay.R;
import com.szzgkon.googleplay.manager.ThreadManager;
import com.szzgkon.googleplay.tools.UIUtils;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/11 上午10:53
 *
 * 描述：创建自定义帧布局，功能是把BaseFrament
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class LoadingPage extends FrameLayout {


    public LoadingPage(Context context) {
        super(context);
        init();
    }



    public LoadingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public LoadingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();

    }
    public static final int STATE_UNKOWN = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_EMPTY = 3;
    public static final int STATE_SUCCESS = 4;


    public  int state = STATE_UNKOWN;




    //四种界面 ：加载中界面，错误界面，为空的界面，成功的界面

    private View loadingView;//加载中的界面
    private View errorView;//错误界面
    private View emptyView;//空界面
    private View successView;//加载成功的界面

    private void init() {

        loadingView = creatLoadingView();
        if (loadingView != null) {
            this.addView(loadingView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        }

        errorView = creatErrorView();
        if (errorView != null) {
            this.addView(errorView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));

        }

        emptyView = creatEmptyView();
        if (emptyView != null) {
            this.addView(emptyView,
                    new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    //根据同的状态显示不同的界面
    private void showPage() {
        if (loadingView != null) {
            loadingView.setVisibility(state == STATE_UNKOWN ||
                    state == STATE_LOADING ?
                    View.VISIBLE : View.INVISIBLE);

        }
        if (errorView != null) {

            errorView.setVisibility(state == STATE_ERROR ?
                    View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(state == STATE_EMPTY ?
                    View.VISIBLE : View.INVISIBLE);
        }

        if (state == STATE_SUCCESS) {
            successView = creatSuccessView();
            if (successView != null) {
                this.addView(successView,
                        new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT));

                successView.setVisibility(View.VISIBLE);
            }

        }else {
            if(successView != null){
                successView.setVisibility(View.INVISIBLE);

            }
        }

    }

    private View creatEmptyView() {

        View view = View.inflate(UIUtils.getContext(), R.layout.loadpage_empty, null);

        return view;
    }

    private View creatErrorView() {
        View view = View.inflate(UIUtils.getContext(), R.layout.loadpage_error, null);

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

        View view = View.inflate(UIUtils.getContext(), R.layout.loadpage_loading, null);

        return view;

    }
    //枚举
    public enum LoadResult {
        error(2), empty(3), success(4);
        int value;

        LoadResult(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    //根据服务器的数据，切换状态
    public void show() {

        if (state == STATE_ERROR || state == STATE_EMPTY) {
            state = STATE_LOADING;
        }

        //请求服务器 获取服务器上的数据 进行判断
        //请求服务器 返回一个结果

        new Thread() {
            @Override
            public void run() {
                super.run();

            }
        }.start();

        ThreadManager.getInstance().creatLongPool().execute(new Runnable(){

            @Override
            public void run() {
                SystemClock.sleep(2000);
                final LoadResult result = load();


                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (result != null) {
                            state = result.getValue();
                            showPage();//状态改变了，重新判断当前应该显示哪个界面
                        }
                    }
                });
            }
        });



        showPage();


    }

    /**
     * 请求服务器
     * @return
     */
    public abstract LoadResult load();

    /**
     * 创建一个成功的界面
     * @return
     */
    public abstract View creatSuccessView();
}
