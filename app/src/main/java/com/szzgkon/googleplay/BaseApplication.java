package com.szzgkon.googleplay;

import android.app.Application;


/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/10 上午10:35
 *
 * 描述：代表当前应用程序
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class BaseApplication extends Application {

   private static BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    public static BaseApplication getApplication() {
        return application;
    }
}
