package com.szzgkon.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;

import com.szzgkon.googleplay.domain.AppInfo;
import com.szzgkon.googleplay.holder.DetailBottomHolder;
import com.szzgkon.googleplay.holder.DetailDesHolder;
import com.szzgkon.googleplay.holder.DetailInfoHolder;
import com.szzgkon.googleplay.holder.DetailSafeHolder;
import com.szzgkon.googleplay.holder.DetailScreenHolder;
import com.szzgkon.googleplay.protocol.DetailProtocol;
import com.szzgkon.googleplay.tools.UIUtils;
import com.szzgkon.googleplay.view.LoadingPage;

public class DetailActivity extends BaseActivity {

    private String packageName;
    private AppInfo data;

    private FrameLayout bottom_layout, detail_info, detail_safe, detail_des;
    private HorizontalScrollView detail_screen;
    private DetailInfoHolder detailInfoHolder;
    private DetailScreenHolder screenHolder;
    private DetailSafeHolder safeHolder;
    private DetailDesHolder desHolder;
    private DetailBottomHolder bottomHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //获取到打开当前activity的意图对象
        Intent intent = getIntent();

        packageName = intent.getStringExtra("packageName");//得到详细信息的包名
        super.onCreate(savedInstanceState);


        initView();
        init();
        initActionBar();
    }

    @Override
    public void initView() {
        LoadingPage loadingPage = new LoadingPage(this) {
            @Override
            public View createSuccessView() {
                return DetailActivity.this.createSuccessView();
            }

            @Override
            protected LoadResult load() {
                return DetailActivity.this.load();
            }
        };
        loadingPage.show();//必须调用show方法，才会请求服务器
        setContentView(loadingPage);
    }

    /**
     * 加载成功的界面
     *
     * @return
     */
    private View createSuccessView() {
        View view = UIUtils.inflate(R.layout.activity_detail);

        bottom_layout = (FrameLayout) view.findViewById(R.id.bottom_layout);
        //操作应用程序信息
        detail_info = (FrameLayout) view.findViewById(R.id.detail_info);
        detailInfoHolder = new DetailInfoHolder();
        detailInfoHolder.setData(data);
        detail_info.addView(detailInfoHolder.getContentView());

        //安全标记
        detail_safe = (FrameLayout) view.findViewById(R.id.detail_safe);
        safeHolder = new DetailSafeHolder();
        safeHolder.setData(data);
        detail_safe.addView(safeHolder.getContentView());

        //描述
        detail_des = (FrameLayout) view.findViewById(R.id.detail_des);
        desHolder = new DetailDesHolder();
        desHolder.setData(data);
        detail_des.addView(desHolder.getContentView());


        //中间五张图片
        detail_screen = (HorizontalScrollView) view.findViewById(R.id.detail_screen);
        screenHolder = new DetailScreenHolder();
        screenHolder.setData(data);
        detail_screen.addView(screenHolder.getContentView());


        return view;
    }

    /**
     * 请求服务器加载数据
     *
     * @return
     */

    private LoadingPage.LoadResult load() {

        DetailProtocol protocol = new DetailProtocol(packageName);

        data = protocol.load(0);
        if (data == null) {
            return LoadingPage.LoadResult.error;
        } else {
            return LoadingPage.LoadResult.success;
        }

    }

    @Override
    public void init() {

    }


    @Override
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }
}
