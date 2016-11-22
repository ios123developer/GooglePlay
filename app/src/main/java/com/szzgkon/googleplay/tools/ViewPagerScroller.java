package com.szzgkon.googleplay.tools;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.lang.reflect.Field;


/**
 * ===================================================
 *
 * 版权：深圳市中广控信息科技有限公司 版权所有（c）2016
 *
 * 作者：张勇柯
 *
 * 版本：1.0
 *
 * 创建日期：16/11/21 下午4:30
 *
 * 描述：控制viewPager滑动速度的类
 *
 * 修订历史：
 *
 * ===================================================
 **/


public class ViewPagerScroller extends Scroller {
   private int mScrollDuration = 2000;

    public void setScrollDuration(int duration){
        this.mScrollDuration = duration;
    }


    public ViewPagerScroller(Context context) {
        super(context);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public ViewPagerScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mScrollDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy,mScrollDuration);
    }

    public void initViewPagerScroll(ViewPager viewPager){
        try {
            Field mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager,this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
