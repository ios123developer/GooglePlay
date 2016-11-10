package com.szzgkon.googleplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.szzgkon.googleplay.fragment.AppFragment;
import com.szzgkon.googleplay.fragment.HomeFragment;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ViewPager mViewPager;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager)findViewById(R.id.vp);




        mViewPager.setAdapter(new MainAdapter(getSupportFragmentManager()));


        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE);
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        ActionBar.Tab tab1 = actionBar.newTab().setText("标签1").setTabListener(new MyTabListener());
        actionBar.addTab(tab1);
        ActionBar.Tab tab2 = actionBar.newTab().setText("标签2").setTabListener(new MyTabListener());
        actionBar.addTab(tab2);
        ActionBar.Tab tab3 = actionBar.newTab().setText("标签3").setTabListener(new MyTabListener());
        actionBar.addTab(tab3);
        ActionBar.Tab tab4 = actionBar.newTab().setText("标签4").setTabListener(new MyTabListener());
        actionBar.addTab(tab4);


        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            /**
             * 当页面发生变化的时候调用
             * @param position
             */

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                getSupportActionBar().setSelectedNavigationItem(position);

            }
        });


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
            if(position == 0){
                return new HomeFragment();
            } else {
                return new AppFragment();
            }
        }

        /**
         * 一共几个条目
         * @return
         */
        @Override
        public int getCount() {
            return 4;
        }

        /**
         * 返回每个条目的标题
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return "标签" + position;
        }
    }

    private class MyTabListener implements ActionBar.TabListener {

        /**
         * 当tab标签被选中的时候viewpager切换到指定位置
         * @param tab
         * @param ft
         */
        @Override
        public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
            mViewPager.setCurrentItem(tab.getPosition());

        }

        @Override
        public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

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
