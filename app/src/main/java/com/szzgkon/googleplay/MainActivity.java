package com.szzgkon.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.szzgkon.googleplay.fragment.BaseFragment;
import com.szzgkon.googleplay.tools.UIUtils;

public class MainActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    private ViewPager mViewPager;
    private PagerTabStrip pager_tab_strip;
    private String[] tab_names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     init();
     initView();
     initActionBar();

    }

    @Override
    protected void init() {

    }

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.vp);
        tab_names = UIUtils.getStringArray(R.array.tab_names);


        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                BaseFragment creatFragment = FragmentFactory.creatFragment(position);
                creatFragment.show();
            }
        });

        pager_tab_strip = (PagerTabStrip)findViewById(R.id.pager_tab_strip);

        pager_tab_strip.setTabIndicatorColor(getResources().getColor(R.color.indicatorColor));

    }

    @Override
    public void initActionBar() {
        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
    }

    private class MainAdapter extends FragmentStatePagerAdapter{


        public MainAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * 每个条目返回的fragment
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
          return FragmentFactory.creatFragment(position);
        }

        /**
         * 一共几个条目
         * @return
         */
        @Override
        public int getCount() {
            return tab_names.length;
        }

        /**
         * 返回每个条目的标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return tab_names[position];
        }
    }



    /**
     * 显示菜单的初始化方法
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.activity_main, menu);

        /**
         * 版本判断

         if(Build.VERSION.SDK_INT > 11){

         }


         */
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    /**
     * 点击菜单的响应事件
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "搜索", Toast.LENGTH_SHORT).show();
        }

        return  super.onOptionsItemSelected(item);
    }


    /**
     * 当搜索提交的时候
     *
     * @param query
     * @return
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    /**
     * 当搜索的文本发生变化的时候
     *
     * @param newText
     * @return
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        return true;
    }


    public void click(View view) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        startActivity(intent);
    }



}
