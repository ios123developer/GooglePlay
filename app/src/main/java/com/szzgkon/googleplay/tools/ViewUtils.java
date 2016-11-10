package com.szzgkon.googleplay.tools;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/10 下午5:17
 *
 * 描述：view的管理工具类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class ViewUtils {

    public static void removeParent(View v){
        //先找到父view，再通过父view移除自己
        ViewParent parent = v.getParent();

        if(parent instanceof ViewGroup){
         ViewGroup group = (ViewGroup) parent;
            group.removeView(v);
        }
    }
}
