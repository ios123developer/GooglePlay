package com.szzgkon.googleplay.tools;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Process;
import android.view.View;

import com.szzgkon.googleplay.BaseActivity;
import com.szzgkon.googleplay.BaseApplication;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/10 上午10:33
 *
 * 描述：UI工具类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class UIUtils {

    /**
     * 获取到字符数组
     * @param tabNames 字符数组的id
     */
    public static String[] getStringArray(int tabNames){


        return getResource().getStringArray(tabNames);
    }

    public static Resources getResource(){
        return BaseApplication.getApplication().getResources();
    }

    public static Context getContext(){
        return BaseApplication.getApplication();
    }

    /* dip 转 px */

    public static int dip2px(int dip){
        final float scale  = getResource().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5f);
    }

    /* px 转 dip */

    public static int px2dip(int px){
        final float scale  = getResource().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    /**
     * 把runable方法提交到主线程运行
     * @param runable
     */
    public static void runOnUiThread(Runnable runable){
        if(Process.myTid() == BaseApplication.getMainTid()){
            runable.run();

        }else {
            BaseApplication.getHandler().post(runable);
        }
    }

    public static View inflate(int id) {
        return View.inflate(getContext(),id,null);
    }

    public static Drawable getDrawalbe(int id) {
        return getResource().getDrawable(id);
    }

    public static int getDimens(int homePictureHeight) {
        return (int) getResource().getDimension(homePictureHeight);
    }

    /**
     * 延迟执行某个任务
     * @param run
     * @param time
     */
    public static void postDelayed(Runnable run, int time) {
        BaseApplication.getHandler().postDelayed(run,time);

    }

    /**
     * 取消任务
     * @param run
     */
    public static void cancel(Runnable run) {
        BaseApplication.getHandler().removeCallbacks(run);
    }

    /**
     * 可以打开Activity
     * @param intent
     */
    public static void startActivity(Intent intent) {
        //如果不在activity里去打开activity，需要指定任务栈,需要设置标签

        if(BaseActivity.activity == null){
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            getContext().startActivity(intent);
        }else {
            BaseActivity.activity.startActivity(intent);
        }


    }
}

