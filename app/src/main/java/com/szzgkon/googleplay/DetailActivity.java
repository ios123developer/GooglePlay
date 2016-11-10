package com.szzgkon.googleplay;

import android.support.v7.app.ActionBar;

public class DetailActivity extends BaseActivity {

    @Override
    protected void initView() {
        setContentView(R.layout.activity_detail);
    }

    @Override
    protected void init() {

    }



    @Override
    protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }
}
