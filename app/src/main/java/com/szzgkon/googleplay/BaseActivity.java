package com.szzgkon.googleplay;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import java.util.LinkedList;
import java.util.List;


/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/10 上午10:04
 *
 * 描述：抽取activity基类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public abstract class BaseActivity extends AppCompatActivity {
    //在基类中可以管理所有的activity
    List<BaseActivity> mActivities = new LinkedList<BaseActivity>();

    /**
     * LinkedList和ArrayList的区别
     * LinkedList增删比较快
     * ArrayList查询比较快
     *
     */

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

        synchronized (mActivities){
            mActivities.add(this);

        }

        init();
        initView();
        initActionBar();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        synchronized (mActivities){
            mActivities.remove(this);

        }
    }

    public void killAll(){
        //复制了一份mActivities 集合

        List<BaseActivity> copy;
        synchronized (mActivities){
            copy = new LinkedList<BaseActivity>(mActivities);

        }

        for (BaseActivity activity : copy) {
            activity.finish();
        }

        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    protected abstract   void init();
    protected  abstract void initView();
    protected abstract void initActionBar();


}
